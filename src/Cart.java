import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a shopping cart containing products.
 */
public class Cart {
    private final List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(Product product, int quantity) throws ECommerceException {
        Objects.requireNonNull(product, "Product cannot be null");

        if (quantity <= 0) {
            throw new ECommerceException("Quantity must be positive");
        }

        if (!product.isAvailable(quantity)) {
            throw new ECommerceException("Insufficient stock for " + product.getName() +
                    ". Available: " + product.getQuantity() + ", Requested: " + quantity);
        }

        if (product.isExpired()) {
            throw new ECommerceException("Product " + product.getName() + " is expired");
        }

        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        return items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        return String.format("Cart{items=%d, subtotal=%.2f}", items.size(), getSubtotal());
    }
}