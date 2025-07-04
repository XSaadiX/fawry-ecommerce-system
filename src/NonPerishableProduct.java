/**
 * Represents a product that does not expire (e.g., electronics).
 */
public class NonPerishableProduct extends Product {
    private final boolean requiresShipping;
    private final double weight;

    public NonPerishableProduct(String name, double price, int quantity,
                                boolean requiresShipping, double weight) {
        super(name, price, quantity);
        this.requiresShipping = requiresShipping;
        this.weight = validateWeight(weight, requiresShipping);
    }

    private double validateWeight(double weight, boolean requiresShipping) {
        if (requiresShipping && weight <= 0) {
            throw new IllegalArgumentException("Shippable products must have positive weight");
        }
        return weight;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public boolean requiresShipping() {
        return requiresShipping;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}