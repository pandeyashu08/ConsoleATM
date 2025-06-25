// Account.java
import java.util.ArrayList;
import java.util.List;

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<Transaction> transactionHistory;
    private DebitCard debitCard;
    
    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.debitCard = new DebitCard(accountNumber);
    }
    
    // Constructor for predefined accounts (for testing)
    public Account(String accountNumber, String accountHolderName, double initialBalance, 
                   String cardNumber, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.debitCard = new DebitCard(accountNumber, cardNumber, pin);
    }
    
    public boolean validateDebitCard(String cardNumber, String pin) {
        return debitCard.getCardNumber().equals(cardNumber) && debitCard.validatePin(pin);
    }
    
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactionHistory() { return transactionHistory; }
    public DebitCard getDebitCard() { return debitCard; }
    
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds. Your current balance is: ₹" + String.format("%.2f", balance));
            return false;
        }
        balance -= amount;
        transactionHistory.add(new Transaction("WITHDRAWAL", amount, balance));
        return true;
    }
    
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive number.");
            return false;
        }
        balance += amount;
        transactionHistory.add(new Transaction("DEPOSIT", amount, balance));
        return true;
    }
}

