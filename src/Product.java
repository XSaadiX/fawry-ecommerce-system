// Simple Product class
abstract class Product {
    String name;
    double price;
    int quantity;

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }

    int getQuantity() {
        return quantity;
    }

    boolean hasStock(int needed) {
        return quantity >= needed;
    }

    void reduceStock(int amount) {
        quantity = quantity - amount;
    }

    abstract boolean isExpired();
    abstract boolean needsShipping();
    abstract double getWeight();
}