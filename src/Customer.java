import java.util.Objects;

/**
 * Represents a customer in the e-commerce system.
 */
public class Customer {
    private final String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = Objects.requireNonNull(name, "Customer name cannot be null");
        if (balance < 0) {
            throw new IllegalArgumentException("Customer balance cannot be negative");
        }
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deduction amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return String.format("Customer{name='%s', balance=%.2f}", name, balance);
    }
}