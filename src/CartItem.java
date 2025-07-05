// Cart item with proper encapsulation
class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters - provide controlled access to private data
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Calculated property - uses private data safely
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}