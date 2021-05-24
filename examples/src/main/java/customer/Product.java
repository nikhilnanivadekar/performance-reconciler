package customer;

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.set.ImmutableSet;

public enum Product
{
    PEN("12345"),
    PAPER("54321"),
    PENCIL("23145"),
    SHARPENER("32154"),
    MARKER("51234"),
    ERASER("43215"),
    BINDER("34512");
    private String identifier;

    public static ImmutableSet<Product> ALL = Sets.immutable.with(Product.values());

    Product(String identifier)
    {
        this.identifier = identifier;
    }

    public static Product from(String id)
    {
        return Product.ALL.detect(product -> product.identifier.equals(id));
    }
}
