package nikhil.nani.reconciler.jdk.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import nikhil.nani.data.bean.Breaks;
import nikhil.nani.data.bean.ReconRecord;
import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.data.bean.RequestType;
import nikhil.nani.data.bean.ReservationKey;
import nikhil.nani.data.service.ReconcilerService;
import nikhil.nani.data.util.FileParserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JdkReconcilerServiceImpl implements ReconcilerService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(JdkReconcilerServiceImpl.class);
    private static final String COMMA_DELIMITER = ",";

    private String outputFileDir = "";

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
        return "reconciled using JDK";
    }

    private Breaks<ReconRecord> getReconRecordBreaksForSmallRequest(ReconcilerRequest request)
    {
        if (request.isIgnoreDuplicates())
        {
            Map<Integer, ReconRecord> map1 = this.getPersonMap(request);
            return this.compareWithDuplicatesIgnored(map1, request.getPathFile2(), request.getRequestType());
        }

        Map<Integer, List<ReconRecord>> multimap1 =
                this.getPersonRecordMultimap(request.getPathFile1(), request.getRequestType());
        Map<Integer, List<ReconRecord>> multimap2 =
                this.getPersonRecordMultimap(request.getPathFile2(), request.getRequestType());

        return this.compareMultimaps(multimap1, multimap2);
    }

    private Map<Integer, ReconRecord> getPersonMap(ReconcilerRequest request)
    {
        List<ReconRecord> personList = new ArrayList<>();
        FileParserUtil.readFile(request.getPathFile1(), personList, request.getRequestType());

        Map<Integer, ReconRecord> personMap = new HashMap<>();
        personList.forEach(reconRecord -> personMap.put(reconRecord.getKey(), reconRecord));

        return personMap;
    }

    private Breaks<ReconRecord> getReconRecordBreaksForLargeRequest(ReconcilerRequest request)
    {
        if (request.isIgnoreDuplicates())
        {
            // Impl on the left as only Impl implements Pool.
            Map<ReservationKey, ReconRecord> map1 = this.getReservationMap(request);
            return this.compareWithDuplicatesIgnored(map1, request.getPathFile2(), request.getRequestType());
        }

        Map<ReservationKey, List<ReconRecord>> multimap1 =
                this.getReservationRecordMultimap(request.getPathFile1(), request.getRequestType());
        Map<ReservationKey, List<ReconRecord>> multimap2 =
                this.getReservationRecordMultimap(request.getPathFile2(), request.getRequestType());

        return this.compareMultimaps(multimap1, multimap2);
    }

    private Map<ReservationKey, ReconRecord> getReservationMap(ReconcilerRequest request)
    {
        List<ReconRecord> reservationList = new ArrayList<>();
        FileParserUtil.readFile(request.getPathFile1(), reservationList, request.getRequestType());

        Map<ReservationKey, ReconRecord> reservationMap = new HashMap<>();
        reservationList.forEach(reconRecord -> reservationMap.put(reconRecord.getKey(), reconRecord));

        return reservationMap;
    }

    private Map<Integer, List<ReconRecord>> getPersonRecordMultimap(String path, RequestType requestType)
    {
        List<ReconRecord> personList = new ArrayList<>();
        FileParserUtil.readFile(path, personList, requestType);

        return personList.stream().collect(Collectors.groupingBy(ReconRecord::getKey));
    }

    private Map<ReservationKey, List<ReconRecord>> getReservationRecordMultimap(String path, RequestType requestType)
    {
        List<ReconRecord> reservationList = new ArrayList<>();
        FileParserUtil.readFile(path, reservationList, requestType);

        return reservationList.stream().collect(Collectors.groupingBy(ReconRecord::getKey));
    }

    private <T> Breaks<ReconRecord> compareMultimaps(
            Map<T, List<ReconRecord>> multimapLhs,
            Map<T, List<ReconRecord>> multimapRhs)
    {
        Breaks<ReconRecord> breaks = new Breaks<>(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        multimapLhs.forEach(
                (id, listLhs) ->
                {
                    List<ReconRecord> listRhs = multimapRhs.remove(id);

                    if (listRhs != null && !listRhs.isEmpty())
                    {
                        int minSize = Math.min(listLhs.size(), listRhs.size());
                        IntStream.range(0, minSize)
                                .filter(i -> listLhs.get(i).notEquals(listRhs.get(i)))
                                .mapToObj(i -> Arrays.asList(listLhs.get(i), listRhs.get(i)))
                                .forEach(breaks::addToBreaks);

                        if (listRhs.size() < listLhs.size())
                        {
                            listLhs.subList(minSize, listLhs.size()).forEach(breaks::addToPresentInLhsNotInRhs);
                        }
                        else if (listRhs.size() > listLhs.size())
                        {
                            listRhs.subList(minSize, listRhs.size()).forEach(breaks::addToPresentInRhsNotInLhs);
                        }
                    }
                    else
                    {
                        breaks.addAllToPresentInLhsNotInRhs(listLhs);
                    }
                });

        multimapRhs.forEach((id, rhsIterable) -> breaks.addAllToPresentInRhsNotInLhs(rhsIterable));

        return breaks;
    }

    private <K> Breaks<ReconRecord> compareWithDuplicatesIgnored(
            Map<K, ReconRecord> mapLhs,
            String pathFile2,
            RequestType requestType)
    {
        Breaks<ReconRecord> breaks = new Breaks<>(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathFile2)))
        {
            String currentLine;

            while ((currentLine = reader.readLine()) != null)
            {
                String[] split = currentLine.split(COMMA_DELIMITER);

                ReconRecord rhs = FileParserUtil.getSingleParsedRecord(split, requestType);

                ReconRecord lhs = mapLhs.remove(rhs.getKey());

                if (Objects.nonNull(lhs))
                {
                    if (!lhs.equals(rhs))
                    {
                        breaks.addToBreaks(Arrays.asList(lhs, rhs));
                    }
                }
                else
                {
                    breaks.addToPresentInRhsNotInLhs(rhs);
                }
            }
            mapLhs.forEach((id, reconRecord) -> breaks.addToPresentInLhsNotInRhs(reconRecord));
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
                    .forEach(each ->
                    {
                        try
                        {
                            this.writeRecords(writer, each);
                        }
                        catch (IOException e)
                        {
                            throw new RuntimeException(e);
                        }
                    });
            writer.flush();
        }
        catch (IOException e)
        {
            LOGGER.error("Something went wrong while writing file", e);
        }
    }

    private void writeRecords(BufferedWriter writer, List<ReconRecord> each) throws IOException
    {
        each.forEach(breakRecord ->
        {
            try
            {
                this.writeRecordStringAndAppend(writer, breakRecord, "||");
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        });
        writer.append(System.lineSeparator());
    }

    private void writeMissingRecords(File file, List<ReconRecord> missingRecords)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            missingRecords.forEach(each ->
            {
                try
                {
                    this.writeRecordStringAndAppend(writer, each, System.lineSeparator());
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            });
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
