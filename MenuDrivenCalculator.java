import java.util.Scanner;

public class MenuDrivenCalculator {

    // method for addition
    public static double add(double a, double b) {
        return a + b;
    }

    // method for subtraction
    public static double subtract(double a, double b) {
        return a - b;
    }

    // method for multiplication
    public static double multiply(double a, double b) {
        return a * b;
    }

    // method for division
    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Cannot divide by zero!");
            return 0;
        }
        return a / b;
    }

    // method for power
    public static double power(double a, double b) {
        return Math.pow(a, b);
    }

    // method to reverse a number
    public static int reverseNumber(int n) {
        int rev = 0;
        while (n != 0) {
            rev = rev * 10 + n % 10;
            n = n / 10;
        }
        return rev;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Add");
            System.out.println("2. Subtract");
            System.out.println("3. Multiply");
            System.out.println("4. Divide");
            System.out.println("5. Power");
            System.out.println("6. Reverse Number");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter two numbers: ");
                    double a1 = scanner.nextDouble();
                    double b1 = scanner.nextDouble();
                    System.out.println("Result: " + add(a1, b1));
                    break;

                case 2:
                    System.out.print("Enter two numbers: ");
                    double a2 = scanner.nextDouble();
                    double b2 = scanner.nextDouble();
                    System.out.println("Result: " + subtract(a2, b2));
                    break;

                case 3:
                    System.out.print("Enter two numbers: ");
                    double a3 = scanner.nextDouble();
                    double b3 = scanner.nextDouble();
                    System.out.println("Result: " + multiply(a3, b3));
                    break;

                case 4:
                    System.out.print("Enter two numbers: ");
                    double a4 = scanner.nextDouble();
                    double b4 = scanner.nextDouble();
                    System.out.println("Result: " + divide(a4, b4));
                    break;

                case 5:
                    System.out.print("Enter base and exponent: ");
                    double base = scanner.nextDouble();
                    double exp = scanner.nextDouble();
                    System.out.println("Result: " + power(base, exp));
                    break;

                case 6:
                    System.out.print("Enter number to reverse: ");
                    int num = scanner.nextInt();
                    System.out.println("Reversed number: " + reverseNumber(num));
                    break;

                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 7);

        scanner.close();
    }
}
