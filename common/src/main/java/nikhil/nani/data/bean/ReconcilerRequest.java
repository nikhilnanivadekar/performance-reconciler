package nikhil.nani.data.bean;

public class ReconcilerRequest
{
    private String pathFile1;
    private String pathFile2;
    private RequestType requestType;
    private ReconcilerModuleType reconcilerModuleType;

    public ReconcilerRequest()
    {
    }

    public ReconcilerRequest(String pathFile1, String pathFile2, RequestType requestType, ReconcilerModuleType reconcilerModuleType)
    {
        this.pathFile1 = pathFile1;
        this.pathFile2 = pathFile2;
        this.requestType = requestType;
        this.reconcilerModuleType = reconcilerModuleType;
    }

    public String getPathFile1()
    {
        return this.pathFile1;
    }

    public void setPathFile1(String pathFile1)
    {
        this.pathFile1 = pathFile1;
    }

    public String getPathFile2()
    {
        return this.pathFile2;
    }

    public void setPathFile2(String pathFile2)
    {
        this.pathFile2 = pathFile2;
    }

    public RequestType getRequestType()
    {
        return this.requestType;
    }

    public void setRequestType(RequestType requestType)
    {
        this.requestType = requestType;
    }

    public ReconcilerModuleType getReconcilerModuleType()
    {
        return this.reconcilerModuleType;
    }

    public void setReconcilerModuleType(ReconcilerModuleType reconcilerModuleType)
    {
        this.reconcilerModuleType = reconcilerModuleType;
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

        ReconcilerRequest that = (ReconcilerRequest) o;

        if (!this.pathFile1.equals(that.pathFile1))
        {
            return false;
        }
        if (!this.pathFile2.equals(that.pathFile2))
        {
            return false;
        }
        if (this.requestType != that.requestType)
        {
            return false;
        }
        return this.reconcilerModuleType == that.reconcilerModuleType;
    }

    @Override
    public int hashCode()
    {
        int result = this.pathFile1.hashCode();
        result = 31 * result + this.pathFile2.hashCode();
        result = 31 * result + this.requestType.hashCode();
        result = 31 * result + this.reconcilerModuleType.hashCode();
        return result;
    }
}
