// Shipping item with proper encapsulation
class ShippingItem implements Shippable {
    private String name;
    private double weight;

    public ShippingItem(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    // Implementing interface methods - must be public
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}