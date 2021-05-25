package datastructures.multimap;

import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultimapTest
{
    private final String words = "The quick brown fox jumps over the lazy dog";

    @Test
    public void groupWordsByFirstCharacter()
    {
        Multimap<Character, String> multimap =
                Lists.mutable.with(this.words.split(" "))
                        .collect(String::toLowerCase)
                        .groupBy(each -> each.charAt(0));

        Assertions.assertEquals(Lists.mutable.with("the", "the"), multimap.get('t'));
        Assertions.assertEquals(Lists.mutable.empty(), multimap.get('a'));
    }

    @Test
    public void flip()
    {
        Multimap<String, Character> multimap =
                Lists.mutable.with(this.words.split(" "))
                        .collect(String::toLowerCase)
                        .groupBy(each -> each.charAt(0))
                        .flip();

        Assertions.assertEquals(Bags.mutable.with('t', 't'), multimap.get("the"));
        Assertions.assertEquals(Bags.mutable.empty(), multimap.get("and"));
    }
}
