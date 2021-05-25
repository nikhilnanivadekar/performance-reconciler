package datastructures.hashingstrategy;

import java.util.HashSet;
import java.util.Set;

import customer.Customer;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.factory.HashingStrategySets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HashingStrategySetTest
{
    @Test
    public void customerByName()
    {
        Set<Customer> jdkSet = new HashSet<>();

        Assertions.assertTrue(jdkSet.add(new Customer("Donald", "A", "Duck")));
        Assertions.assertTrue(jdkSet.add(new Customer("Mickey", "Mouse", "T")));
        Assertions.assertTrue(jdkSet.add(new Customer("Donald", "A", "Duck")));

        MutableSet<Customer> setByName = HashingStrategySets.mutable.with(
                HashingStrategies.chain(
                        HashingStrategies.fromFunction(Customer::getLastName),
                        HashingStrategies.fromFunction(Customer::getFirstName),
                        HashingStrategies.fromFunction(Customer::getMiddleInitial)));

        Assertions.assertTrue(setByName.add(new Customer("Donald", "A", "Duck")));
        Assertions.assertTrue(setByName.add(new Customer("Mickey", "Mouse", "T")));
        Assertions.assertFalse(setByName.add(new Customer("Donald", "A", "Duck")));
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
