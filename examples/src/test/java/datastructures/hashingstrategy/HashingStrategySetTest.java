package datastructures.hashingstrategy;

import java.util.HashSet;
import java.util.Set;

import customer.Customer;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.factory.HashingStrategySets;
import org.eclipse.collections.impl.set.strategy.mutable.UnifiedSetWithHashingStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashingStrategySetTest {
    @Test
    public void caseInsensitivePool() {
        UnifiedSetWithHashingStrategy<String> caseInsensitivePool =
                UnifiedSetWithHashingStrategy.newSet(HashingStrategies.fromFunction(String::toLowerCase));

        Assertions.assertTrue(caseInsensitivePool.add("one"));
        Assertions.assertTrue(caseInsensitivePool.add("two"));
        Assertions.assertTrue(caseInsensitivePool.add("three"));
        Assertions.assertFalse(caseInsensitivePool.add("oNe"));
        Assertions.assertFalse(caseInsensitivePool.add("TWO"));
        Assertions.assertFalse(caseInsensitivePool.add("ThReE"));

        Assertions.assertEquals("one", caseInsensitivePool.get("ONE"));
        Assertions.assertEquals("two", caseInsensitivePool.get("TWO"));
        Assertions.assertEquals("three", caseInsensitivePool.get("THREE"));

        Assertions.assertEquals("one", caseInsensitivePool.get("oNe"));
        Assertions.assertEquals("two", caseInsensitivePool.get("TwO"));
        Assertions.assertEquals("three", caseInsensitivePool.get("ThReE"));
    }

    @Test
    public void customerByName() {
        Set<Customer> jdkSet = new HashSet<>();

        Assertions.assertTrue(jdkSet.add(new Customer("Donald", "A", "Duck")));
        Assertions.assertTrue(jdkSet.add(new Customer("Mickey", "Mouse", "T")));
        Assertions.assertTrue(jdkSet.add(new Customer("Donald", "A", "Duck")));

        MutableSet<Customer> setByLastAndFirstName = HashingStrategySets.mutable.with(
                HashingStrategies.chain(
                        HashingStrategies.fromFunction(Customer::getLastName),
                        HashingStrategies.fromFunction(Customer::getFirstName)));

        Assertions.assertTrue(setByLastAndFirstName.add(new Customer("Donald", "A", "Duck")));
        Assertions.assertFalse(setByLastAndFirstName.add(new Customer("Donald", "A", "Duck")));
        Assertions.assertFalse(setByLastAndFirstName.add(new Customer("Donald", "B", "Duck")));
        Assertions.assertTrue(setByLastAndFirstName.add(new Customer("Daffy", "A", "Duck")));
        Assertions.assertTrue(setByLastAndFirstName.add(new Customer("Mickey", "T", "Mouse")));
    }

    @Test
    public void customerById()
    {
        Set<Customer> jdkSet = new HashSet<>();

        Assertions.assertTrue(jdkSet.add(new Customer("Donald", "A", "Duck")));
        Assertions.assertTrue(jdkSet.add(new Customer("Mickey", "Mouse", "T")));
        Assertions.assertTrue(jdkSet.add(new Customer("Donald", "A", "Duck")));

        MutableSet<Customer> setById = HashingStrategySets.mutable.with(
                HashingStrategies.fromLongFunction(Customer::getId));

        Customer donaldDuck = new Customer("Donald", "A", "Duck");
        Assertions.assertTrue(setById.add(donaldDuck));
        Assertions.assertTrue(setById.add(new Customer("Mickey", "Mouse", "T")));
        Assertions.assertFalse(setById.add(new Customer(donaldDuck.getId(), "Donald", "B", "Quack")));
    }
}
