package datastructures.primitive;

import java.util.IntSummaryStatistics;

import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.api.stack.primitive.IntStack;
import org.eclipse.collections.impl.factory.primitive.IntStacks;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntStackTest
{
    @Test
    public void math()
    {
        IntStack stack = IntStacks.mutable.withAll(IntInterval.oneTo(5));
        Assertions.assertEquals(15L, stack.sum());
        Assertions.assertEquals(1, stack.min());
        Assertions.assertEquals(1, stack.minIfEmpty(0));
        Assertions.assertEquals(5, stack.max());
        Assertions.assertEquals(5, stack.maxIfEmpty(0));
        Assertions.assertEquals(3.0d, stack.average());
        Assertions.assertEquals(3.0d, stack.averageIfEmpty(0.0d));
        Assertions.assertEquals(3, stack.median());
        Assertions.assertEquals(5, stack.size());

        IntSummaryStatistics stats = stack.summaryStatistics();
        Assertions.assertEquals(15L, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(5, stats.getMax());
        Assertions.assertEquals(3.0d, stats.getAverage());
        Assertions.assertEquals(5, stats.getCount());
    }

    @Test
    public void peek()
    {
        IntStack stack = IntStacks.mutable.withAll(IntInterval.oneTo(5));
        Assertions.assertEquals(5, stack.peek());
        Assertions.assertEquals(IntInterval.fromTo(5, 1), stack.peek(5));
    }
}
