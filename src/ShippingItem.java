// Shipping item that implements the Shippable interface
class ShippingItem implements Shippable {
    String name;
    double weight;

    ShippingItem(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
}