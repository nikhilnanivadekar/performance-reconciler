package datastructures.multimap;

import java.util.Comparator;

import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.Multimap;
import org.eclipse.collections.api.multimap.bag.ImmutableBagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.ImmutableSetMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.sortedbag.ImmutableSortedBagMultimap;
import org.eclipse.collections.api.multimap.sortedbag.MutableSortedBagMultimap;
import org.eclipse.collections.api.multimap.sortedset.ImmutableSortedSetMultimap;
import org.eclipse.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import org.eclipse.collections.impl.factory.Multimaps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultimapTest
{
    @Test
    public void multimapsFactoryExamples()
    {
        MutableListMultimap<String, String> mutableListMultimap =
                Multimaps.mutable.list.empty();
        MutableSetMultimap<String, String> mutableSetMultimap =
                Multimaps.mutable.set.empty();
        MutableBagMultimap<String, String> mutableBagMultimap =
                Multimaps.mutable.bag.empty();
        MutableSortedSetMultimap<String, String> mutableSortedSetMultimap =
                Multimaps.mutable.sortedSet.with(Comparator.<String>reverseOrder());
        MutableSortedBagMultimap<String, String> mutableSortedBagMultimap =
                Multimaps.mutable.sortedBag.with(Comparator.<String>reverseOrder());

        ImmutableListMultimap<String, String> immutableListMultimap =
                Multimaps.immutable.list.empty();
        ImmutableSetMultimap<String, String> immutableSetMultimap =
                Multimaps.immutable.set.empty();
        ImmutableBagMultimap<String, String> immutableBagMultimap =
                Multimaps.immutable.bag.empty();
        ImmutableSortedSetMultimap<String, String> immutableSortedSetMultimap =
                Multimaps.immutable.sortedSet.with(Comparator.<String>reverseOrder());
        ImmutableSortedBagMultimap<String, String> immutableSortedBagMultimap =
                Multimaps.immutable.sortedBag.with(Comparator.<String>reverseOrder());

        Assertions.assertEquals(mutableListMultimap, immutableListMultimap);
        Assertions.assertEquals(mutableSetMultimap, immutableSetMultimap);
        Assertions.assertEquals(mutableBagMultimap, immutableBagMultimap);
        Assertions.assertEquals(mutableSortedSetMultimap, immutableSortedSetMultimap);
        Assertions.assertEquals(mutableSortedBagMultimap, immutableSortedBagMultimap);
    }

    @Test
    public void groupWordsByFirstCharacter()
    {
        String words = "The quick brown fox jumps over the lazy dog";
        MutableList<String> list = Lists.mutable.with(words.split(" "));
        Multimap<Character, String> multimap = list.collect(String::toLowerCase)
                .groupBy(each -> each.charAt(0));

        Assertions.assertEquals(Lists.mutable.with("the", "the"), multimap.get('t'));
        Assertions.assertEquals(Lists.mutable.empty(), multimap.get('a'));
    }

    @Test
    public void groupAndCollectWordsByLowercaseFirstCharacter()
    {
        String words = "The quick brown fox jumps over the lazy dog";
        MutableList<String> list = Lists.mutable.with(words.split(" "));
        Multimap<Character, String> multimap =
                list.groupByAndCollect(
                        each -> Character.toLowerCase(each.charAt(0)),
                        String::toLowerCase,
                        Multimaps.mutable.list.empty());

        Assertions.assertEquals(Lists.mutable.with("the", "the"), multimap.get('t'));
        Assertions.assertEquals(Lists.mutable.empty(), multimap.get('a'));
    }

    @Test
    public void flip()
    {
        String words = "The quick brown fox jumps over the lazy dog";
        MutableList<String> list = Lists.mutable.with(words.split(" "));
        Multimap<String, Character> multimap = list.collect(String::toLowerCase)
                .groupBy(each -> each.charAt(0))
                .flip();

        Assertions.assertEquals(Bags.mutable.with('t', 't'), multimap.get("the"));
        Assertions.assertEquals(Bags.mutable.empty(), multimap.get("and"));
    }
}
