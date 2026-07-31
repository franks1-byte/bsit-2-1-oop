/*
 * ============================================================================
 * LAB ACTIVITY 5 — Exception Handling &amp; Debugging
 * Program : Mini ATM (Command-Line Interface)
 * Course : Object-Oriented Programming (Java)
 * ----------------------------------------------------------------------------
 * STARTER SCAFFOLD. This file already COMPILES and RUNS, but it is NOT safe:
 * it crashes on bad input and lets you withdraw more money than you have.
 * Your job is to make it robust using the exception-handling tools from the
 * lecture. Follow the TODOs in order (Task 1 -&gt; Task 5).

 *
 * How to compile and run (from this folder, in the terminal):
 * javac MiniATM.java
 * java MiniATM
 * ============================================================================
 */
import java.util.Scanner;
public class MiniATM {
    // The account balance shared by the whole program.
    static double balance = 1000.00;
    // One Scanner for reading everything the user types.
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println(" WELCOME TO THE MINI ATM");
        System.out.println("=========================================");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = input.nextLine().trim();
            switch (choice) {
                case "1":
                    deposit();
                    break;
                case "2":
                    withdraw();
                    break;
                case "3":
                    checkBalance();
                    break;
                case "4":
                    running = false;
                    System.out.println("\nThank you for using the Mini ATM. Goodbye!");
                    break;
                default:
// A friendly message beats a crash. (See Task 2 for input parsing.)
                    System.out.println("\n[!] Please choose a number from 1 to 4.\n");
            }
        }
    }
    static void printMenu() {
        System.out.println("Current options:");
        System.out.println(" [1] Deposit");
        System.out.println(" [2] Withdraw");
        System.out.println(" [3] Check balance");
        System.out.println(" [4] Exit");
        System.out.print("Enter your choice: ");
    }
    // -------------------------------------------------------------------------
// DEPOSIT
// -------------------------------------------------------------------------
    static void deposit() {
        System.out.print("Enter amount to deposit: ");
        String line = input.nextLine().trim();
// TODO (Task 2): The next line CRASHES with a NumberFormatException if the

// user types something that is not a number (e.g. "abc").
// Wrap the parsing + deposit logic in a try/catch so the
// program prints a friendly message instead of crashing.
        try {
            double amount = Double.parseDouble(line);
// TODO (Task 3): A deposit must be POSITIVE. If amount &lt;= 0, throw your
// custom InvalidAmountException with a clear message,
// and catch it here (or let deposit() declare `throws`).
            if (amount <= 0) {
                throw new InvalidAmountException("Deposit amount must be greater than PHP 0.00.");
            }
            balance += amount;
            System.out.printf("Deposited PHP %.2f. New balance: PHP %.2f%n%n", amount, balance);
        } catch (NumberFormatException e) {
            System.out.println("[!] Please enter a valid number.");
        } catch (InvalidAmountException e) {
            System.out.println("[!] " + e.getMessage());
        } finally {
// TODO (Task 4): Add a `finally` block that always prints a line such as
// "-- transaction finished --" whether or not it succeeded.
            System.out.println("-- transaction finished --\n");
        }
    }
    // -------------------------------------------------------------------------
// WITHDRAW
// -------------------------------------------------------------------------
    static void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        String line = input.nextLine().trim();
// TODO (Task 2): Same as deposit — guard the parsing with try/catch so a
// bad entry (letters, symbols, empty) is handled gracefully.
        try {
            double amount = Double.parseDouble(line);
// TODO (Task 3): If `amount` is greater than `balance`, the code below
// wrongly allows an overdraft. Instead:
// - throw a custom InsufficientFundsException
// - the exception should carry the shortfall (amount - balance)
// - catch it and tell the user how much they are short.
// Also reject amounts &lt;= 0 (InvalidAmountException).
            if (amount <= 0) {
                throw new InvalidAmountException("Withdrawal amount must be greater than PHP 0.00.");
            }
            if (amount > balance) {
                double shortfall = amount - balance;
                throw new InsufficientFundsException(
                        String.format("Insufficient funds. You are short by PHP %.2f.", shortfall),
                        shortfall
                );
            }
            balance -= amount;
            System.out.printf("Withdrew PHP %.2f. New balance: PHP %.2f%n%n", amount, balance);
// TODO (Task 4): Combine the two input problems (NumberFormatException and
// your custom exceptions) and use MULTI-CATCH where it makes
// sense. Add a `finally` block for the closing message.
        } catch (NumberFormatException e) {
            System.out.println("[!] Please enter a valid number.");
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("[!] " + e.getMessage());
        } finally {
            System.out.println("-- transaction finished --\n");
        }
    }
    // -------------------------------------------------------------------------
// CHECK BALANCE
// -------------------------------------------------------------------------
    static void checkBalance() {
        System.out.printf("%nYour current balance is: PHP %.2f%n%n", balance);
    }
}
/*
 * ============================================================================
 * CUSTOM EXCEPTIONS
 * These live in the SAME file for simplicity. They are not `public`, so the
 * file name only has to match the public class (MiniATM).
 * ============================================================================
 */
// TODO (Task 3): Finish this custom exception.
// - Add a constructor that accepts a String message and calls super(message).
// - (Optional) store the shortfall amount and add a getShortfall() method.
class InsufficientFundsException extends Exception {
    // Example of what you will add:
//
// public InsufficientFundsException(String message) {
// super(message);
// }
    private final double shortfall;

    public InsufficientFundsException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() {
        return shortfall;
    }
}
// TODO (Task 3): Create this custom exception too, for amounts that are zero or
// negative. Give it a constructor that accepts a message.
class InvalidAmountException extends Exception {
    // your code here
    public InvalidAmountException(String message) {
        super(message);
    }
}