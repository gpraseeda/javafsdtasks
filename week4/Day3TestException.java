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
            throw new InsufficientFundsException(
                    "Minimum balance of 100 must be maintained in SavingsAccount.");
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
            throw new InsufficientFundsException("Overdraft limit exceeded in CheckingAccount!");
        }

        balance -= amount;
        System.out.println("CheckingAccount: Withdrawn " + amount + " | Balance: " + balance);
    }
}

// -------------------- MAIN CLASS TO TEST ALL EXCEPTIONS --------------------

public class Day3TestException {
    public static void main(String[] args) {

        // ---------- Test with base Account ----------
        Account acc = new Account("A101", 300);

        // 1) Valid withdraw
        System.out.println("=== Test 1: Account - Valid Withdraw ===");
        try {
            acc.withdraw(200);  // OK
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // 2) Invalid amount (<= 0)
        System.out.println("\n=== Test 2: Account - Invalid Amount ===");
        try {
            acc.withdraw(0);    // InvalidAmountException
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // 3) Insufficient funds
        System.out.println("\n=== Test 3: Account - Insufficient Funds ===");
        try {
            acc.withdraw(500);  // InsufficientFundsException
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // ---------- Test with SavingsAccount ----------
        SavingsAccount sa = new SavingsAccount("S202", 600);

        // 4) Valid withdraw (still keeps minimum 100)
        System.out.println("\n=== Test 4: SavingsAccount - Valid Withdraw ===");
        try {
            sa.withdraw(400);   // 600 - 400 = 200 >= 100 -> OK
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // 5) Minimum balance violation
        System.out.println("\n=== Test 5: SavingsAccount - Min Balance Violation ===");
        try {
            sa.withdraw(550);   // will break min balance rule
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // 6) Invalid amount in SavingsAccount
        System.out.println("\n=== Test 6: SavingsAccount - Invalid Amount ===");
        try {
            sa.withdraw(-50);   // InvalidAmountException
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // ---------- Test with CheckingAccount ----------
        CheckingAccount ca = new CheckingAccount("C303", 500, 200); // total allowed = 700

        // 7) Valid withdraw (within overdraft limit)
        System.out.println("\n=== Test 7: CheckingAccount - Valid with Overdraft ===");
        try {
            ca.withdraw(650);   // 500 + 200 = 700, so 650 is OK
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // 8) Overdraft exceeded
        System.out.println("\n=== Test 8: CheckingAccount - Overdraft Exceeded ===");
        try {
            ca.withdraw(200);   // after previous, balance is -150, overdraft already used heavily
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        // 9) Invalid amount in CheckingAccount
        System.out.println("\n=== Test 9: CheckingAccount - Invalid Amount ===");
        try {
            ca.withdraw(0);     // InvalidAmountException
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        System.out.println("\n=== All exception handling tests completed ===");
    }
}
