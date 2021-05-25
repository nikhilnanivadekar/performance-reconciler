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
        String one = pool.put(new String("a"));
        String two = pool.put(new String("a"));

        Assertions.assertNotNull(one);
        Assertions.assertSame(one, two);
    }

    @Test
    void hashingStrategyPool()
    {
        Pool<Customer> poolById = UnifiedSetWithHashingStrategy.newSet(
                HashingStrategies.fromLongFunction(Customer::getId));

        Customer donaldDuck = new Customer("Donald", "A", "Duck");
        Assertions.assertSame(donaldDuck, poolById.put(donaldDuck));
        Assertions.assertNotNull(poolById.put(new Customer("Mickey", "Mouse", "T")));
        Assertions.assertSame(donaldDuck, poolById.put(new Customer(donaldDuck.getId(), "Donald", "B", "Quack")));
    }
}
