import java.util.Scanner;

// -------------------- CUSTOM EXCEPTIONS --------------------

class InvalidAmountException extends Exception {
    InvalidAmountException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) {
        super(message);
    }
}

// -------------------- BASE CLASS --------------------

class Account {
    String accNumber;
    double balance;

    Account(String accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds in Account.");
        }

        balance -= amount;
        System.out.println("Account: Withdrawn " + amount + " | New Balance: " + balance);
    }
}

// -------------------- SAVINGS ACCOUNT --------------------

class SavingsAccount extends Account {

    SavingsAccount(String accNumber, double balance) {
        super(accNumber, balance);
    }

    @Override
    void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {

        if (amount <= 0) {
            throw new InvalidAmountException("Invalid withdraw amount for SavingsAccount.");
        }

        if (balance - amount < 100) {
            throw new InsufficientFundsException("Minimum balance of 100 must be maintained.");
        }

        balance -= amount;
        System.out.println("SavingsAccount: Withdrawn " + amount + " | Balance: " + balance);
    }
}

// -------------------- CHECKING ACCOUNT --------------------

class CheckingAccount extends Account {
    double overdraftLimit;

    CheckingAccount(String accNumber, double balance, double overdraftLimit) {
        super(accNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {

        if (amount <= 0) {
            throw new InvalidAmountException("Invalid amount for CheckingAccount.");
        }

        if (amount > balance + overdraftLimit) {
            throw new InsufficientFundsException("Overdraft limit exceeded!");
        }

        balance -= amount;
        System.out.println("CheckingAccount: Withdrawn " + amount + " | Balance: " + balance);
    }
}

// -------------------- MAIN CLASS --------------------

public class Day3Custom {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Polymorphism: Parent reference → Child object
        Account acc = new CheckingAccount("C202", 500, 200);

        try {
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();

            acc.withdraw(amount);   // calls overridden method

        } catch (InvalidAmountException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unknown error occurred.");
        }

        System.out.println("Program ended safely.");
    }
}
