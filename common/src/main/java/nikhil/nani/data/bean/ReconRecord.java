package nikhil.nani.data.bean;

public interface ReconRecord
{
    default int getId()
    {
        throw new UnsupportedOperationException("getId() not implemented");
    }

    default boolean notEquals(ReconRecord other)
    {
        return !this.equals(other);
    }

    <K> K getKey();

    String getRecordString();
}
