package nikhil.nani.data.bean;

public interface ReconRecord
{
    default int getId()
    {
        throw new UnsupportedOperationException("getId() not implemented");
    }

    default <K> K getKey()
    {
        throw new UnsupportedOperationException("getKey() not implemented");
    }

    String getRecordString();
}
