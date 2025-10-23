import java.util.Scanner;

public class CountVowels {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        int count = 0;
        input = input.toLowerCase(); // make it lowercase for easy comparison

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i); // get each character

            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }

        System.out.println("Number of vowels: " + count);

        scanner.close();
    }
}
