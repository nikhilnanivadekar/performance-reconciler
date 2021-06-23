package hiddentreasures.setoperations;

import org.eclipse.collections.api.set.primitive.IntSet;
import org.eclipse.collections.api.set.primitive.MutableIntSet;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.eclipse.collections.impl.factory.primitive.IntSets;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimitiveSetOperationsTest
{
    @Test
    public void eager()
    {
        IntSet setA = IntSets.mutable.with(1, 2, 3, 4);
        IntSet setB = IntSets.mutable.with(3, 4, 5, 6);
        IntSet intersect = setA.intersect(setB);
        IntSet union = setA.union(setB);
        IntSet difference = setA.difference(setB);
        IntSet symmetricDifference = setA.symmetricDifference(setB);

        Assertions.assertEquals(IntSets.mutable.with(3, 4), intersect);
        Assertions.assertEquals(IntInterval.oneTo(6).toSet(), union);
        Assertions.assertEquals(IntSets.mutable.with(1, 2), difference);
        Assertions.assertEquals(IntSets.mutable.with(1, 2, 5, 6), symmetricDifference);
    }
}
