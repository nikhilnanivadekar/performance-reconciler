package datastructures.hashingstrategy;

import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.factory.HashingStrategyMaps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashingStrategyMapTest
{
    @Test
    public void caseInsensitiveMap()
    {
        MutableMap<String, String> caseInsensitiveMap =
                HashingStrategyMaps.mutable.<String, String>with(
                        HashingStrategies.fromFunction(String::toLowerCase))
                        .withKeyValue("one", "1")
                        .withKeyValue("Two", "2")
                        .withKeyValue("THREE", "3");

        Assertions.assertEquals("1", caseInsensitiveMap.get("ONE"));
        Assertions.assertEquals("2", caseInsensitiveMap.get("tWO"));
        Assertions.assertEquals("3", caseInsensitiveMap.get("three"));
    }
}
