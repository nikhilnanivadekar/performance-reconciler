package hiddentreasures.with;

import customer.Customer;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WithPredicatesTest
{
    private final ImmutableList<Customer> customers = Lists.immutable.with(
        new Customer("John", "E", "Smith"),
        new Customer("Sally", "C", "Smith"),
        new Customer("Ted", "G", "Williams"),
        new Customer("Mary", "D", "Travis")
    );

    @Test
    public void selectWithRejectWith()
    {
        ImmutableList<Customer> smiths =
                this.customers.select(customer -> customer.lastNameMatches("Smith"));
        ImmutableList<Customer> notSmiths =
                this.customers.reject(customer -> customer.lastNameMatches("Smith"));

        ImmutableList<Customer> withSmiths =
                this.customers.selectWith(Customer::lastNameMatches, "Smith");
        ImmutableList<Customer> notWithSmiths =
                this.customers.rejectWith(Customer::lastNameMatches, "Smith");

        Assertions.assertTrue(
                smiths.allSatisfy(customer -> customer.lastNameMatches("Smith")));
        Assertions.assertTrue(
                notSmiths.noneSatisfy(customer -> customer.lastNameMatches("Smith")));
        Assertions.assertTrue(
                withSmiths.allSatisfyWith(Customer::lastNameMatches, "Smith"));
        Assertions.assertTrue(
                notWithSmiths.noneSatisfyWith(Customer::lastNameMatches, "Smith"));
    }

    @Test
    public void rejectWith()
    {
        ImmutableList<Customer> notSmiths = this.customers.rejectWith(Customer::lastNameMatches, "Smith");

        Assertions.assertTrue(notSmiths.noneSatisfyWith(Customer::lastNameMatches, "Smith"));
    }

    @Test
    public void detectWith()
    {
        Customer mary = this.customers.detectWith(Customer::firstNameMatches, "Mary");

        Assertions.assertEquals("Travis", mary.getLastName());
    }

    @Test
    public void anySatisfyWith()
    {
        boolean anyMary = this.customers.anySatisfyWith(Customer::firstNameMatches, "Mary");

        Assertions.assertTrue(anyMary);
    }

    @Test
    public void allSatisfyWith()
    {
        boolean allBob = this.customers.allSatisfyWith(Customer::firstNameMatches, "Bob");

        Assertions.assertFalse(allBob);
    }

    @Test
    public void noneSatisfyWith()
    {
        boolean noneBob = this.customers.noneSatisfyWith(Customer::firstNameMatches, "Bob");

        Assertions.assertTrue(noneBob);
    }

    @Test
    public void countWith()
    {
        int count = this.customers.countWith(Customer::lastNameMatches, "Smith");

        Assertions.assertEquals(2, count);
    }
}
