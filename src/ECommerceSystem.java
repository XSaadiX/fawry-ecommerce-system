import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Fawry Assessment e-commerce system .
 */
public class ECommerceSystem {

    public static void checkout(Customer customer, Cart cart) {
        try {
            validateCheckout(customer, cart);

            double subtotal = cart.getSubtotal();
            List<Shippable> shippableItems = collectShippableItems(cart);
            double shippingFee = ShippingService.calculateShippingFee(shippableItems);
            double totalAmount = subtotal + shippingFee;

            validatePayment(customer, totalAmount);

            processPayment(customer, totalAmount);
            updateInventory(cart);

            ShippingService.processShipment(shippableItems);
            printReceipt(cart, subtotal, shippingFee, totalAmount, customer.getBalance());

        } catch (ECommerceException e) {
            System.err.println("Checkout failed: " + e.getMessage());
        }
    }

    private static void validateCheckout(Customer customer, Cart cart) throws ECommerceException {
        if (customer == null) {
            throw new ECommerceException("Customer cannot be null");
        }

        if (cart == null || cart.isEmpty()) {
            throw new ECommerceException("Cart is empty");
        }

        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();

            if (!product.isAvailable(item.getQuantity())) {
                throw new ECommerceException("Product " + product.getName() + " is out of stock");
            }

            if (product.isExpired()) {
                throw new ECommerceException("Product " + product.getName() + " is expired");
            }
        }
    }

    private static List<Shippable> collectShippableItems(Cart cart) {
        List<Shippable> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().requiresShipping()) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    shippableItems.add(new ShippingItem(
                            item.getProduct().getName(),
                            item.getProduct().getWeight()
                    ));
                }
            }
        }

        return shippableItems;
    }

    private static void validatePayment(Customer customer, double amount) throws ECommerceException {
        if (customer.getBalance() < amount) {
            throw new ECommerceException("Customer's balance is insufficient");
        }
    }

    private static void processPayment(Customer customer, double amount) {
        customer.deductBalance(amount);
    }

    private static void updateInventory(Cart cart) {
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
    }

    private static void printReceipt(Cart cart, double subtotal, double shippingFee,
                                     double totalAmount, double remainingBalance) {
        System.out.println("** Checkout receipt **");

        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %d%n",
                    item.getQuantity(),
                    item.getProduct().getName(),
                    (int) item.getTotalPrice());
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal %d%n", (int) subtotal);
        System.out.printf("Shipping %d%n", (int) shippingFee);
        System.out.printf("Amount %d%n", (int) totalAmount);
        System.out.printf("Customer current balance after payment: %.2f%n", remainingBalance);
        System.out.println("END.");
    }

    public static void main(String[] args) {
        System.out.println("=== Fawry E-commerce System ===\n");

        Product cheese = new PerishableProduct("Cheese", 100.0, 10,
                LocalDate.now().plusDays(7), true, 0.4);
        Product biscuits = new PerishableProduct("Biscuits", 150.0, 5,
                LocalDate.now().plusDays(30), true, 0.7);
        Product tv = new NonPerishableProduct("TV", 500.0, 3, true, 15.0);
        Product mobile = new NonPerishableProduct("Mobile", 300.0, 8, true, 0.2);
        Product scratchCard = new NonPerishableProduct("Scratch Card", 50.0, 20, false, 0.0);

        Customer customer = new Customer("John Doe", 1000.0);

        System.out.println("=== Test Case 1: Successful Checkout ===");
        try {
            Cart cart = new Cart();
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);
            checkout(customer, cart);
        } catch (ECommerceException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Test Case 2: Empty Cart Error ===");
        Cart emptyCart = new Cart();
        checkout(customer, emptyCart);

        System.out.println("\n=== Test Case 3: Insufficient Stock Error ===");
        try {
            Cart cart = new Cart();
            cart.add(tv, 5);
            checkout(customer, cart);
        } catch (ECommerceException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}