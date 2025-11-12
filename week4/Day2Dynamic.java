class Account {
    String accNumber;
    double balance;

    Account(String accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    void withdraw(double amount) {
        System.out.println("Account: General withdraw rule");
    }
}

class SavingsAccount extends Account {
    SavingsAccount(String accNumber, double balance) {
        super(accNumber, balance);
    }

    @Override
    void withdraw(double amount) {
        System.out.println("SavingsAccount: Withdraw with minimum balance rule");
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
        System.out.println("CheckingAccount: Withdraw with overdraft rule");
    }
}

public class Day2Dynamic {
    public static void main(String[] args) {

        // 🔥 Parent reference → Child objects
        Account acc1 = new SavingsAccount("S101", 500);
        Account acc2 = new CheckingAccount("C202", 300, 200);

        // Calls child's overridden methods
        acc1.withdraw(200);
        acc2.withdraw(400);
    }
}
