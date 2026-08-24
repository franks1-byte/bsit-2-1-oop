public class Account {
    // Fields are PRIVATE — no one outside can touch them directly.
    private String owner;
    private double balance;

    // Constructor
    public Account(String owner, double openingBalance) {
        this.owner = owner;

        // Only accept an opening balance of 0 or more.
        // If negative, set balance to 0.
        if (openingBalance < 0) {
            this.balance = 0;
        } else {
            this.balance = openingBalance;
        }
    }

    // Getter for owner
    public String getOwner() {
        return owner;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        balance += amount;
        System.out.println("Deposited " + amount + ". New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance -= amount;
        System.out.println("Withdrawn " + amount + ". New balance: " + balance);
    }
}