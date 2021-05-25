package datastructures.primitive;

import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.eclipse.collections.api.map.primitive.MutableObjectIntMap;
import org.eclipse.collections.impl.factory.primitive.IntObjectMaps;
import org.eclipse.collections.impl.factory.primitive.ObjectIntMaps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimitiveMapsTest
{
    @Test
    public void intObjectMap()
    {
        MutableIntObjectMap<String> map = IntObjectMaps.mutable.<String>empty()
                .withKeyValue(1, "One")
                .withKeyValue(2, "Two")
                .withKeyValue(3, "Three")
                .withKeyValue(4, "Four");

        Assertions.assertEquals("One", map.get(1));
        Assertions.assertNull(map.get(0));
        Assertions.assertEquals("Two", map.get(2));
        Assertions.assertEquals("Three", map.get(3));
        Assertions.assertEquals("Four", map.get(4));
    }

    @Test
    public void objectIntMap()
    {
        MutableObjectIntMap<String> map = ObjectIntMaps.mutable.<String>empty()
                .withKeyValue("One", 1)
                .withKeyValue("Two", 2)
                .withKeyValue("Three", 3)
                .withKeyValue("Four", 4);

        Assertions.assertEquals(1, map.get("One"));
        Assertions.assertEquals(0, map.get(null));
        Assertions.assertEquals(2, map.get("Two"));
        Assertions.assertEquals(3, map.get("Three"));
        Assertions.assertEquals(4, map.get("Four"));
    }

    @Test
    public void flipUniqueValues()
    {
        MutableIntObjectMap<String> map1 = IntObjectMaps.mutable.<String>empty()
                .withKeyValue(1, "One")
                .withKeyValue(2, "Two")
                .withKeyValue(3, "Three")
                .withKeyValue(4, "Four");

        MutableObjectIntMap<String> map2 = ObjectIntMaps.mutable.<String>empty()
                .withKeyValue("One", 1)
                .withKeyValue("Two", 2)
                .withKeyValue("Three", 3)
                .withKeyValue("Four", 4);

        Assertions.assertEquals(map2.flipUniqueValues(), map1);
        Assertions.assertEquals(map1.flipUniqueValues(), map2);
    }
}
