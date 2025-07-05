// Expirable products with proper encapsulation
class ExpirableProduct extends Product {
    private boolean expired;
    private boolean shippable;
    private double weight;

    public ExpirableProduct(String name, double price, int quantity, boolean expired, boolean shippable, double weight) {
        super(name, price, quantity);
        this.expired = expired;
        this.shippable = shippable;
        this.weight = weight;
    }

    // Getters for private fields
    public boolean getExpired() {
        return expired;
    }

    public boolean getShippable() {
        return shippable;
    }

    // Implementing abstract methods
    @Override
    public boolean isExpired() {
        return expired;
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