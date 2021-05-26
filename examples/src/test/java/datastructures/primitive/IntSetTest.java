package datastructures.primitive;

import java.util.IntSummaryStatistics;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.set.primitive.IntSet;
import org.eclipse.collections.api.tuple.primitive.IntIntPair;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntSetTest
{
    @Test
    public void basicMath()
    {
        IntSet set = IntInterval.oneTo(5).toSet();
        Assertions.assertEquals(15L, set.sum());
        Assertions.assertEquals(1, set.min());
        Assertions.assertEquals(1, set.minIfEmpty(0));
        Assertions.assertEquals(5, set.max());
        Assertions.assertEquals(5, set.maxIfEmpty(0));
        Assertions.assertEquals(3.0d, set.average());
        Assertions.assertEquals(3.0d, set.averageIfEmpty(0.0d));
        Assertions.assertEquals(3, set.median());
        Assertions.assertEquals(5, set.size());

        IntSummaryStatistics stats = set.summaryStatistics();
        Assertions.assertEquals(15L, stats.getSum());
        Assertions.assertEquals(1, stats.getMin());
        Assertions.assertEquals(5, stats.getMax());
        Assertions.assertEquals(3.0d, stats.getAverage());
        Assertions.assertEquals(5, stats.getCount());
    }

    @Test
    public void setMath()
    {
        IntSet set1 = IntSets.mutable.with(1, 2, 3);
        IntSet set2 = IntSets.mutable.with(2, 3, 4);
        IntSet intersect = set1.intersect(set2);
        IntSet union = set1.union(set2);
        IntSet difference = set1.difference(set2);
        IntSet symmetricDifference = set1.symmetricDifference(set2);
        Bag<IntIntPair> cartesianProduct = set1.cartesianProduct(set2).toBag();

        Assertions.assertEquals(IntSets.mutable.with(2, 3), intersect);
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 3, 4), union);
        Assertions.assertEquals(IntSets.mutable.with(1), difference);
        Assertions.assertEquals(IntSets.mutable.with(1, 4), symmetricDifference);
        ImmutableBag<IntIntPair> expected = Bags.immutable.with(
                PrimitiveTuples.pair(1, 2),
                PrimitiveTuples.pair(2, 2),
                PrimitiveTuples.pair(3, 2),
                PrimitiveTuples.pair(1, 3),
                PrimitiveTuples.pair(2, 3),
                PrimitiveTuples.pair(3, 3),
                PrimitiveTuples.pair(1, 4),
                PrimitiveTuples.pair(2, 4),
                PrimitiveTuples.pair(3, 4)
        );
        Assertions.assertEquals(expected, cartesianProduct);
    }
}
