package datastructures.bag;

import java.util.Arrays;
import java.util.stream.Stream;

import org.eclipse.collections.api.bag.Bag;
import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.bag.primitive.CharBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.impl.collector.Collectors2;
import org.eclipse.collections.impl.factory.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BagTest
{
    @Test
    void wordCounter1()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        MutableBag<String> bag = Bags.mutable.with(words.split(" "));
        Bag<String> bagOfWords = bag.asLazy()
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
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        MutableBag<String> bag = Bags.mutable.with(words.split(" "));
        Bag<String> lowercaseWords = bag.countBy(String::toLowerCase);

        Assertions.assertEquals(1, lowercaseWords.occurrencesOf("one"));
        Assertions.assertEquals(2, lowercaseWords.occurrencesOf("two"));
        Assertions.assertEquals(3, lowercaseWords.occurrencesOf("three"));
        Assertions.assertEquals(4, lowercaseWords.occurrencesOf("four"));
    }

    @Test
    void wordCounter3()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        Stream<String> stream = Arrays.stream(words.split(" "));
        Bag<String> bagOfWords = stream
                .collect(Collectors2.countBy(String::toLowerCase));

        Assertions.assertEquals(1, bagOfWords.occurrencesOf("one"));
        Assertions.assertEquals(2, bagOfWords.occurrencesOf("two"));
        Assertions.assertEquals(3, bagOfWords.occurrencesOf("three"));
        Assertions.assertEquals(4, bagOfWords.occurrencesOf("four"));
    }

    @Test
    void characterCounter()
    {
        String words = "one two Two three Three THREE four FOUR Four FoUr";
        CharBag charBag = Strings.asChars(words)
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
