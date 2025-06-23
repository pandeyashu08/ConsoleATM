
// Account.java
import java.util.ArrayList;
import java.util.List;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;
    private List<Transaction> transactionHistory;
    
    public Account(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }
    
    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }
    
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactionHistory() { return transactionHistory; }
    
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
