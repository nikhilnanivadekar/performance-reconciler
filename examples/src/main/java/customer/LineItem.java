package customer;

public class LineItem
{
    private final Order order;
    private final Product product;
    private final int quantity;

    public LineItem(Order order, Product product, int quantity)
    {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public Order getOrder()
    {
        return order;
    }

    public Product getProduct()
    {
        return product;
    }

    public int getQuantity()
    {
        return quantity;
    }
}
