// Main e-commerce system with proper encapsulation
public class ECommerceSystem {

    public static void checkout(Customer customer, Cart cart) throws ECommerceException {
        // Check if cart is empty
        if (cart.isEmpty()) {
            throw new ECommerceException("Cart is empty");
        }

        // Check stock and expiration for all items
        for (int i = 0; i < cart.getItemCount(); i++) {
            CartItem item = cart.getItems()[i];
            if (!item.getProduct().hasStock(item.getQuantity())) {
                throw new ECommerceException("Product " + item.getProduct().getName() + " is out of stock");
            }
            if (item.getProduct().isExpired()) {
                throw new ECommerceException("Product " + item.getProduct().getName() + " is expired");
            }
        }

        // Calculate costs
        double subtotal = cart.getSubtotal();

        // Collect shipping items
        Shippable[] shippingItems = new Shippable[100]; // Max possible items
        int shippingCount = 0;

        for (int i = 0; i < cart.getItemCount(); i++) {
            CartItem item = cart.getItems()[i];
            if (item.getProduct().needsShipping()) {
                // Add each quantity as separate shipping item
                for (int j = 0; j < item.getQuantity(); j++) {
                    shippingItems[shippingCount] = new ShippingItem(
                            item.getProduct().getName(),
                            item.getProduct().getWeight()
                    );
                    shippingCount++;
                }
            }
        }

        double shippingFee = ShippingService.calculateShipping(shippingItems, shippingCount);
        double totalAmount = subtotal + shippingFee;

        // Check customer balance
        if (!customer.canPay(totalAmount)) {
            throw new ECommerceException("Customer's balance is insufficient");
        }

        // Process payment and update stock
        customer.pay(totalAmount);

        for (int i = 0; i < cart.getItemCount(); i++) {
            CartItem item = cart.getItems()[i];
            item.getProduct().reduceStock(item.getQuantity());
        }

        // Print shipping notice and receipt
        ShippingService.processShipment(shippingItems, shippingCount);

        System.out.println("** Checkout receipt **");
        for (int i = 0; i < cart.getItemCount(); i++) {
            CartItem item = cart.getItems()[i];
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + (int)item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);
        System.out.println("Shipping " + (int)shippingFee);
        System.out.println("Amount " + (int)totalAmount);
        System.out.println("Customer current balance after payment: " + customer.getBalance());
        System.out.println("END.");
    }

    public static void main(String[] args) {
        System.out.println("=== Fawry E-commerce System Demo ===\n");

        // Create products
        ExpirableProduct cheese = new ExpirableProduct("Cheese", 100.0, 10, false, true, 0.4);
        ExpirableProduct biscuits = new ExpirableProduct("Biscuits", 150.0, 5, false, true, 0.7);
        NonExpirableProduct tv = new NonExpirableProduct("TV", 500.0, 3, true, 15.0);
        NonExpirableProduct scratchCard = new NonExpirableProduct("Scratch Card", 50.0, 20, false, 0.0);

        // Create customer
        Customer customer = new Customer("John Doe", 1000.0);

        // Test Case 1: Successful checkout
        System.out.println("=== Test Case 1: Successful Checkout ===");
        try {
            Cart cart1 = new Cart();
            cart1.add(cheese, 2);
            cart1.add(biscuits, 1);
            cart1.add(scratchCard, 1);
            checkout(customer, cart1);
        } catch (ECommerceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 2: Empty cart
        System.out.println("\n=== Test Case 2: Empty Cart Error ===");
        try {
            Cart emptyCart = new Cart();
            checkout(customer, emptyCart);
        } catch (ECommerceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 3: Insufficient stock
        System.out.println("\n=== Test Case 3: Insufficient Stock Error ===");
        try {
            Cart cart2 = new Cart();
            cart2.add(tv, 5); // Only 3 TVs available
            checkout(customer, cart2);
        } catch (ECommerceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 4: Expired product
        System.out.println("\n=== Test Case 4: Expired Product Error ===");
        try {
            ExpirableProduct expiredCheese = new ExpirableProduct("Expired Cheese", 100.0, 5, true, true, 0.4);
            Cart cart3 = new Cart();
            cart3.add(expiredCheese, 1);
            checkout(customer, cart3);
        } catch (ECommerceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 5: Insufficient balance
        System.out.println("\n=== Test Case 5: Insufficient Balance Error ===");
        try {
            Customer poorCustomer = new Customer("Poor Customer", 50.0);
            Cart cart4 = new Cart();
            cart4.add(tv, 1);
            checkout(poorCustomer, cart4);
        } catch (ECommerceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Test Case 6: Non-shippable items only
        System.out.println("\n=== Test Case 6: Non-shippable Items Only ===");
        try {
            Cart cart5 = new Cart();
            cart5.add(scratchCard, 3);
            checkout(customer, cart5);
        } catch (ECommerceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}