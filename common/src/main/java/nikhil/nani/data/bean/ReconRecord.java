package nikhil.nani.data.bean;

public interface ReconRecord
{
    default int getId()
    {
        throw new UnsupportedOperationException("getId() not implemented");
    }

    <K> K getKey();

    String getRecordString();
}
