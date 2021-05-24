package customer;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;

public class Customer
{
    static final AtomicLong ORDER_ID_GENERATOR = new AtomicLong(0);

    private final String lastName;
    private final String middleInitial;
    private final String firstName;
    private final MutableList<Order> orders = Lists.mutable.empty();

    public Customer(String lastName, String middleInitial, String firstName)
    {
        this.lastName = Objects.requireNonNull(lastName);
        this.middleInitial = Objects.requireNonNull(middleInitial);
        this.firstName = Objects.requireNonNull(firstName);
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
