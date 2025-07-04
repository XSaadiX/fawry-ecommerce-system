import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a product that can expire
 */
public class PerishableProduct extends Product {
    private final LocalDate expirationDate;
    private final boolean requiresShipping;
    private final double weight;

    public PerishableProduct(String name, double price, int quantity,
                             LocalDate expirationDate, boolean requiresShipping, double weight) {
        super(name, price, quantity);
        this.expirationDate = Objects.requireNonNull(expirationDate, "Expiration date cannot be null");
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
        return LocalDate.now().isAfter(expirationDate);
    }

    @Override
    public boolean requiresShipping() {
        return requiresShipping;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}