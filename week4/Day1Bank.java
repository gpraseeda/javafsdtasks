import java.util.Scanner;

class Account {
    String accNumber;
    double balance;

    Account(String accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", New balance = " + balance);
    }

    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ", New balance = " + balance);
        }
    }
}

class SavingsAccount extends Account {
    double interestRate;

    SavingsAccount(String accNumber, double balance, double interestRate) {
        super(accNumber, balance);
        this.interestRate = interestRate;
    }

    void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest added: " + interest + ", New balance = " + balance);
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
        if (amount > balance + overdraftLimit) {
            System.out.println("Overdraft limit exceeded!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn with overdraft (if needed): " + amount + ", New balance = " + balance);
        }
    }
}

public class Day1Bank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // SavingsAccount Input
        System.out.println("---- Create Savings Account ----");
        System.out.print("Enter Savings Account Number: ");
        String saNum = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double saBalance = sc.nextDouble();

        System.out.print("Enter Interest Rate: ");
        double interest = sc.nextDouble();

        SavingsAccount sa = new SavingsAccount(saNum, saBalance, interest);

        // CheckingAccount Input
        sc.nextLine(); // clear buffer
        System.out.println("\n---- Create Checking Account ----");
        System.out.print("Enter Checking Account Number: ");
        String caNum = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double caBalance = sc.nextDouble();

        System.out.print("Enter Overdraft Limit: ");
        double overdraft = sc.nextDouble();

        CheckingAccount ca = new CheckingAccount(caNum, caBalance, overdraft);

        // Test operations
        System.out.println("\n---- Operations ----");

        System.out.print("Enter deposit amount for Savings Account: ");
        sa.deposit(sc.nextDouble());

        sa.addInterest();

        System.out.print("\nEnter withdrawal amount for Checking Account: ");
        ca.withdraw(sc.nextDouble());
    }
}

