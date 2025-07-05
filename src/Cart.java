// Cart class with proper encapsulation
class Cart {
    private CartItem[] items;
    private int itemCount;

    public Cart() {
        items = new CartItem[20]; // Fixed size array
        itemCount = 0;
    }

    // Public method to add items - controls access to private data
    public void add(Product product, int quantity) throws ECommerceException {
        if (itemCount >= 20) {
            throw new ECommerceException("Cart is full!");
        }

        if (!product.hasStock(quantity)) {
            throw new ECommerceException("Insufficient stock for " + product.getName() +
                    ". Available: " + product.getQuantity() + ", Requested: " + quantity);
        }

        if (product.isExpired()) {
            throw new ECommerceException("Product " + product.getName() + " is expired");
        }

        items[itemCount] = new CartItem(product, quantity);
        itemCount++;
    }

    // Getters - provide controlled read access
    public boolean isEmpty() {
        return itemCount == 0;
    }

    public double getSubtotal() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total = total + items[i].getTotalPrice();
        }
        return total;
    }

    public CartItem[] getItems() {
        return items; // In real code, we'd return a copy to protect the array
    }

    public int getItemCount() {
        return itemCount;
    }
}