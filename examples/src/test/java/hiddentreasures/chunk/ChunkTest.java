package hiddentreasures.chunk;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.impl.list.primitive.IntInterval;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChunkTest
{
    @Test
    public void chunk()
    {
        IntInterval ints = IntInterval.oneTo(10);
        RichIterable<IntIterable> chunks = ints.chunk(3);
        LazyIterable<IntIterable> lazy = chunks.asLazy();
        Assertions.assertEquals(IntInterval.oneTo(3), lazy.getFirst());
        Assertions.assertEquals(IntInterval.fromTo(4, 6), lazy.drop(1).getFirst());
        Assertions.assertEquals(IntInterval.fromTo(7, 9), lazy.drop(2).getFirst());
        Assertions.assertEquals(IntInterval.fromTo(10, 10), lazy.drop(3).getFirst());
    }
}
