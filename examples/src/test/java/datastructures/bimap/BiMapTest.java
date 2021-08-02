package datastructures.bimap;

import org.eclipse.collections.api.bimap.BiMap;
import org.eclipse.collections.api.bimap.ImmutableBiMap;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.BiMaps;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BiMapTest
{
    @Test
    public void forwardAndInverse()
    {
        BiMap<String, Integer> biMap = BiMaps.mutable.with("1", 1, "2", 2, "3", 3);
        BiMap<Integer, String> inverse = biMap.inverse();

        Assertions.assertEquals(1, biMap.get("1"));
        Assertions.assertEquals("1", inverse.get(1));

        Assertions.assertEquals(2, biMap.get("2"));
        Assertions.assertEquals("2", inverse.get(2));

        Assertions.assertEquals(3, biMap.get("3"));
        Assertions.assertEquals("3", inverse.get(3));
    }

    @Test
    public void converterMethods()
    {
        BiMap<String, Integer> biMap = BiMaps.mutable.with("1", 1, "2", 2, "3", 3);
        BiMap<Integer, String> inverse = biMap.inverse();

        ImmutableBiMap<String, Integer> immutableBiMap = biMap.toImmutable();
        ImmutableBiMap<Integer, String> immutableInverse = immutableBiMap.inverse();

        Assertions.assertEquals(biMap, immutableBiMap);
        Assertions.assertEquals(inverse, immutableInverse);

        Assertions.assertEquals(Lists.mutable.with(1, 2, 3), biMap.toSortedList());
        Assertions.assertEquals(Sets.mutable.with(1, 2, 3), biMap.toSet());
        Assertions.assertEquals(Bags.mutable.with(1, 2, 3), biMap.toBag());
        Assertions.assertEquals(Lists.immutable.with(1, 2, 3), biMap.toImmutableSortedList());
        Assertions.assertEquals(Sets.immutable.with(1, 2, 3), biMap.toImmutableSet());
        Assertions.assertEquals(Bags.immutable.with(1, 2, 3), biMap.toImmutableBag());

        Assertions.assertEquals(Lists.mutable.with("1", "2", "3"), inverse.toSortedList());
        Assertions.assertEquals(Sets.mutable.with("1", "2", "3"), inverse.toSet());
        Assertions.assertEquals(Bags.mutable.with("1", "2", "3"), inverse.toBag());
        Assertions.assertEquals(Lists.immutable.with("1", "2", "3"), inverse.toImmutableSortedList());
        Assertions.assertEquals(Sets.immutable.with("1", "2", "3"), inverse.toImmutableSet());
        Assertions.assertEquals(Bags.immutable.with("1", "2", "3"), inverse.toImmutableBag());
    }
}
