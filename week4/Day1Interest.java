class Account {
    String accNumber;
    double balance;

    Account(String accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + " | New Balance: " + balance);
    }

    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient Balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);
        }
    }
}

// SavingsAccount that adds interest method
class SavingsAccount extends Account {
    double interestRate; // in %

    SavingsAccount(String accNumber, double balance, double interestRate) {
        super(accNumber, balance);
        this.interestRate = interestRate;
    }

    // 🔥 add interest method
    void addInterest() {
        double interest = (balance * interestRate) / 100;
        balance += interest;
        System.out.println("Interest Added: " + interest + " | Balance After Interest: " + balance);
    }
}

public class Day1Interest {
    public static void main(String[] args) {
        SavingsAccount sa = new SavingsAccount("SA101", 1000, 5.0);

        sa.deposit(500);
        sa.addInterest();
        sa.withdraw(300);
    }
}
