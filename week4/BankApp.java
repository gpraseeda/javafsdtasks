import java.io.*;
import java.util.*;

// --------- Custom Exceptions ---------
class InvalidAmountException extends Exception {
    InvalidAmountException(String msg) { super(msg); }
}

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String msg) { super(msg); }
}

class AccountNotFoundException extends Exception {
    AccountNotFoundException(String msg) { super(msg); }
}

// --------- Account Class ---------
class Account {
    String accNumber;
    String holderName;
    double balance;
    String type; // e.g., SAVINGS / CHECKING

    public Account(String accNumber, String holderName, double balance, String type) {
        this.accNumber = accNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.type = type;
    }

    void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be > 0");
        }
        balance += amount;
    }

    void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be > 0");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Available: " + balance);
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return accNumber + " | " + holderName + " | " + type + " | Balance: " + balance;
    }

    // For file persistence (CSV)
    String toCSV() {
        return accNumber + "," + holderName + "," + balance + "," + type;
    }

    static Account fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) return null;
        String accNo = parts[0];
        String name = parts[1];
        double bal = Double.parseDouble(parts[2]);
        String type = parts[3];
        return new Account(accNo, name, bal, type);
    }
}

// --------- Bank Class (manages accounts + file) ---------
class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private final String FILE_NAME = "accounts.txt";

    public Bank() {
        loadFromFile();
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            // No file yet, first run
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            accounts.clear();
            while ((line = br.readLine()) != null) {
                Account acc = Account.fromCSV(line.trim());
                if (acc != null) {
                    accounts.put(acc.accNumber, acc);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading accounts file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts.values()) {
                pw.println(acc.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts file: " + e.getMessage());
        }
    }

    public void createAccount(String accNo, String name, double initialBalance, String type)
            throws InvalidAmountException {
        if (accounts.containsKey(accNo)) {
            System.out.println("Account already exists with this number.");
            return;
        }
        if (initialBalance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative.");
        }
        Account acc = new Account(accNo, name, initialBalance, type);
        accounts.put(accNo, acc);
        saveToFile();
        System.out.println("Account created successfully.");
    }

    private Account getAccount(String accNo) throws AccountNotFoundException {
        Account acc = accounts.get(accNo);
        if (acc == null) {
            throw new AccountNotFoundException("Account not found: " + accNo);
        }
        return acc;
    }

    public void deposit(String accNo, double amount)
            throws AccountNotFoundException, InvalidAmountException {
        Account acc = getAccount(accNo);
        acc.deposit(amount);
        saveToFile();
        System.out.println("Deposit successful. New balance: " + acc.balance);
    }

    public void withdraw(String accNo, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientFundsException {
        Account acc = getAccount(accNo);
        acc.withdraw(amount);
        saveToFile();
        System.out.println("Withdrawal successful. New balance: " + acc.balance);
    }

    public void transfer(String fromAccNo, String toAccNo, double amount)
            throws AccountNotFoundException, InvalidAmountException, InsufficientFundsException {
        if (fromAccNo.equals(toAccNo)) {
            throw new InvalidAmountException("Cannot transfer to same account.");
        }
        Account from = getAccount(fromAccNo);
        Account to = getAccount(toAccNo);

        from.withdraw(amount);   // may throw
        to.deposit(amount);      // may throw

        saveToFile();
        System.out.println("Transfer successful.");
        System.out.println("From " + from.accNumber + " New balance: " + from.balance);
        System.out.println("To   " + to.accNumber + " New balance: " + to.balance);
    }

    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("---- All Accounts ----");
        for (Account acc : accounts.values()) {
            System.out.println(acc);
        }
    }
}

// --------- Main App (Menu + Exception Handling) ---------
public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Show All Accounts");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Account Number: ");
                        String accNo = sc.nextLine();

                        System.out.print("Enter Holder Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Initial Balance: ");
                        double initBal = Double.parseDouble(sc.nextLine());

                        System.out.print("Enter Account Type (SAVINGS/CHECKING): ");
                        String type = sc.nextLine().toUpperCase();

                        bank.createAccount(accNo, name, initBal, type);
                        break;

                    case 2:
                        System.out.print("Enter Account Number: ");
                        String dAcc = sc.nextLine();
                        System.out.print("Enter Amount to Deposit: ");
                        double dAmt = Double.parseDouble(sc.nextLine());
                        bank.deposit(dAcc, dAmt);
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        String wAcc = sc.nextLine();
                        System.out.print("Enter Amount to Withdraw: ");
                        double wAmt = Double.parseDouble(sc.nextLine());
                        bank.withdraw(wAcc, wAmt);
                        break;

                    case 4:
                        System.out.print("Enter FROM Account Number: ");
                        String fromAcc = sc.nextLine();
                        System.out.print("Enter TO Account Number: ");
                        String toAcc = sc.nextLine();
                        System.out.print("Enter Amount to Transfer: ");
                        double tAmt = Double.parseDouble(sc.nextLine());
                        bank.transfer(fromAcc, toAcc, tAmt);
                        break;

                    case 5:
                        bank.showAllAccounts();
                        break;

                    case 0:
                        exit = true;
                        System.out.println("Exiting. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (InvalidAmountException |
                     InsufficientFundsException |
                     AccountNotFoundException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
