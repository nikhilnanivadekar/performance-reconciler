package nikhil.nani.reconciler.ec.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import nikhil.nani.data.bean.Breaks;
import nikhil.nani.data.bean.ReconRecord;
import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.data.bean.RequestType;
import nikhil.nani.data.bean.Reservation;
import nikhil.nani.data.bean.ReservationKey;
import nikhil.nani.data.service.ReconcilerService;
import nikhil.nani.data.util.FileParserUtil;
import org.eclipse.collections.api.block.HashingStrategy;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.block.factory.Procedures;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EcReconcilerServiceImpl implements ReconcilerService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EcReconcilerServiceImpl.class);
    private static final String COMMA_DELIMITER = ",";

    private static final HashingStrategy<ReconRecord> RESERVATION_HASHING_STRATEGY =
            EcReconcilerServiceImpl.getHashingReservationHashingStrategy();

    private String outputFileDir = "D:/reconciler/results/ec";

    protected void setOutputFileDir(String outputFileDir)
    {
        this.outputFileDir = outputFileDir;
    }

    @Override
    public String reconcile(ReconcilerRequest request)
    {
        LOGGER.info("Starting Reconciler:{} Time:{}", request, System.currentTimeMillis());
        try
        {
            Breaks<ReconRecord> breaks = null;
            if (request.getRequestType() == RequestType.SMALL)
            {
                breaks = this.getReconRecordBreaksForSmallRequest(request);
            }
            if (request.getRequestType() == RequestType.LARGE)
            {
                breaks = this.getReconRecordBreaksForLargeRequest(request);
            }
            if (Objects.nonNull(breaks))
            {
                this.writeBreakFiles(breaks);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Something went wrong while reconciling", e);
            return "Something went wrong while reconciling:" + e.getMessage();
        }

        LOGGER.info("Completed Reconciler:{} Time:{}", request, System.currentTimeMillis());
        return "reconciled using EC";
    }

    private Breaks<ReconRecord> getReconRecordBreaksForSmallRequest(ReconcilerRequest request)
    {
        if (request.isIgnoreDuplicates())
        {
            // Impl on the left as only Impl implements Pool.
            UnifiedSetWithHashingStrategy<ReconRecord> set1 =
                    UnifiedSetWithHashingStrategy.newSet(HashingStrategies.fromIntFunction(ReconRecord::getId));
            FileParserUtil.readFile(request.getPathFile1(), set1, request.getRequestType());

            return this.compareWithDuplicatesIgnored(set1, request.getPathFile2(), request.getRequestType());
        }

        MutableListMultimap<Integer, ReconRecord> multimap1 =
                this.getPersonRecordMultimap(request.getPathFile1(), request.getRequestType());
        MutableListMultimap<Integer, ReconRecord> multimap2 =
                this.getPersonRecordMultimap(request.getPathFile2(), request.getRequestType());

        return this.compareMultimaps(multimap1, multimap2);
    }

    private Breaks<ReconRecord> getReconRecordBreaksForLargeRequest(ReconcilerRequest request)
    {
        if (request.isIgnoreDuplicates())
        {
            // Impl on the left as only Impl implements Pool.
            UnifiedSetWithHashingStrategy<ReconRecord> set1 =
                    UnifiedSetWithHashingStrategy.newSet(RESERVATION_HASHING_STRATEGY);
            FileParserUtil.readFile(request.getPathFile1(), set1, request.getRequestType());

            return this.compareWithDuplicatesIgnored(set1, request.getPathFile2(), request.getRequestType());
        }

        MutableListMultimap<ReservationKey, ReconRecord> multimap1 =
                this.getReservationRecordMultimap(request.getPathFile1(), request.getRequestType());
        MutableListMultimap<ReservationKey, ReconRecord> multimap2 =
                this.getReservationRecordMultimap(request.getPathFile2(), request.getRequestType());

        return this.compareMultimaps(multimap1, multimap2);
    }

    private static HashingStrategy<ReconRecord> getHashingReservationHashingStrategy()
    {
        return HashingStrategies.chain(
                HashingStrategies.fromFunction(each -> ((Reservation) each).getFirstName()),
                HashingStrategies.fromFunction(each -> ((Reservation) each).getLastName()),
                HashingStrategies.fromFunction(each -> ((Reservation) each).getDestination()),
                HashingStrategies.fromFunction(each -> ((Reservation) each).getTransportType()));
    }

    private MutableListMultimap<Integer, ReconRecord> getPersonRecordMultimap(String path, RequestType requestType)
    {
        MutableList<ReconRecord> personList = Lists.mutable.empty();
        FileParserUtil.readFile(path, personList, requestType);

        return personList.groupBy(ReconRecord::getId);
    }

    private MutableListMultimap<ReservationKey, ReconRecord> getReservationRecordMultimap(
            String path,
            RequestType requestType)
    {
        MutableList<ReconRecord> reservationList = Lists.mutable.empty();
        FileParserUtil.readFile(path, reservationList, requestType);

        return reservationList.groupBy(ReconRecord::getKey);
    }

    private <T> Breaks<ReconRecord> compareMultimaps(
            MutableListMultimap<T, ReconRecord> multimapLhs,
            MutableListMultimap<T, ReconRecord> multimapRhs)
    {
        Breaks<ReconRecord> breaks = new Breaks<>(Lists.mutable.empty(), Lists.mutable.empty(), Lists.mutable.empty());

        multimapLhs.forEachKeyMutableList((id, listLhs) ->
        {
            MutableList<ReconRecord> listRhs = multimapRhs.removeAll(id);

            if (listRhs.notEmpty())
            {
                int minSize = Math.min(listLhs.size(), listRhs.size());
                listLhs.forEachWithIndex(0, minSize - 1, (left, index) ->
                {
                    ReconRecord right = listRhs.get(index);
                    if (left.notEquals(right))
                    {
                        breaks.addToBreaks(Lists.fixedSize.with(left, right));
                    }
                });
                if (listRhs.size() < listLhs.size())
                {
                    listLhs.forEachWithIndex(
                            minSize,
                            listLhs.size() - 1,
                            (each, index) -> breaks.addToPresentInLhsNotInRhs(each));
                }
                else if (listRhs.size() > listLhs.size())
                {
                    listRhs.forEachWithIndex(
                            minSize,
                            listRhs.size() - 1,
                            (each, index) -> breaks.addToPresentInRhsNotInLhs(each));
                }
            }
            else
            {
                breaks.addAllToPresentInLhsNotInRhs(listLhs);
            }
        });

        multimapRhs.forEachKeyMutableList((id, rhsList) -> breaks.addAllToPresentInRhsNotInLhs(rhsList));

        return breaks;
    }

    private Breaks<ReconRecord> compareWithDuplicatesIgnored(
            UnifiedSetWithHashingStrategy<ReconRecord> setLhs,
            String pathFile2,
            RequestType requestType)
    {
        Breaks<ReconRecord> breaks = new Breaks<>(Lists.mutable.empty(), Lists.mutable.empty(), Lists.mutable.empty());

        try (Stream<String> stream = Files.lines(Paths.get(pathFile2)))
        {
            stream.forEach(currentLine ->
            {
                String[] split = currentLine.split(COMMA_DELIMITER);
                ReconRecord rhs = FileParserUtil.getSingleParsedRecord(split, requestType);
                ReconRecord lhs = setLhs.removeFromPool(rhs);

                if (Objects.nonNull(lhs))
                {
                    if (lhs.notEquals(rhs))
                    {
                        breaks.addToBreaks(Lists.fixedSize.with(lhs, rhs));
                    }
                }
                else
                {
                    breaks.addToPresentInRhsNotInLhs(rhs);
                }
            });
            setLhs.each(breaks::addToPresentInLhsNotInRhs);
        }
        catch (IOException e)
        {
            LOGGER.error("Some went wrong while reading file: {}", pathFile2, e);
        }

        return breaks;
    }

    private void writeBreakFiles(Breaks<ReconRecord> personBreaks)
    {
        File presentInLhsNotInRhs =
                new File(this.outputFileDir + "/PresentInLhsNotInRhs-" + UUID.randomUUID() + ".dat");
        File presentInRhsNotInLhs =
                new File(this.outputFileDir + "/PresentInRhsNotInLhs-" + UUID.randomUUID() + ".dat");
        File breaks = new File(this.outputFileDir + "/Breaks-" + UUID.randomUUID() + ".dat");

        this.writeMissingRecords(presentInLhsNotInRhs, personBreaks.getPresentInLhsNotInRhs());

        this.writeMissingRecords(presentInRhsNotInLhs, personBreaks.getPresentInRhsNotInLhs());

        this.writeBreaks(personBreaks, breaks);
    }

    private void writeBreaks(Breaks<ReconRecord> reconRecordBreaks, File breaks)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(breaks)))
        {
            reconRecordBreaks.getBreaks()
                    .forEach(Procedures.throwing(each -> this.writeRecords(writer, each)));
            writer.flush();
        }
        catch (IOException e)
        {
            LOGGER.error("Something went wrong while writing file", e);
        }
    }

    private void writeRecords(BufferedWriter writer, List<ReconRecord> each) throws IOException
    {
        each.forEach(Procedures.throwing(
                breakRecord -> this.writeRecordStringAndAppend(writer, breakRecord, "||")));
        writer.append(System.lineSeparator());
    }

    private void writeMissingRecords(File file, List<ReconRecord> missingRecords)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            missingRecords.forEach(Procedures.throwing(
                    each -> this.writeRecordStringAndAppend(writer, each, System.lineSeparator())));
            writer.flush();
        }
        catch (IOException e)
        {
            LOGGER.error("Something went wrong while writing file", e);
        }
    }

    private void writeRecordStringAndAppend(BufferedWriter writer, ReconRecord each, String s) throws IOException
    {
        writer.write(each.getRecordString());
        writer.append(s);
    }
}
