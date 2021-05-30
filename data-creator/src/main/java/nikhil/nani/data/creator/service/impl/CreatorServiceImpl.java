package nikhil.nani.data.creator.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nikhil.nani.data.bean.PaymentType;
import nikhil.nani.data.bean.Person;
import nikhil.nani.data.bean.ReconRecord;
import nikhil.nani.data.bean.RequestType;
import nikhil.nani.data.bean.Reservation;
import nikhil.nani.data.bean.TransportType;
import nikhil.nani.data.creator.bean.CreatorRequest;
import nikhil.nani.data.creator.service.CreatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreatorServiceImpl implements CreatorService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CreatorServiceImpl.class);

    public String create(CreatorRequest request)
    {
        LOGGER.info("Creating Data");

        List<ReconRecord> lhsList = new ArrayList<>();
        List<ReconRecord> rhsList = new ArrayList<>();

        for (int i = 0; i < request.getNumRecords(); i++)
        {
            if (request.getCreatorRequestType() == RequestType.SMALL)
            {
                Person person = new Person(i, "firstName" + i, "lastName" + i, i, "city" + i);
                lhsList.add(person);
                rhsList.add(person);
            }
            if (request.getCreatorRequestType() == RequestType.LARGE)
            {
                Reservation reservation = new Reservation("firstName" + i, "lastName" + i, "destination" + i, TransportType.AIR, "travelingFrom" + i, i, true, "airline" + i, false, "hotel" + i, true, "car" + i, "promo", PaymentType.CREDIT_CARD);
                lhsList.add(reservation);
                rhsList.add(reservation);
            }
        }

        this.addDataToLhs(lhsList, request.getCreatorRequestType(), request.getNumRecords());
        this.addDataToRhs(rhsList, request.getCreatorRequestType(), request.getNumRecords());

        this.writeFile(lhsList, request.getLhsFileName());
        this.writeFile(rhsList, request.getRhsFileName());

        return "created";
    }

    private void addDataToLhs(List<ReconRecord> lhsList, RequestType requestType, int numRecords)
    {
        for (int i = 0; i < numRecords / 10; i++)
        {
            if (requestType == RequestType.SMALL)
            {
                lhsList.add(new Person(i, "firstName" + i, "lastName" + i, i, "city" + i));
            }
            if (requestType == RequestType.LARGE)
            {
                Reservation reservation = new Reservation("firstName" + i, "lastName" + i, "destination" + i, TransportType.AIR, "travelingFrom" + i, i, true, "airline" + i, false, "hotel" + i, true, "car" + i, "promo", PaymentType.CHECK);
                lhsList.add(reservation);
            }
        }
        for (int i = -1; i >= -1 * numRecords; i--)
        {
            if (requestType == RequestType.SMALL)
            {
                lhsList.add(new Person(i, "firstName" + i, "lastName" + i, i, "city" + i));
            }
            if (requestType == RequestType.LARGE)
            {
                Reservation reservation = new Reservation("firstName" + i, "lastName" + i, "destination" + i, TransportType.AIR, "travelingFrom" + i, i, true, "airline" + i, false, "hotel" + i, true, "car" + i, "promo", PaymentType.CHECK);
                lhsList.add(reservation);
            }
        }
    }

    private void addDataToRhs(List<ReconRecord> rhsList, RequestType requestType, int numRecords)
    {
        for (int i = 0; i < numRecords / 10; i++)
        {
            if (requestType == RequestType.SMALL)
            {
                rhsList.add(new Person(i, "firstName:" + i, "lastName:" + i, i, "city:" + i));
            }
            if (requestType == RequestType.LARGE)
            {
                Reservation reservation = new Reservation("firstName:" + i, "lastName:" + i, "destination:" + i, TransportType.WATER, "travelingFrom:" + i, i, true, "airline:" + i, false, "hotel:" + i, true, "car:" + i, "promo:", PaymentType.CASH);
                rhsList.add(reservation);
            }
        }

        for (int i = 0; i < numRecords; i++)
        {
            int id = numRecords + i;
            if (requestType == RequestType.SMALL)
            {
                rhsList.add(new Person(id, "firstName" + i, "lastName" + i, i, "city" + i));
            }
            if (requestType == RequestType.LARGE)
            {
                Reservation reservation = new Reservation("firstName" + id, "lastName" + id, "destination" + id, TransportType.WATER, "travelingFrom" + id, id, true, "airline" + id, false, "hotel" + id, true, "car" + id, "promo", PaymentType.CASH);
                rhsList.add(reservation);
            }
        }
    }

    private void writeFile(List<ReconRecord> list, String fileName)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName))))
        {
            list.forEach(each ->
            {
                try
                {
                    writer.write(each.getRecordString());
                    writer.append(System.lineSeparator());
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
}
