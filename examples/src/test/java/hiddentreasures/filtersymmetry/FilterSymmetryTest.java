package hiddentreasures.filtersymmetry;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.partition.list.PartitionMutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FilterSymmetryTest
{
    @Test
    public void inclusiveSelectExclusiveReject()
    {
        MutableList<Integer> list = Lists.mutable.with(1, 2, 3, 4, 5);
        MutableList<Integer> evens = list.select(each -> each % 2 == 0);
        MutableList<Integer> odds = list.reject(each -> each % 2 == 0);
        PartitionMutableList<Integer> partition = list.partition(each -> each % 2 == 0);

        MutableList<Integer> expectedEvens = Lists.mutable.with(2, 4);
        MutableList<Integer> expectedOdds = Lists.mutable.with(1, 3, 5);
        Assertions.assertEquals(expectedEvens, evens);
        Assertions.assertEquals(expectedOdds, odds);
        Assertions.assertEquals(expectedEvens, partition.getSelected());
        Assertions.assertEquals(expectedOdds, partition.getRejected());
    }
}
