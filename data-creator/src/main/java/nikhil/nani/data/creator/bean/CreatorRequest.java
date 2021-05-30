package nikhil.nani.data.creator.bean;

import nikhil.nani.data.bean.RequestType;

public class CreatorRequest
{
    private String lhsFileName;
    private String rhsFileName;
    private RequestType creatorRequestType;
    private int numRecords;

    public CreatorRequest()
    {
    }

    public CreatorRequest(String lhsFileName, String rhsFileName, RequestType creatorRequestType, int numRecords)
    {
        this.lhsFileName = lhsFileName;
        this.rhsFileName = rhsFileName;
        this.creatorRequestType = creatorRequestType;
        this.numRecords = numRecords;
    }

    public String getLhsFileName()
    {
        return this.lhsFileName;
    }

    public void setLhsFileName(String lhsFileName)
    {
        this.lhsFileName = lhsFileName;
    }

    public String getRhsFileName()
    {
        return this.rhsFileName;
    }

    public void setRhsFileName(String rhsFileName)
    {
        this.rhsFileName = rhsFileName;
    }

    public RequestType getCreatorRequestType()
    {
        return this.creatorRequestType;
    }

    public void setCreatorRequestType(RequestType creatorRequestType)
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
        if (this.lhsFileName != null ? !this.lhsFileName.equals(that.lhsFileName) : that.lhsFileName != null)
        {
            return false;
        }
        if (this.rhsFileName != null ? !this.rhsFileName.equals(that.rhsFileName) : that.rhsFileName != null)
        {
            return false;
        }
        return this.creatorRequestType == that.creatorRequestType;
    }

    @Override
    public int hashCode()
    {
        int result = this.lhsFileName != null ? this.lhsFileName.hashCode() : 0;
        result = 31 * result + (this.rhsFileName != null ? this.rhsFileName.hashCode() : 0);
        result = 31 * result + (this.creatorRequestType != null ? this.creatorRequestType.hashCode() : 0);
        result = 31 * result + this.numRecords;
        return result;
    }
}
