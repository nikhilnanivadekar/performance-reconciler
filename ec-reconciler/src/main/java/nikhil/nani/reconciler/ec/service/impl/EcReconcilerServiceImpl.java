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
                Breaks<Person> personBreaks;
                if (request.isIgnoreDuplicates())
                {
                    // Impl on the left as only Impl implements Pool.
                    UnifiedSetWithHashingStrategy<Person> personSet1 = new UnifiedSetWithHashingStrategy<>(HashingStrategies.fromFunction(Person::getId));
                    this.readPersonFile(request.getPathFile1(), personSet1);

                    personBreaks = this.compareWithDuplicatesIgnored(personSet1, request.getPathFile2());
                    personSet1 = null;
                }
                else
                {
                    MutableListMultimap<Integer, Person> personMultimap1 = this.getRecordMultimap(request.getPathFile1());
                    MutableListMultimap<Integer, Person> personMultimap2 = this.getRecordMultimap(request.getPathFile2());

                    personBreaks = this.compareMultimaps(personMultimap1, personMultimap2);
                    personMultimap1 = null;
                    personMultimap2 = null;
                }

                this.writeBreakFiles(personBreaks);
            }
        }
        catch (Exception e)
        {
            return "Something went wrong while reconciling:" + e.getMessage();
        }

        return "reconciled using EC";
    }

    private void readPersonFile(String filePath, Collection<Person> personCollection)
    {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath)))
        {
            String currentLine;

            while ((currentLine = reader.readLine()) != null)
            {
                String[] split = currentLine.split(COMMA_DELIMITER);

                personCollection.add(
                        new Person(
                                Integer.valueOf(split[0]), //id
                                split[1], //firstName
                                split[2], //lastName
                                Integer.valueOf(split[3]), //age
                                split[4] //city
                        ));
            }
        }
        catch (IOException e)
        {
            LOGGER.error("Some went wrong while reading file: {}", filePath, e);
        }
    }

    private MutableListMultimap<Integer, Person> getRecordMultimap(String path)
    {
        MutableList<Person> personList = Lists.mutable.empty();
        this.readPersonFile(path, personList);

        return personList.groupBy(Person::getId);
    }

    private Breaks<Person> compareMultimaps(MutableListMultimap<Integer, Person> personMultimapLhs, MutableListMultimap<Integer, Person> personMultimapRhs)
    {
        Breaks<Person> breaks = new Breaks<>(Lists.mutable.empty(), Lists.mutable.empty(), Lists.mutable.empty());

        personMultimapLhs.forEachKeyMultiValues(
                (id, personIterable1) ->
                {
                    MutableList<Person> personListLhs = (MutableList<Person>) personIterable1;
                    MutableList<Person> personListRhs = personMultimapRhs.removeAll(id);

                    if (Iterate.notEmpty(personListRhs))
                    {
                        MutableIntSet visitedIndexes = IntSets.mutable.empty();
                        personListLhs.forEachWithIndex((personLhs, index) ->
                        {
                            if (index < personListRhs.size())
                            {
                                Person personRhs = personListRhs.get(index);

                                if (!personRhs.equals(personLhs))
                                {
                                    breaks.addToBreaks(Lists.mutable.with(personLhs, personRhs));
                                }

                                visitedIndexes.add(index);
                            }
                            else
                            {
                                breaks.addToPresentInLhsNotInRhs(personLhs);
                            }
                        });

                        personListRhs.forEachWithIndex((personRhs, index) ->
                        {
                            if (!visitedIndexes.contains(index))
                            {
                                breaks.addToPresentInRhsNotInLhs(personRhs);
                            }
                        });
                    }
                    else
                    {
                        breaks.addAllToPresentInLhsNotInRhs(personListLhs);
                    }
                });

        personMultimapRhs.forEachKeyMultiValues((id, personRhsIterable) ->
                breaks.addAllToPresentInRhsNotInLhs((MutableList<Person>) personRhsIterable));

        return breaks;
    }

    private Breaks<Person> compareWithDuplicatesIgnored(UnifiedSetWithHashingStrategy<Person> personSetLhs, String pathFile2)
    {
        Breaks<Person> breaks = new Breaks<>(Lists.mutable.empty(), Lists.mutable.empty(), Lists.mutable.empty());

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathFile2)))
        {
            String currentLine;
            String COMMA_DELIMITER = ",";

            while ((currentLine = reader.readLine()) != null)
            {
                String[] split = currentLine.split(COMMA_DELIMITER);

                Person personRhs = new Person(
                        Integer.valueOf(split[0]), //id
                        split[1], //firstName
                        split[2], //lastName
                        Integer.valueOf(split[3]), //age
                        split[4] //city
                );

                Person personLhs = personSetLhs.removeFromPool(personRhs);

                if (Objects.nonNull(personLhs))
                {
                    if (!personLhs.equals(personRhs))
                    {
                        breaks.addToBreaks(Lists.mutable.with(personLhs, personRhs));
                    }
                }
                else
                {
                    breaks.addToPresentInRhsNotInLhs(personRhs);
                }
            }
            personSetLhs.each(breaks::addToPresentInLhsNotInRhs);
        }
        catch (IOException e)
        {
            LOGGER.error("Some went wrong while reading file: {}", pathFile2, e);
        }

        return breaks;
    }

    private void writeBreakFiles(Breaks<Person> personBreaks)
    {
        File presentInLhsNotInRhs = new File(this.outputFileDir + "/PresentInLhsNotInRhs-" + UUID.randomUUID() + ".dat");
        File presentInRhsNotInLhs = new File(this.outputFileDir + "/PresentInRhsNotInLhs-" + UUID.randomUUID() + ".dat");
        File breaks = new File(this.outputFileDir + "/Breaks-" + UUID.randomUUID() + ".dat");

        this.writeMissingRecords(presentInLhsNotInRhs, personBreaks.getPresentInLhsNotInRhs());

        this.writeMissingRecords(presentInRhsNotInLhs, personBreaks.getPresentInRhsNotInLhs());

        this.writeBreaks(personBreaks, breaks);
    }

    private void writeBreaks(Breaks<Person> personBreaks, File breaks)
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

    private void writeMissingRecords(File file, List<Person> missingRecords)
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
