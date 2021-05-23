package nikhil.nani.data.bean;

import java.util.Collection;
import java.util.List;

public class Breaks<T>
{
    private final List<T> presentInLhsNotInRhs;
    private final List<T> presentInRhsNotInLhs;
    private final List<List<T>> breaks;

    public Breaks(List<T> presentInLhsNotInRhs, List<T> presentInRhsNotInLhs, List<List<T>> breaks)
    {
        this.presentInLhsNotInRhs = presentInLhsNotInRhs;
        this.presentInRhsNotInLhs = presentInRhsNotInLhs;
        this.breaks = breaks;
    }

    public List<T> getPresentInLhsNotInRhs()
    {
        return this.presentInLhsNotInRhs;
    }

    public List<T> getPresentInRhsNotInLhs()
    {
        return this.presentInRhsNotInLhs;
    }

    public List<List<T>> getBreaks()
    {
        return this.breaks;
    }

    public void addToPresentInLhsNotInRhs(T obj)
    {
        this.presentInLhsNotInRhs.add(obj);
    }

    public void addAllToPresentInLhsNotInRhs(Collection<T> objs)
    {
        this.presentInLhsNotInRhs.addAll(objs);
    }

    public void addToPresentInRhsNotInLhs(T obj)
    {
        this.presentInRhsNotInLhs.add(obj);
    }

    public void addAllToPresentInRhsNotInLhs(Collection<T> objs)
    {
        this.presentInRhsNotInLhs.addAll(objs);
    }

    public void addToBreaks(List<T> breaks)
    {
        this.breaks.add(breaks);
    }
}
