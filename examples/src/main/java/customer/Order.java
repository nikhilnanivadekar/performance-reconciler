package customer;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;

public class Order
{
    private final long id;
    private final MutableList<LineItem> lineItems = Lists.mutable.empty();

    public Order(long id)
    {
        this.id = id;
    }

    public void addLineItem(Product product, int quantity)
    {
        this.lineItems.add(new LineItem(this, product, quantity));
    }

    public long getId()
    {
        return this.id;
    }

    public ListIterable<LineItem> getLineItems()
    {
        return this.lineItems.asUnmodifiable();
    }
}
