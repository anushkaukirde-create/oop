import java.util.Scanner;

/**
 * Experiment 7: ATM simulation with exception handling.
 * - Insufficient funds     -> ArithmeticException
 * - Invalid amount/choice  -> IllegalArgumentException
 * finally block prints a transaction completion message.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM atm = new ATM(5000.0);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextLine()) break;
            String input = sc.nextLine().trim();

            try {
                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid menu choice: '" + input + "'");
                }

                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        atm.withdraw(readAmount(sc));
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        atm.deposit(readAmount(sc));
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid menu choice: " + choice);
                }
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } finally {
                System.out.println("-- Transaction completed --");
            }
        }

        System.out.println("Thank you for using the ATM. Goodbye!");
        sc.close();
    }

    static double readAmount(Scanner sc) {
        String s = sc.nextLine().trim();
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Amount must be a number.");
        }
    }
}

class ATM {
    private double balance;

    ATM(double initialBalance) {
        this.balance = initialBalance;
    }

    void checkBalance() {
        System.out.printf("Current balance: Rs. %.2f%n", balance);
    }

    void withdraw(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if (amount > balance)
            throw new ArithmeticException("Insufficient funds.");
        balance -= amount;
        System.out.printf("Withdrawal successful. New balance: Rs. %.2f%n", balance);
    }

    void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Deposit amount must be positive.");
        balance += amount;
        System.out.printf("Deposit successful. New balance: Rs. %.2f%n", balance);
    }
}
