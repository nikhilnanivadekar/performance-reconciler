package hiddentreasures.by;

import customer.Customer;
import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.factory.Multimaps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ByFunctionsTest
{
    private final ImmutableList<Customer> customers = Lists.immutable.with(
            new Customer("John", "E", "Smith"),
            new Customer("Sally", "C", "Smith"),
            new Customer("Ted", "G", "Williams"),
            new Customer("Mary", "D", "Travis")
    );

    @Test
    public void groupBy()
    {
        ImmutableListMultimap<String, Customer> customersByLastName =
                this.customers.groupBy(Customer::getLastName);

        MutableListMultimap<Object, Object> expected = Multimaps.mutable.list.empty();
        expected.putAll("Smith", Lists.mutable.with(this.customers.get(0), this.customers.get(1)));
        expected.put("Williams", this.customers.get(2));
        expected.put("Travis", this.customers.get(3));
        Assertions.assertEquals(expected, customersByLastName);
    }

    @Test
    public void countBy()
    {
        ImmutableBag<String> lastNames = this.customers.countBy(Customer::getLastName);

        ImmutableBag<String> expected = Bags.immutable.with("Smith", "Smith", "Williams", "Travis");
        Assertions.assertEquals(expected, lastNames);

        Assertions.assertEquals("Smith", lastNames.topOccurrences(1).getFirst().getOne());
        Assertions.assertEquals(2, lastNames.bottomOccurrences(1).size());
    }

    @Test
    public void containsBy()
    {
        boolean hasSmith = this.customers.containsBy(Customer::getLastName, "Smith");
        boolean hasTodd = this.customers.containsBy(Customer::getFirstName, "Todd");

        Assertions.assertTrue(hasSmith);
        Assertions.assertFalse(hasTodd);
    }

}
