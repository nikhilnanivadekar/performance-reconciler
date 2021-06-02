package hiddentreasures.top25methods;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.bag.primitive.MutableCharBag;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.list.ParallelListIterable;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.map.primitive.ImmutableObjectLongMap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.factory.Strings;
import org.eclipse.collections.impl.factory.primitive.CharBags;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MyTop25FavoriteECMethods
{
    private final ImmutableList<String> fruit =
            Lists.immutable.with("apple", "apricot", "banana", "blueberry", "clementine");
    private final ImmutableSet<String> onlyBanana =
            Sets.immutable.with("banana");

    @Test
    public void with()
    {
        MutableList<String> fruit =
                Lists.mutable.with("apple", "apricot", "banana", "blueberry", "clementine");

        List<String> expected =
                Arrays.asList("apple", "apricot", "banana", "blueberry", "clementine");

        Assertions.assertEquals(expected, fruit);
    }

    @Test
    public void collect()
    {
        ImmutableList<String> uppercase = this.fruit.collect(String::toUpperCase);

        List<String> expected =
                Arrays.asList("APPLE", "APRICOT", "BANANA", "BLUEBERRY", "CLEMENTINE");

        Assertions.assertEquals(expected, uppercase);
    }

    @Test
    public void of()
    {
        MutableSet<String> onlyBanana = Sets.mutable.of("banana");

        Set<String> expected = Collections.singleton("banana");

        Assertions.assertEquals(expected, onlyBanana);
    }

    @Test
    public void select()
    {
        ImmutableList<String> justBanana = this.fruit.select(this.onlyBanana::contains);

        List<String> expected = Arrays.asList("banana");

        Assertions.assertEquals(expected, justBanana);
    }

    @Test
    public void reject()
    {
        ImmutableList<String> notBanana = this.fruit.reject(this.onlyBanana::contains);

        List<String> expected = Arrays.asList("apple", "apricot", "blueberry", "clementine");

        Assertions.assertEquals(expected, notBanana);
    }

    @Test
    public void count()
    {
        int countBanana = this.fruit.count(this.onlyBanana::contains);

        Assertions.assertEquals(1, countBanana);
    }

    @Test
    public void anySatisfy()
    {
        boolean anyBanana = this.fruit.anySatisfy(this.onlyBanana::contains);

        Assertions.assertTrue(anyBanana);
    }

    @Test
    public void allSatisfy()
    {
        boolean allBanana = this.fruit.allSatisfy(this.onlyBanana::contains);

        Assertions.assertFalse(allBanana);
    }

    @Test
    public void noneSatisfy()
    {
        boolean noneBanana = this.fruit.allSatisfy(this.onlyBanana::contains);

        Assertions.assertFalse(noneBanana);
    }

    @Test
    public void groupBy()
    {
        ImmutableListMultimap<Character, String> multimap =
                this.fruit.groupBy(each -> each.charAt(0));

        Assertions.assertEquals(Arrays.asList("apple", "apricot"), multimap.get('a'));
        Assertions.assertEquals(Arrays.asList("banana", "blueberry"), multimap.get('b'));
        Assertions.assertEquals(Arrays.asList("clementine"), multimap.get('c'));
    }

    @Test
    public void countBy()
    {
        ImmutableBag<Character> firstLetterCounts =
                this.fruit.countBy(each -> each.charAt(0));

        Assertions.assertEquals(2, firstLetterCounts.occurrencesOf('a'));
        Assertions.assertEquals(2, firstLetterCounts.occurrencesOf('b'));
        Assertions.assertEquals(1, firstLetterCounts.occurrencesOf('c'));
    }

    @Test
    public void makeString()
    {
        String fruitString = this.fruit.makeString("(", ",", ")");

        Assertions.assertEquals("(apple,apricot,banana,blueberry,clementine)", fruitString);
    }

    @Test
    public void toImmutable()
    {
        MutableList<String> mutableFruit =
                Lists.mutable.with("apple", "apricot", "banana", "blueberry", "clementine");

        ImmutableList<String> immutableFruit = mutableFruit.toImmutable();

        Assertions.assertEquals(mutableFruit, immutableFruit);
    }

    @Test
    public void asLazy()
    {
        LazyIterable<String> lazyFruit = this.fruit.asLazy();

        Assertions.assertEquals(5, lazyFruit.size());
    }

    @Test
    public void containsBy()
    {
        boolean hasApple = this.fruit.containsBy(String::toUpperCase, "APPLE");

        Assertions.assertTrue(hasApple);
    }

    @Test
    public void detectWith()
    {
        String banana = this.fruit.detectWith(String::startsWith, "b");

        Assertions.assertEquals("banana", banana);
    }

    @Test
    public void detectWithIfNone()
    {
        String stillBanana =
                this.fruit.detectWithIfNone(String::startsWith, "d", () -> "banana");

        Assertions.assertEquals("banana", stillBanana);
    }

    @Test
    public void injectInto()
    {
        StringBuilder stringBuilder =
                this.fruit.injectInto(new StringBuilder(), StringBuilder::append);

        String mixedFruitString = stringBuilder.toString();

        Assertions.assertEquals("appleapricotbananablueberryclementine", mixedFruitString);
    }

    @Test
    public void partition()
    {
        PartitionImmutableList<String> partitionFruit =
                this.fruit.partition(each -> each.length() > 6);

        ImmutableList<String> selected = partitionFruit.getSelected();
        ImmutableList<String> rejected = partitionFruit.getRejected();

        Assertions.assertEquals(
                Lists.mutable.with("apricot", "blueberry", "clementine"), selected);
        Assertions.assertEquals(
                Lists.mutable.with("apple", "banana"), rejected);
    }

    @Test
    public void chunk()
    {
        RichIterable<RichIterable<String>> chunkFruit = this.fruit.chunk(2);

        Assertions.assertEquals(3, chunkFruit.size());
        Assertions.assertEquals(
                Lists.mutable.with("apple", "apricot"), chunkFruit.getFirst());
        Assertions.assertEquals(
                Lists.mutable.with("clementine"), chunkFruit.getLast());
    }

    @Test
    public void sumByInt()
    {
        ImmutableObjectLongMap<Character> sumLengthsByFirstCharacter =
                this.fruit.sumByInt(each -> each.charAt(0), String::length);

        Assertions.assertEquals(12, sumLengthsByFirstCharacter.get('a'));
        Assertions.assertEquals(15, sumLengthsByFirstCharacter.get('b'));
        Assertions.assertEquals(10, sumLengthsByFirstCharacter.get('c'));
    }

    @Test
    public void collectInt()
    {
        ImmutableIntList lengths = this.fruit.collectInt(String::length);

        ImmutableIntList expected = IntLists.immutable.with(5, 7, 6, 9, 10);

        Assertions.assertEquals(expected, lengths);
    }

    @Test
    public void flatCollectChar()
    {
        MutableCharBag charCounts =
                this.fruit.flatCollectChar(Strings::asChars, CharBags.mutable.empty());

        Assertions.assertEquals(5, charCounts.occurrencesOf('a'));
        Assertions.assertEquals(3, charCounts.occurrencesOf('b'));
        Assertions.assertEquals(2, charCounts.occurrencesOf('c'));
    }

    @Test
    public void asParallel()
    {
        ParallelListIterable<String> parallelFruit =
                this.fruit.asParallel(Executors.newFixedThreadPool(4), 1);

        ParallelListIterable<String> parallelSelect =
                parallelFruit.select(this.onlyBanana::contains);

        MutableList<String> onlyBanana = parallelSelect.toList();

        Assertions.assertEquals(Lists.mutable.with("banana"), onlyBanana);
    }

    @Test
    public void distinct()
    {
        MutableList<String> duplicateFruit =
                Lists.mutable.with("apple", "apple", "banana", "banana");

        MutableList<String> distinctFruit = duplicateFruit.distinct();

        Assertions.assertEquals(Lists.mutable.with("apple", "banana"), distinctFruit);
    }
}
