// Product class with proper encapsulation
abstract class Product {
    private String name;
    private double price;
    private int quantity;

    protected Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters - public methods to access private data
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Business logic methods
    public boolean hasStock(int needed) {
        return quantity >= needed;
    }

    public void reduceStock(int amount) {
        quantity = quantity - amount;
    }

    // Abstract methods - must be implemented by subclasses
    public abstract boolean isExpired();
    public abstract boolean needsShipping();
    public abstract double getWeight();
}