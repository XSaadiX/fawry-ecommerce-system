// Customer class with proper encapsulation
class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    // Getters - provide read access to private data
    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    // Business logic methods that modify private data safely
    public void pay(double amount) {
        balance = balance - amount;
    }

    public boolean canPay(double amount) {
        return balance >= amount;
    }
}