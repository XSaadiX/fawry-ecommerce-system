// Simple cart using arrays with exception handling
class Cart {
    CartItem[] items;
    int itemCount;

    Cart() {
        items = new CartItem[20]; // Fixed size array
        itemCount = 0;
    }

    void add(Product product, int quantity) throws ECommerceException {
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

    boolean isEmpty() {
        return itemCount == 0;
    }

    double getSubtotal() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total = total + items[i].getTotalPrice();
        }
        return total;
    }

    CartItem[] getItems() {
        return items;
    }

    int getItemCount() {
        return itemCount;
    }
}