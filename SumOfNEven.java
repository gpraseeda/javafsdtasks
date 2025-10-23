import java.util.Scanner;

public class SumOfNEven {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter value of N: ");
        int n = scanner.nextInt();

        int sum = 0;

        for (int i = 1; i <= n; i++) {
            int evenNumber = 2 * i; // generate even number
            sum += evenNumber;      // add to sum
        }

        System.out.println("Sum of first " + n + " even numbers is: " + sum);

        scanner.close();
    }
}
