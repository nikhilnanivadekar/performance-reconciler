package datastructures.pool;

import customer.Customer;
import org.eclipse.collections.api.set.Pool;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PoolTest
{
    @Test
    public void stringPool()
    {
        Pool<String> pool = UnifiedSet.newSet();
        String a1 = pool.put(new String("a"));
        String a2 = pool.put(new String("a"));
        String b1 = pool.put(new String("b"));
        String b2 = pool.put(new String("b"));

        Assertions.assertNotNull(a1);
        Assertions.assertNotEquals(a1, b1);
        Assertions.assertSame(a1, a2);
        Assertions.assertSame(b1, b2);
    }

    @Test
    void hashingStrategyPool()
    {
        Pool<Customer> poolById =
                UnifiedSetWithHashingStrategy.newSet(HashingStrategies.fromLongFunction(Customer::getId));

        Customer donaldDuck = new Customer("Donald", "A", "Duck");
        Customer donaldQuack = new Customer(donaldDuck.getId(), "Donald", "B", "Quack");
        Assertions.assertSame(donaldDuck, poolById.put(donaldDuck));
        Assertions.assertNotNull(poolById.put(new Customer("Mickey", "Mouse", "T")));
        Assertions.assertSame(donaldDuck, poolById.put(donaldQuack));
        Assertions.assertSame(donaldDuck, poolById.get(new Customer(donaldDuck.getId(), "", "", "")));
    }
}
