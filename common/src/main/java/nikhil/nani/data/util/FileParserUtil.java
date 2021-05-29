package nikhil.nani.data.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import nikhil.nani.data.bean.Person;
import nikhil.nani.data.bean.ReconRecord;
import nikhil.nani.data.bean.RequestType;
import nikhil.nani.data.bean.Reservation;

public final class FileParserUtil
{
    private static final String COMMA_DELIMITER = ",";

    private FileParserUtil()
    {
    }

    public static void readFile(String filePath, Collection<ReconRecord> reconRecordCollection, RequestType requestType)
    {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath)))
        {
            String currentLine;

            while ((currentLine = reader.readLine()) != null)
            {
                String[] split = currentLine.split(COMMA_DELIMITER);

                reconRecordCollection.add(FileParserUtil.getSingleParsedRecord(split, requestType));
            }
        }
        catch (IOException e)
        {
//            LOGGER.error("Some went wrong while reading file: {}", filePath, e);
        }
    }

    public static ReconRecord getSingleParsedRecord(String[] split, RequestType requestType)
    {
        if (RequestType.SMALL == requestType)
        {
            return Person.getPerson(split);
        }
        if (RequestType.LARGE == requestType)
        {
            return Reservation.getReservation(split);
        }
        return null;
    }
}
