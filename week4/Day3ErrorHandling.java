import java.util.InputMismatchException;
import java.util.Scanner;

class Account {
    String accNumber;
    double balance;

    Account(String accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Must be greater than 0.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds in Account!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | Remaining balance: " + balance);
        }
    }
}

class SavingsAccount extends Account {
    SavingsAccount(String accNumber, double balance) {
        super(accNumber, balance);
    }

    @Override
    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for SavingsAccount!");
            return;
        }
        if (balance - amount < 100) {
            System.out.println("Cannot withdraw. Minimum balance of 100 must be maintained.");
        } else {
            balance -= amount;
            System.out.println("SavingsAccount: Withdrawn " + amount + " | Balance: " + balance);
        }
    }
}

class CheckingAccount extends Account {
    double overdraftLimit;

    CheckingAccount(String accNumber, double balance, double overdraftLimit) {
        super(accNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for CheckingAccount!");
            return;
        }
        if (amount > balance + overdraftLimit) {
            System.out.println("Overdraft limit exceeded!");
        } else {
            balance -= amount;
            System.out.println("CheckingAccount: Withdrawn " + amount + " | Balance: " + balance);
        }
    }
}

public class Day3ErrorHandling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Account acc = new CheckingAccount("C101", 500, 200);

        try {
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();

            acc.withdraw(amount);  // polymorphism + error handling

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter numbers only.");
        }

        System.out.println("Program finished.");
    }
}
