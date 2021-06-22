package datastructures.multimap;

import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultimapTest
{

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
