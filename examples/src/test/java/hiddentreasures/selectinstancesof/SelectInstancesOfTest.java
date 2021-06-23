package hiddentreasures.selectinstancesof;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SelectInstancesOfTest
{
    @Test
    public void selectNumbers()
    {
        MutableList<Number> numbers = Lists.mutable.with(1, 2L, 3.0, 4.0f);
        MutableList<Integer> integers = numbers.selectInstancesOf(Integer.class);
        MutableList<Long> longs = numbers.selectInstancesOf(Long.class);
        MutableList<Double> doubles = numbers.selectInstancesOf(Double.class);
        MutableList<Float> floats = numbers.selectInstancesOf(Float.class);

        Assertions.assertEquals(Lists.mutable.with(1), integers);
        Assertions.assertEquals(Lists.mutable.with(2L), longs);
        Assertions.assertEquals(Lists.mutable.with(3.0), doubles);
        Assertions.assertEquals(Lists.mutable.with(4.0f), floats);
    }
}
