package datastructures.hashingstrategy;

import java.util.Arrays;
import java.util.List;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.factory.HashingStrategyBags;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashingStrategyBagTest
{

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
