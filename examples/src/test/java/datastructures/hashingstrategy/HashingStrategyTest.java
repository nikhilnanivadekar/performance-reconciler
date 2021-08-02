package datastructures.hashingstrategy;

import java.util.Arrays;
import java.util.List;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.set.Pool;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.factory.HashingStrategyBags;
import org.eclipse.collections.impl.factory.HashingStrategyMaps;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashingStrategyTest
{
    @Test
    public void caseInsensitivePool()
    {
        UnifiedSetWithHashingStrategy<String> caseInsensitiveSet =
                UnifiedSetWithHashingStrategy.newSet(HashingStrategies.fromFunction(String::toLowerCase));
        Pool<String> caseInsensitivePool = caseInsensitiveSet;

        Assertions.assertEquals("fox", caseInsensitivePool.put("fox"));
        Assertions.assertEquals("dog", caseInsensitivePool.put("dog"));
        Assertions.assertEquals("cat", caseInsensitivePool.put("cat"));

        Assertions.assertEquals("fox", caseInsensitivePool.put("fOx"));
        Assertions.assertEquals("dog", caseInsensitivePool.put("DoG"));
        Assertions.assertEquals("cat", caseInsensitivePool.put("CAT"));

        Assertions.assertFalse(caseInsensitiveSet.add("fOx"));
        Assertions.assertFalse(caseInsensitiveSet.add("DoG"));
        Assertions.assertFalse(caseInsensitiveSet.add("CAT"));

        Assertions.assertEquals("fox", caseInsensitivePool.get("FOX"));
        Assertions.assertEquals("dog", caseInsensitivePool.get("DOG"));
        Assertions.assertEquals("cat", caseInsensitivePool.get("CAT"));

        Assertions.assertEquals("fox", caseInsensitivePool.get("FoX"));
        Assertions.assertEquals("dog", caseInsensitivePool.get("dOg"));
        Assertions.assertEquals("cat", caseInsensitivePool.get("cat"));
    }

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

    @Test
    public void caseInsensitiveBag()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        List<String> list = Arrays.asList(words.split(" "));
        MutableBag<String> caseInsensitiveBag =
                HashingStrategyBags.mutable.<String>with(
                                HashingStrategies.fromFunction(String::toLowerCase))
                        .withAll(list);

        Assertions.assertEquals(1, caseInsensitiveBag.occurrencesOf("ONE"));
        Assertions.assertEquals(2, caseInsensitiveBag.occurrencesOf("two"));
        Assertions.assertEquals(3, caseInsensitiveBag.occurrencesOf("THREE"));
        Assertions.assertEquals(4, caseInsensitiveBag.occurrencesOf("four"));
    }
}
