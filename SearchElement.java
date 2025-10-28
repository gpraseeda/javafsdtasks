import java.util.Scanner;

public class SearchElement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = {10, 25, 30, 45, 60};

        System.out.print("Enter element to search: ");
        int key = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                System.out.println("Element " + key + " found at index: " + i);
                found = true;
                break; // stop once found
            }
        }

        if (!found) {
            System.out.println("Element not found in array.");
        }
    }
}
