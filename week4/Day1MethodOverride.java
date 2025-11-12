import java.util.Scanner;

class Account {
    String accNumber;
    double balance;

    Account(String accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);
        }
    }
}

class PremiumAccount extends Account {
    double extraLimit;

    PremiumAccount(String accNumber, double balance, double extraLimit) {
        super(accNumber, balance);
        this.extraLimit = extraLimit;
    }

    // 🔥 Overridden withdraw() method
    @Override
    void withdraw(double amount) {
        if (amount > balance + extraLimit) {
            System.out.println("Withdrawal denied! Amount exceeds premium limit.");
        } else {
            balance -= amount;
            System.out.println("Withdrawn under PREMIUM rules: " + amount);
            System.out.println("Remaining Balance: " + balance);
        }
    }
}

public class Day1MethodOverride {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("---- Create Premium Account ----");
        System.out.print("Account Number: ");
        String acc = sc.nextLine();

        System.out.print("Balance: ");
        double bal = sc.nextDouble();

        System.out.print("Extra Premium Limit: ");
        double limit = sc.nextDouble();

        PremiumAccount pa = new PremiumAccount(acc, bal, limit);

        System.out.print("\nEnter amount to withdraw: ");
        double amt = sc.nextDouble();

        pa.withdraw(amt); // calls overridden method
    }
}
