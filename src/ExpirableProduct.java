// Expirable products like cheese and biscuits
class ExpirableProduct extends Product {
    boolean expired;
    boolean shippable;
    double weight;

    ExpirableProduct(String name, double price, int quantity, boolean expired, boolean shippable, double weight) {
        super(name, price, quantity);
        this.expired = expired;
        this.shippable = shippable;
        this.weight = weight;
    }

    boolean isExpired() {
        return expired;
    }

    boolean needsShipping() {
        return shippable;
    }

    double getWeight() {
        return weight;
    }
}