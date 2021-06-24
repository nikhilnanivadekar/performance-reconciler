package hiddentreasures.zip;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZipTest
{
    @Test
    public void zip()
    {
        MutableList<Integer> list1 = Lists.mutable.with(1, 2, 3);
        MutableList<Integer> list2 = Lists.mutable.with(0, 1, 2, 0);
        MutableList<Pair<Integer, Integer>> zip = list1.zip(list2);

        Assertions.assertEquals(Tuples.pair(1, 0), zip.getFirst());
        Assertions.assertEquals(Tuples.pair(2, 1), zip.get(1));
        Assertions.assertEquals(Tuples.pair(3, 2), zip.getLast());
        Assertions.assertEquals(zip, list1.zipWithIndex());
    }
}
