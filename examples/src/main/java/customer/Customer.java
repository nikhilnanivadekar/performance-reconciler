package customer;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;

public class Customer
{
    static final AtomicLong CUSTOMER_ID_GENERATOR = new AtomicLong(0);
    static final AtomicLong ORDER_ID_GENERATOR = new AtomicLong(0);

    private final long id;
    private final String lastName;
    private final String middleInitial;
    private final String firstName;
    private final MutableList<Order> orders = Lists.mutable.empty();

    public Customer(String firstName, String middleInitial, String lastName)
    {
        this(CUSTOMER_ID_GENERATOR.incrementAndGet(), firstName, middleInitial, lastName);
    }

    public Customer(long id, String firstName, String middleInitial, String lastName)
    {
        this.id = id;
        this.firstName = Objects.requireNonNull(firstName);
        this.middleInitial = Objects.requireNonNull(middleInitial);
        this.lastName = Objects.requireNonNull(lastName);
    }

    public long getId()
    {
        return this.id;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public String getMiddleInitial()
    {
        return this.middleInitial;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public boolean fullNameMatches(String name)
    {
        return name.equals(this.firstName + " " + this.middleInitial + " " + this.lastName);
    }

    public boolean lastNameMatches(String name)
    {
        return name.equals(this.lastName);
    }

    public boolean firstNameMatches(String name)
    {
        return name.equals(this.firstName);
    }

    public ListIterable<Order> getOrders()
    {
        return this.orders.asUnmodifiable();
    }

    public Order createOrder()
    {
        Order order = new Order(ORDER_ID_GENERATOR.incrementAndGet());
        this.orders.add(order);
        return order;
    }
}
