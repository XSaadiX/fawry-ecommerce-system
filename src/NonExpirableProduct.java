// Non-expirable products with proper encapsulation
class NonExpirableProduct extends Product {
    private boolean shippable;
    private double weight;

    public NonExpirableProduct(String name, double price, int quantity, boolean shippable, double weight) {
        super(name, price, quantity);
        this.shippable = shippable;
        this.weight = weight;
    }

    // Getters for private fields
    public boolean getShippable() {
        return shippable;
    }

    // Implementing abstract methods
    @Override
    public boolean isExpired() {
        return false; // Never expires
    }

    @Override
    public boolean needsShipping() {
        return shippable;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}