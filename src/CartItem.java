// Cart item representing a product and its quantity in the cart
class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    Product getProduct() {
        return product;
    }

    int getQuantity() {
        return quantity;
    }

    double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}