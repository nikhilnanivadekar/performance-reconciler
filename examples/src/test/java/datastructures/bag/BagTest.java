package datastructures.bag;

import java.util.Arrays;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.primitive.CharBag;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.impl.collector.Collectors2;
import org.eclipse.collections.impl.factory.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BagTest
{

    private final String words = "one two Two three Three THREE four FOUR Four FoUr";

    @Test
    void wordCounter1()
    {
        Bag<String> bagOfWords = Lists.mutable.with(this.words.split(" "))
                .asLazy()
                .collect(String::toLowerCase)
                .toBag();

        Assertions.assertEquals(1, bagOfWords.occurrencesOf("one"));
        Assertions.assertEquals(2, bagOfWords.occurrencesOf("two"));
        Assertions.assertEquals(3, bagOfWords.occurrencesOf("three"));
        Assertions.assertEquals(4, bagOfWords.occurrencesOf("four"));
    }

    @Test
    void wordCounter2()
    {
        Bag<String> bagOfWords = Lists.mutable.with(this.words.split(" "))
                .countBy(String::toLowerCase);

        Assertions.assertEquals(1, bagOfWords.occurrencesOf("one"));
        Assertions.assertEquals(2, bagOfWords.occurrencesOf("two"));
        Assertions.assertEquals(3, bagOfWords.occurrencesOf("three"));
        Assertions.assertEquals(4, bagOfWords.occurrencesOf("four"));
    }

    @Test
    void wordCounter3()
    {
        Bag<String> bagOfWords = Arrays.stream(this.words.split(" "))
                .collect(Collectors2.countBy(String::toLowerCase));

        Assertions.assertEquals(1, bagOfWords.occurrencesOf("one"));
        Assertions.assertEquals(2, bagOfWords.occurrencesOf("two"));
        Assertions.assertEquals(3, bagOfWords.occurrencesOf("three"));
        Assertions.assertEquals(4, bagOfWords.occurrencesOf("four"));
    }

    @Test
    void characterCounter()
    {
        CharBag charBag = Strings.asChars(this.words)
                .select(Character::isAlphabetic)
                .collectChar(Character::toLowerCase)
                .toBag();

        Assertions.assertEquals(7, charBag.occurrencesOf('o'));
        Assertions.assertEquals(1, charBag.occurrencesOf('n'));
        Assertions.assertEquals(7, charBag.occurrencesOf('e'));
        Assertions.assertEquals(5, charBag.occurrencesOf('t'));
        Assertions.assertEquals(2, charBag.occurrencesOf('w'));
        Assertions.assertEquals(3, charBag.occurrencesOf('h'));
        Assertions.assertEquals(7, charBag.occurrencesOf('r'));
        Assertions.assertEquals(4, charBag.occurrencesOf('f'));
        Assertions.assertEquals(4, charBag.occurrencesOf('u'));
    }
}
