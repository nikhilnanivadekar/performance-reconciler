package nikhil.nani.data.bean;

public class ReconcilerRequest
{
    private String pathFile1;
    private String pathFile2;
    private RequestType requestType;
    private ReconcilerModuleType reconcilerModuleType;
    private boolean ignoreDuplicates;

    public ReconcilerRequest()
    {
    }

    public ReconcilerRequest(String pathFile1, String pathFile2, RequestType requestType, ReconcilerModuleType reconcilerModuleType, boolean ignoreDuplicates)
    {
        this.pathFile1 = pathFile1;
        this.pathFile2 = pathFile2;
        this.requestType = requestType;
        this.reconcilerModuleType = reconcilerModuleType;
        this.ignoreDuplicates = ignoreDuplicates;
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

    public boolean isIgnoreDuplicates()
    {
        return this.ignoreDuplicates;
    }

    public void setIgnoreDuplicates(boolean ignoreDuplicates)
    {
        this.ignoreDuplicates = ignoreDuplicates;
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

        if (this.ignoreDuplicates != that.ignoreDuplicates)
        {
            return false;
        }
        if (this.pathFile1 != null ? !this.pathFile1.equals(that.pathFile1) : that.pathFile1 != null)
        {
            return false;
        }
        if (this.pathFile2 != null ? !this.pathFile2.equals(that.pathFile2) : that.pathFile2 != null)
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
        int result = this.pathFile1 != null ? this.pathFile1.hashCode() : 0;
        result = 31 * result + (this.pathFile2 != null ? this.pathFile2.hashCode() : 0);
        result = 31 * result + (this.requestType != null ? this.requestType.hashCode() : 0);
        result = 31 * result + (this.reconcilerModuleType != null ? this.reconcilerModuleType.hashCode() : 0);
        result = 31 * result + (this.ignoreDuplicates ? 1 : 0);
        return result;
    }
}
