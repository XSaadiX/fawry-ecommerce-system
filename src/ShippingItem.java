import java.util.Objects;

/**
 * Implementation of Shippable interface for shipping service.
 */
public class ShippingItem implements Shippable {
    private final String name;
    private final double weight;

    public ShippingItem(String name, double weight) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("ShippingItem{name='%s', weight=%.2f}", name, weight);
    }
}