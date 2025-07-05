// Non-expirable products like TV and scratch cards
class NonExpirableProduct extends Product {
    boolean shippable;
    double weight;

    NonExpirableProduct(String name, double price, int quantity, boolean shippable, double weight) {
        super(name, price, quantity);
        this.shippable = shippable;
        this.weight = weight;
    }

    boolean isExpired() {
        return false; // Never expires
    }

    boolean needsShipping() {
        return shippable;
    }

    double getWeight() {
        return weight;
    }
}