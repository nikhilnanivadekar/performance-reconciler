package nikhil.nani.data.bean;

public class Counter
{
    private int count;

    public Counter()
    {
    }

    public Counter(int count)
    {
        this.count = count;
    }

    public int getCount()
    {
        return this.count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public void increment()
    {
        this.count += 1;
    }

    @Override
    public String toString()
    {
        return "Counter:" + this.count;
    }
}
