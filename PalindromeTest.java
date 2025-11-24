import java.util.Scanner;

public class PalindromeTest{
    public static void main(String args[]){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter a word:");
        String word= sc.nextLine();

        String reverse="";
        for(int i=word.length()-1; i>=0; i--){
            reverse += word.charAt(i);
        }
        if(reverse.equalsIgnoreCase(word)){
            System.out.println("Word is palindrome");
        }else{
            System.out.println("It is not palindrome");
        }
    }
}