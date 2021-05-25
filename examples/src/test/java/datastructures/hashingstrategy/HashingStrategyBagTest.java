package datastructures.hashingstrategy;

import java.util.Arrays;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.factory.HashingStrategyBags;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashingStrategyBagTest
{
    private final String words = "one two Two three Three THREE four FOUR Four FoUr";

    @Test
    public void caseInsensitiveBag()
    {
        MutableBag<String> bagOfWords =
                HashingStrategyBags.mutable.<String>with(HashingStrategies.fromFunction(String::toLowerCase))
                        .withAll(Arrays.asList(this.words.split(" ")));

        Assertions.assertEquals(1, bagOfWords.occurrencesOf("one"));
        Assertions.assertEquals(2, bagOfWords.occurrencesOf("two"));
        Assertions.assertEquals(3, bagOfWords.occurrencesOf("three"));
        Assertions.assertEquals(4, bagOfWords.occurrencesOf("four"));
    }
}
