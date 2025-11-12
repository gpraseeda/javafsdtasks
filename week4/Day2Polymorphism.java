class Account {
    String accNumber;
    double balance;

    Account(String accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Account: Insufficient Balance!");
        } else {
            balance -= amount;
            System.out.println("Account: Withdrawn " + amount + ", Balance: " + balance);
        }
    }
}

class SavingsAccount extends Account {
    SavingsAccount(String accNumber, double balance) {
        super(accNumber, balance);
    }

    @Override
    void withdraw(double amount) {
        // Savings account cannot go below 100 minimum balance
        if (balance - amount < 100) {
            System.out.println("SavingsAccount: Cannot go below minimum balance of 100.");
        } else {
            balance -= amount;
            System.out.println("SavingsAccount: Withdrawn " + amount + ", Balance: " + balance);
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
        // Checking account allows overdraft
        if (amount > balance + overdraftLimit) {
            System.out.println("CheckingAccount: Overdraft limit exceeded!");
        } else {
            balance -= amount;
            System.out.println("CheckingAccount: Withdrawn (with overdraft if needed) "
                    + amount + ", Balance: " + balance);
        }
    }
}

public class Day2Polymorphism {
    public static void main(String[] args) {

        Account acc = new Account("A101", 500);
        SavingsAccount sa = new SavingsAccount("S202", 600);
        CheckingAccount ca = new CheckingAccount("C303", 300, 200);

        System.out.println("---- ACCOUNT WITHDRAW ----");
        acc.withdraw(400);

        System.out.println("\n---- SAVINGS ACCOUNT WITHDRAW ----");
        sa.withdraw(550);   // violates minimum balance

        System.out.println("\n---- CHECKING ACCOUNT WITHDRAW ----");
        ca.withdraw(450);   // uses overdraft
    }
}
