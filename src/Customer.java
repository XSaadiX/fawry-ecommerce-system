// Customer class to handle customer data and balance
class Customer {
    String name;
    double balance;

    Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    String getName() {
        return name;
    }

    double getBalance() {
        return balance;
    }

    void pay(double amount) {
        balance = balance - amount;
    }

    boolean canPay(double amount) {
        return balance >= amount;
    }
}