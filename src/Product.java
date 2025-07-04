import java.util.Objects;

/**
 * Abstract base class representing a product in the e-commerce system.
 */
public abstract class Product {
    private final String name;
    private final double price;
    private int quantity;

    protected Product(String name, double price, int quantity) {
        validateProductData(name, price, quantity);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private void validateProductData(String name, double price, int quantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public boolean isAvailable(int requestedQuantity) {
        return requestedQuantity > 0 && quantity >= requestedQuantity;
    }

    public void reduceQuantity(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Reduction amount must be positive");
        }
        if (amount > quantity) {
            throw new IllegalArgumentException("Cannot reduce quantity below zero");
        }
        this.quantity -= amount;
    }

    public abstract boolean isExpired();
    public abstract boolean requiresShipping();
    public abstract double getWeight();

    @Override
    public String toString() {
        return String.format("%s{name='%s', price=%.2f, quantity=%d}",
                getClass().getSimpleName(), name, price, quantity);
    }
}