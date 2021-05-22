package nikhil.nani.data.creator.bean;

public class CreatorRequest
{
    private String fullyQualifiedFileName;
    private CreatorRequestType creatorRequestType;
    private int numRecords;

    public CreatorRequest()
    {
    }

    public CreatorRequest(String fullyQualifiedFileName, CreatorRequestType creatorRequestType, int numRecords)
    {
        this.fullyQualifiedFileName = fullyQualifiedFileName;
        this.creatorRequestType = creatorRequestType;
        this.numRecords = numRecords;
    }

    public String getFullyQualifiedFileName()
    {
        return this.fullyQualifiedFileName;
    }

    public void setFullyQualifiedFileName(String fullyQualifiedFileName)
    {
        this.fullyQualifiedFileName = fullyQualifiedFileName;
    }

    public CreatorRequestType getCreatorRequestType()
    {
        return this.creatorRequestType;
    }

    public void setCreatorRequestType(CreatorRequestType creatorRequestType)
    {
        this.creatorRequestType = creatorRequestType;
    }

    public int getNumRecords()
    {
        return this.numRecords;
    }

    public void setNumRecords(int numRecords)
    {
        this.numRecords = numRecords;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }

        CreatorRequest that = (CreatorRequest) o;

        if (this.numRecords != that.numRecords)
        {
            return false;
        }
        if (this.fullyQualifiedFileName != null ? !this.fullyQualifiedFileName.equals(that.fullyQualifiedFileName) : that.fullyQualifiedFileName != null)
        {
            return false;
        }
        return this.creatorRequestType == that.creatorRequestType;
    }

    @Override
    public int hashCode()
    {
        int result = this.fullyQualifiedFileName != null ? this.fullyQualifiedFileName.hashCode() : 0;
        result = 31 * result + (this.creatorRequestType != null ? this.creatorRequestType.hashCode() : 0);
        result = 31 * result + this.numRecords;
        return result;
    }
}
