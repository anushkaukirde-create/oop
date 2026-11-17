import java.util.Scanner;

/**
 * Experiment 1: Robust menu-driven calculator.
 * Input format: operand1 operator operand2   (e.g. 12 + 5)
 * Type "exit" or "quit" to stop.
 */
public class Main {

    static double calculate(double a, char op, double b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Cannot divide by zero.");
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== JAVA CALCULATOR =====");
        System.out.println("Supported operators: +  -  *  /");
        System.out.println("Enter: operand1 operator operand2   (example: 12 * 4)");
        System.out.println("Type 'exit' to quit.");

        while (true) {
            System.out.print("\n> ");
            if (!sc.hasNextLine()) break;
            String line = sc.nextLine().trim();

            if (line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("quit")) {
                break;
            }

            String[] parts = line.split("\\s+");
            if (parts.length != 3 || parts[1].length() != 1) {
                System.out.println("Invalid input. Use: operand1 operator operand2");
                continue;
            }

            try {
                double a = Double.parseDouble(parts[0]);
                char op = parts[1].charAt(0);
                double b = Double.parseDouble(parts[2]);
                double result = calculate(a, op, b);
                System.out.println("Result: " + a + " " + op + " " + b + " = " + result);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: operands must be numbers.");
            } catch (ArithmeticException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
        System.out.println("Calculator closed. Goodbye!");
    }
}
