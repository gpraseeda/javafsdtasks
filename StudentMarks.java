import java.util.Scanner;

public class StudentMarks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        int[] marks = new int[n];

        // Input marks
        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks of student " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

        int max = marks[0];
        int min = marks[0];
        int sum = 0;

        // Loop to calculate max, min, sum
        for (int i = 0; i < n; i++) {
            if (marks[i] > max)
                max = marks[i];
            if (marks[i] < min)
                min = marks[i];
            sum += marks[i];
        }

        double average = (double) sum / n;

        // Output results
        System.out.println("\nHighest Marks: " + max);
        System.out.println("Lowest Marks: " + min);
        System.out.println("Average Marks: " + average);
    }
}