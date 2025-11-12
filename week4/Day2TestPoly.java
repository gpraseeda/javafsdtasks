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
        System.out.println("SavingsAccount: Withdraw with MINIMUM BALANCE rule");
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
        System.out.println("CheckingAccount: Withdraw with OVERDRAFT rule");
    }
}

public class Day2TestPoly {
    public static void main(String[] args) {

        // 🔥 Parent reference → Child object
        Account acc1 = new SavingsAccount("S101", 800);
        Account acc2 = new CheckingAccount("C202", 500, 200);

        // 🔥 Polymorphism: Calls child's overridden methods
        acc1.withdraw(300);   // calls SavingsAccount version
        acc2.withdraw(600);   // calls CheckingAccount version
    }
}
