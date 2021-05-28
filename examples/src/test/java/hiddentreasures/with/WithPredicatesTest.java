package hiddentreasures.with;

import customer.Customer;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WithPredicatesTest
{
    private static final ImmutableList<Integer> INTEGERS = Lists.immutable.with(1, 2, 3, 4, 5);

    private ImmutableList<Customer> customers = Lists.immutable.with(
        new Customer("John", "E", "Smith"),
        new Customer("Sally", "C", "Smith"),
        new Customer("Ted", "G", "Williams"),
        new Customer("Mary", "D", "Travis")
    );

    @Test
    public void selectWith()
    {
        ImmutableList<Customer> smiths =
                this.customers.selectWith(Customer::lastNameMatches, "Smith");

        Assertions.assertTrue(smiths.allSatisfyWith(Customer::lastNameMatches, "Smith"));
    }

    @Test
    public void rejectWith()
    {
        ImmutableList<Customer> notSmiths =
                this.customers.rejectWith(Customer::lastNameMatches, "Smith");

        Assertions.assertTrue(notSmiths.noneSatisfyWith(Customer::lastNameMatches, "Smith"));
    }

    @Test
    public void detectWith()
    {
        Customer firstNameMary =
                this.customers.detectWith(Customer::firstNameMatches, "Mary");

        Assertions.assertEquals("Travis", firstNameMary.getLastName());
    }

}
