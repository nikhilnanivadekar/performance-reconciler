package nikhil.nani.reconciler.ec.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import nikhil.nani.data.bean.Person;
import nikhil.nani.data.bean.ReconcilerModuleType;
import nikhil.nani.data.bean.ReconcilerRequest;
import nikhil.nani.data.bean.RequestType;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EcReconcilerServiceImplTest
{
    @TempDir
    public File tempDir;

    private Person extraPersonInLhs = new Person(9999, "John", "Doe", 99, "Awesome City");
    private Person extraPersonInRhs = new Person(8888, "John", "Doe", 88, "Another Awesome City");
    private List<String> firstNames = Lists.mutable.with("James", "Robert", "John", "Michael", "William", "David", "Richard", "Joseph", "Thomas", "Charles");
    private List<String> lastNames = Lists.mutable.with("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davos", "Rodriguez", "Martinez");

    private EcReconcilerServiceImpl testObj;

    @BeforeEach
    public void setup()
    {
        this.testObj = new EcReconcilerServiceImpl();

        this.testObj.setOutputFileDir(this.tempDir.getAbsolutePath());
    }

    @Test
    public void reconcile()
    {
        assertEquals("reconciled using EC", this.testObj.reconcile(new ReconcilerRequest()));
    }

    @Test
    public void reconcile_RequestTypeSmall_IgnoreDuplicates_ButDuplicatesPresent() throws Exception
    {
        File lhsFile = new File(this.tempDir.getAbsolutePath() + "/lhs.dat");
        File rhsFile = new File(this.tempDir.getAbsolutePath() + "/rhs.dat");

        this.createFilesLhs(true, false, false, lhsFile);
        this.createFilesRhs(true, false, false, rhsFile);

        assertEquals("reconciled using EC",
                this.testObj.reconcile(new ReconcilerRequest(
                        lhsFile.getAbsolutePath(),
                        rhsFile.getAbsolutePath(),
                        RequestType.SMALL,
                        ReconcilerModuleType.EC,
                        true)));
        File file = new File(this.tempDir.getAbsolutePath());
        File[] files = file.listFiles();

        File breaks = ArrayIterate.detect(files, each -> each.getName().contains("Breaks-"));
        List<String> actualBreaks = Files.readAllLines(Paths.get(breaks.getAbsolutePath()));
        List<String> expectedBreaks = Files.readAllLines(Paths.get(this.getClass().getResource("/reconcile_RequestTypeSmall_IgnoreDuplicates_ButDuplicatesPresent-Breaks.dat").toURI()));
        assertEquals(expectedBreaks, actualBreaks);

        File presentInLhsNotInRhs = ArrayIterate.detect(files, each -> each.getName().contains("PresentInLhsNotInRhs-"));
        List<String> actualPresentInLhsNotInRhs = Files.readAllLines(Paths.get(presentInLhsNotInRhs.getAbsolutePath()));
        List<String> expectedPresentInLhsNotInRhs = Files.readAllLines(Paths.get(this.getClass().getResource("/reconcile_RequestTypeSmall_IgnoreDuplicates_ButDuplicatesPresent-PresentInLhsNotInRhs.dat").toURI()));
        assertEquals(expectedPresentInLhsNotInRhs, actualPresentInLhsNotInRhs);

        File presentInRhsNotInLhs = ArrayIterate.detect(files, each -> each.getName().contains("PresentInRhsNotInLhs-"));
        List<String> actualPresentInRhsNotInLhs = Files.readAllLines(Paths.get(presentInRhsNotInLhs.getAbsolutePath()));
        List<String> expectedPresentInRhsNotInLhs = Files.readAllLines(Paths.get(this.getClass().getResource("/reconcile_RequestTypeSmall_IgnoreDuplicates_ButDuplicatesPresent-PresentInRhsNotInLhs.dat").toURI()));
        assertEquals(expectedPresentInRhsNotInLhs, actualPresentInRhsNotInLhs);
    }

    @Test
    public void reconcile_RequestTypeSmall_IgnoreDuplicates_DuplicatesNotPresent() throws Exception
    {
        File lhsFile = new File(this.tempDir.getAbsolutePath() + "/lhs.dat");
        File rhsFile = new File(this.tempDir.getAbsolutePath() + "/rhs.dat");

        this.createFilesLhs(false, false, false, lhsFile);
        this.createFilesRhs(false, false, false, rhsFile);

        assertEquals("reconciled using EC",
                this.testObj.reconcile(new ReconcilerRequest(
                        lhsFile.getAbsolutePath(),
                        rhsFile.getAbsolutePath(),
                        RequestType.SMALL,
                        ReconcilerModuleType.EC,
                        true)));
        File file = new File(this.tempDir.getAbsolutePath());
        File[] files = file.listFiles();

        File breaks = ArrayIterate.detect(files, each -> each.getName().contains("Breaks-"));
        List<String> actualBreaks = Files.readAllLines(Paths.get(breaks.getAbsolutePath()));
        List<String> expectedBreaks = Files.readAllLines(Paths.get(this.getClass().getResource("/reconcile_RequestTypeSmall_IgnoreDuplicates_ButDuplicatesPresent-Breaks.dat").toURI()));
        assertEquals(expectedBreaks, actualBreaks);

        File presentInLhsNotInRhs = ArrayIterate.detect(files, each -> each.getName().contains("PresentInLhsNotInRhs-"));
        List<String> actualPresentInLhsNotInRhs = Files.readAllLines(Paths.get(presentInLhsNotInRhs.getAbsolutePath()));
        List<String> expectedPresentInLhsNotInRhs = Lists.mutable.with(extraPersonInLhs.getRecordString());
        assertEquals(expectedPresentInLhsNotInRhs, actualPresentInLhsNotInRhs);

        File presentInRhsNotInLhs = ArrayIterate.detect(files, each -> each.getName().contains("PresentInRhsNotInLhs-"));
        List<String> actualPresentInRhsNotInLhs = Files.readAllLines(Paths.get(presentInRhsNotInLhs.getAbsolutePath()));
        List<String> expectedPresentInRhsNotInLhs = Lists.mutable.with(extraPersonInRhs.getRecordString());
        assertEquals(expectedPresentInRhsNotInLhs, actualPresentInRhsNotInLhs);
    }

    @Test
    public void reconcile_RequestTypeSmall_DoNotIgnoreDuplicates_DuplicatesPresent() throws Exception
    {
        File lhsFile = new File(this.tempDir.getAbsolutePath() + "/lhs.dat");
        File rhsFile = new File(this.tempDir.getAbsolutePath() + "/rhs.dat");

        this.createFilesLhs(true, false, true, lhsFile);
        this.createFilesRhs(true, true, false, rhsFile);

        assertEquals("reconciled using EC",
                this.testObj.reconcile(new ReconcilerRequest(
                        lhsFile.getAbsolutePath(),
                        rhsFile.getAbsolutePath(),
                        RequestType.SMALL,
                        ReconcilerModuleType.EC,
                        false)));
        File file = new File(this.tempDir.getAbsolutePath());
        File[] files = file.listFiles();

        File breaks = ArrayIterate.detect(files, each -> each.getName().contains("Breaks-"));
        List<String> actualBreaks = Files.readAllLines(Paths.get(breaks.getAbsolutePath()));
        List<String> expectedBreaks = Files.readAllLines(Paths.get(this.getClass().getResource("/reconcile_RequestTypeSmall_DoNotIgnoreDuplicates_DuplicatesPresent-Breaks.dat").toURI()));
        assertEquals(expectedBreaks, actualBreaks);

        File presentInLhsNotInRhs = ArrayIterate.detect(files, each -> each.getName().contains("PresentInLhsNotInRhs-"));
        List<String> actualPresentInLhsNotInRhs = Files.readAllLines(Paths.get(presentInLhsNotInRhs.getAbsolutePath()));
        List<String> expectedPresentInLhsNotInRhs = Files.readAllLines(Paths.get(this.getClass().getResource("/reconcile_RequestTypeSmall_DoNotIgnoreDuplicates_DuplicatesPresent-PresentInLhsNotInRhs.dat").toURI()));
        assertEquals(expectedPresentInLhsNotInRhs, actualPresentInLhsNotInRhs);

        File presentInRhsNotInLhs = ArrayIterate.detect(files, each -> each.getName().contains("PresentInRhsNotInLhs-"));
        List<String> actualPresentInRhsNotInLhs = Files.readAllLines(Paths.get(presentInRhsNotInLhs.getAbsolutePath()));
        List<String> expectedPresentInRhsNotInLhs = Files.readAllLines(Paths.get(this.getClass().getResource("/reconcile_RequestTypeSmall_DoNotIgnoreDuplicates_DuplicatesPresent-PresentInRhsNotInLhs.dat").toURI()));
        assertEquals(expectedPresentInRhsNotInLhs, actualPresentInRhsNotInLhs);
    }

    @Test
    public void reconcile_RequestTypeSmall_DoNotIgnoreDuplicates_DuplicatesNotPresent() throws Exception
    {
        File lhsFile = new File(this.tempDir.getAbsolutePath() + "/lhs.dat");
        File rhsFile = new File(this.tempDir.getAbsolutePath() + "/rhs.dat");

        this.createFilesLhs(false, false, false, lhsFile);
        this.createFilesRhs(false, false, false, rhsFile);

        assertEquals("reconciled using EC",
                this.testObj.reconcile(new ReconcilerRequest(
                        lhsFile.getAbsolutePath(),
                        rhsFile.getAbsolutePath(),
                        RequestType.SMALL,
                        ReconcilerModuleType.EC,
                        false)));
        File file = new File(this.tempDir.getAbsolutePath());
        File[] files = file.listFiles();
        File breaks = ArrayIterate.detect(files, each -> each.getName().contains("Breaks-"));
        List<String> actualBreaks = Files.readAllLines(Paths.get(breaks.getAbsolutePath()));
        List<String> expectedBreaks = Files.readAllLines(Paths.get(this.getClass().getResource("/reconcile_RequestTypeSmall_DoNotIgnoreDuplicates_DuplicatesNotPresent-Breaks.dat").toURI()));
        assertEquals(expectedBreaks, actualBreaks);

        File presentInLhsNotInRhs = ArrayIterate.detect(files, each -> each.getName().contains("PresentInLhsNotInRhs-"));
        List<String> actualPresentInLhsNotInRhs = Files.readAllLines(Paths.get(presentInLhsNotInRhs.getAbsolutePath()));
        List<String> expectedPresentInLhsNotInRhs = Lists.mutable.with(extraPersonInLhs.getRecordString());
        assertEquals(expectedPresentInLhsNotInRhs, actualPresentInLhsNotInRhs);

        File presentInRhsNotInLhs = ArrayIterate.detect(files, each -> each.getName().contains("PresentInRhsNotInLhs-"));
        List<String> actualPresentInRhsNotInLhs = Files.readAllLines(Paths.get(presentInRhsNotInLhs.getAbsolutePath()));
        List<String> expectedPresentInRhsNotInLhs = Lists.mutable.with(extraPersonInRhs.getRecordString());
        assertEquals(expectedPresentInRhsNotInLhs, actualPresentInRhsNotInLhs);
    }

    private void createFilesLhs(boolean insertDuplicates1, boolean insertDuplicates2, boolean insertDuplicates3, File file) throws Exception
    {
        List<String> cities = Lists.mutable.with("Phoenix", "Sacramento", "Atlanta", "Denver", "Boise", "Honolulu", "Baton Rouge", "Boston", "Trenton", "Albany", "Raleigh", "Austin", "Salt Lake City", "Olympia");

        this.writeFile(
                insertDuplicates1,
                insertDuplicates2,
                insertDuplicates3,
                file,
                cities,
                firstNames,
                lastNames,
                extraPersonInLhs);
    }

    private void createFilesRhs(boolean insertDuplicates1, boolean insertDuplicates2, boolean insertDuplicates3, File file) throws Exception
    {
        List<String> cities = Lists.mutable.with("Phoenix", "Sacramento", "Denver", "Atlanta", "Boise", "Honolulu", "Baton Rouge", "Boston", "Trenton", "Albany", "Raleigh", "Austin", "Salt Lake City", "Olympia");

        this.writeFile(
                insertDuplicates1,
                insertDuplicates2,
                insertDuplicates3,
                file,
                cities,
                firstNames,
                lastNames,
                extraPersonInRhs);
    }

    private void writeFile(boolean insertDuplicates1, boolean insertDuplicates2, boolean insertDuplicates3, File file, List<String> cities, List<String> firstName, List<String> lastName, Person extraRecord) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            int count = 0;
            for (String city : cities)
            {
                for (int i = 0; i < 10; i++)
                {
                    String fName = firstName.get(i);
                    String lName = lastName.get(i);
                    this.writePerson(writer, count, fName, lName, i, city);
                    if (insertDuplicates1 && count % 5 == 0)
                    {
                        this.writePerson(writer, count, fName, lName, i, city);
                    }
                    if (insertDuplicates2 && count % 10 == 0)
                    {
                        this.writePerson(writer, count, fName, lName, i, city);
                    }
                    if (insertDuplicates3 && count % 7 == 0)
                    {
                        this.writePerson(writer, count, fName, lName, i, city);
                    }
                    count++;
                }
            }

            writer.write(extraRecord.getRecordString());
            writer.flush();
        }
        catch (IOException e)
        {
            throw e;
        }
    }

    private void writePerson(
            BufferedWriter writer,
            int count,
            String firstName,
            String lastName,
            int i,
            String city) throws IOException
    {
        writer.write(new Person(count, firstName, lastName, i + 10, city).getRecordString());
        writer.append(System.lineSeparator());
    }
}
