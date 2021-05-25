package datastructures.primitive;

import java.util.IntSummaryStatistics;

import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntIntervalTest
{
    @Test
    public void math()
    {
        IntInterval interval = IntInterval.oneTo(5);
        Assertions.assertEquals(15L, interval.sum());
        Assertions.assertEquals(1, interval.min());
        Assertions.assertEquals(5, interval.max());
        Assertions.assertEquals(3.0d, interval.average());
        Assertions.assertEquals(3, interval.median());
        Assertions.assertEquals(35, interval.dotProduct(IntInterval.fromTo(5, 1)));
        Assertions.assertEquals(5, interval.size());

        IntSummaryStatistics stats = interval.summaryStatistics();
        Assertions.assertEquals(15L, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(5, stats.getMax());
        Assertions.assertEquals(3.0d, stats.getAverage());
        Assertions.assertEquals(5, stats.getCount());
    }
}
