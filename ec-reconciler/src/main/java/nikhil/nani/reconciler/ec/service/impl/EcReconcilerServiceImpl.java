package nikhil.nani.reconciler.ec.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import nikhil.nani.data.bean.Breaks;
import nikhil.nani.data.bean.Person;
import nikhil.nani.data.bean.ReconRecord;
import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.data.bean.RequestType;
import nikhil.nani.data.service.ReconcilerService;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.eclipse.collections.impl.utility.Iterate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EcReconcilerServiceImpl implements ReconcilerService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EcReconcilerServiceImpl.class);

    private static final String COMMA_DELIMITER = ",";

    private String outputFileDir = "";

    protected void setOutputFileDir(String outputFileDir)
    {
        this.outputFileDir = outputFileDir;
    }

    @Override
    public String reconcile(ReconcilerRequest request)
    {
        try
        {
            if (request.getRequestType() == RequestType.SMALL)
            {
                Breaks<ReconRecord> breaks;
                if (request.isIgnoreDuplicates())
                {
                    // Impl on the left as only Impl implements Pool.
                    UnifiedSetWithHashingStrategy<ReconRecord> set1 = new UnifiedSetWithHashingStrategy<>(HashingStrategies.fromFunction(each -> ((Person) each).getId()));
                    this.readFile(request.getPathFile1(), set1, request.getRequestType());

                    breaks = this.compareWithDuplicatesIgnored(set1, request.getPathFile2(), request.getRequestType());
                    set1 = null;
                }
                else
                {
                    MutableListMultimap<Integer, ReconRecord> multimap1 = this.getPersonRecordMultimap(request.getPathFile1(), request.getRequestType());
                    MutableListMultimap<Integer, ReconRecord> multimap2 = this.getPersonRecordMultimap(request.getPathFile2(), request.getRequestType());

                    breaks = this.compareMultimaps(multimap1, multimap2);
                    multimap1 = null;
                    multimap2 = null;
                }

                this.writeBreakFiles(breaks);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Something went wrong while reconciling", e);
            return "Something went wrong while reconciling:" + e.getMessage();
        }

        return "reconciled using EC";
    }

    private void readFile(String filePath, Collection<ReconRecord> reconRecordCollection, RequestType requestType)
    {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath)))
        {
            String currentLine;

            while ((currentLine = reader.readLine()) != null)
            {
                String[] split = currentLine.split(COMMA_DELIMITER);

                reconRecordCollection.add(this.getSingleParsedRecord(split, requestType));
            }
        }
        catch (IOException e)
        {
            LOGGER.error("Some went wrong while reading file: {}", filePath, e);
        }
    }

    private MutableListMultimap<Integer, ReconRecord> getPersonRecordMultimap(String path, RequestType requestType)
    {
        MutableList<ReconRecord> personList = Lists.mutable.empty();
        this.readFile(path, personList, requestType);

        return personList.groupBy(each -> ((Person) each).getId());
    }

    private <T> Breaks<ReconRecord> compareMultimaps(MutableListMultimap<T, ReconRecord> multimapLhs, MutableListMultimap<T, ReconRecord> multimapRhs)
    {
        Breaks<ReconRecord> breaks = new Breaks<>(Lists.mutable.empty(), Lists.mutable.empty(), Lists.mutable.empty());

        multimapLhs.forEachKeyMultiValues(
                (id, reconRecordIterable) ->
                {
                    MutableList<ReconRecord> listLhs = (MutableList<ReconRecord>) reconRecordIterable;
                    MutableList<ReconRecord> listRhs = multimapRhs.removeAll(id);

                    if (Iterate.notEmpty(listRhs))
                    {
                        MutableIntSet visitedIndexes = IntSets.mutable.empty();
                        listLhs.forEachWithIndex((lhs, index) ->
                        {
                            if (index < listRhs.size())
                            {
                                ReconRecord rhs = listRhs.get(index);

                                if (!rhs.equals(lhs))
                                {
                                    breaks.addToBreaks(Lists.mutable.with(lhs, rhs));
                                }

                                visitedIndexes.add(index);
                            }
                            else
                            {
                                breaks.addToPresentInLhsNotInRhs(lhs);
                            }
                        });

                        listRhs.forEachWithIndex((rhs, index) ->
                        {
                            if (!visitedIndexes.contains(index))
                            {
                                breaks.addToPresentInRhsNotInLhs(rhs);
                            }
                        });
                    }
                    else
                    {
                        breaks.addAllToPresentInLhsNotInRhs(listLhs);
                    }
                });

        multimapRhs.forEachKeyMultiValues((id, rhsIterable) ->
                breaks.addAllToPresentInRhsNotInLhs((MutableList<ReconRecord>) rhsIterable));

        return breaks;
    }

    private Breaks<ReconRecord> compareWithDuplicatesIgnored(UnifiedSetWithHashingStrategy<ReconRecord> setLhs, String pathFile2, RequestType requestType)
    {
        Breaks<ReconRecord> breaks = new Breaks<>(Lists.mutable.empty(), Lists.mutable.empty(), Lists.mutable.empty());

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathFile2)))
        {
            String currentLine;

            while ((currentLine = reader.readLine()) != null)
            {
                String[] split = currentLine.split(COMMA_DELIMITER);

                ReconRecord rhs = this.getSingleParsedRecord(split, requestType);

                ReconRecord lhs = setLhs.removeFromPool(rhs);

                if (Objects.nonNull(lhs))
                {
                    if (!lhs.equals(rhs))
                    {
                        breaks.addToBreaks(Lists.mutable.with(lhs, rhs));
                    }
                }
                else
                {
                    breaks.addToPresentInRhsNotInLhs(rhs);
                }
            }
            setLhs.each(breaks::addToPresentInLhsNotInRhs);
        }
        catch (IOException e)
        {
            LOGGER.error("Some went wrong while reading file: {}", pathFile2, e);
        }

        return breaks;
    }

    private ReconRecord getSingleParsedRecord(String[] split, RequestType requestType)
    {
        if (RequestType.SMALL == requestType)
        {
            return new Person(
                    Integer.valueOf(split[0]), //id
                    split[1], //firstName
                    split[2], //lastName
                    Integer.valueOf(split[3]), //age
                    split[4] //city
            );
        }
        return null;
    }

    private void writeBreakFiles(Breaks<ReconRecord> personBreaks)
    {
        File presentInLhsNotInRhs = new File(this.outputFileDir + "/PresentInLhsNotInRhs-" + UUID.randomUUID() + ".dat");
        File presentInRhsNotInLhs = new File(this.outputFileDir + "/PresentInRhsNotInLhs-" + UUID.randomUUID() + ".dat");
        File breaks = new File(this.outputFileDir + "/Breaks-" + UUID.randomUUID() + ".dat");

        this.writeMissingRecords(presentInLhsNotInRhs, personBreaks.getPresentInLhsNotInRhs());

        this.writeMissingRecords(presentInRhsNotInLhs, personBreaks.getPresentInRhsNotInLhs());

        this.writeBreaks(personBreaks, breaks);
    }

    private void writeBreaks(Breaks<ReconRecord> personBreaks, File breaks)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(breaks)))
        {
            personBreaks.getBreaks()
                    .forEach(Procedures.throwing(
                            each ->
                            {
                                each.forEach(Procedures.throwing(
                                        breakRecord ->
                                        {
                                            writer.write(breakRecord.getRecordString());
                                            writer.append("||");
                                        }));

                                writer.append(System.lineSeparator());
                            }));
            writer.flush();
        }
        catch (IOException e)
        {
            LOGGER.error("Something went wrong while writing file", e);
        }
    }

    private void writeMissingRecords(File file, List<ReconRecord> missingRecords)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            missingRecords.forEach(Procedures.throwing(
                    each ->
                    {
                        writer.write(each.getRecordString());
                        writer.append(System.lineSeparator());
                    }));
            writer.flush();
        }
        catch (IOException e)
        {
            LOGGER.error("Something went wrong while writing file", e);
        }
    }
}
