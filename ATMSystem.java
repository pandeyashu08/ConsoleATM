// ATMSystem.java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ATMSystem {
    private Map<String, Account> accounts;
    private Account currentAccount;
    private Scanner scanner;
    
    public ATMSystem() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
        initializeAccounts();
    }
    
    private void initializeAccounts() {
        // Pre-populated accounts for testing
        accounts.put("123456", new Account("123456", "1234", 75000.00));
        accounts.put("789012", new Account("789012", "5678", 125000.00));
        accounts.put("345678", new Account("345678", "9012", 37500.00));
    }
    
    public void start() {
        System.out.println("==============================================");
        System.out.println("        WELCOME TO ATM SIMULATION");
        System.out.println("==============================================");
        
        while (true) {
            if (currentAccount == null) {
                if (!login()) {
                    System.out.println("Thank you for using our ATM service!");
                    break;
                }
            } else {
                showMainMenu();
            }
        }
        scanner.close();
    }
    
    private boolean login() {
        System.out.println("\n--- LOGIN ---");
        System.out.println("Test Accounts Available:");
        System.out.println("Account: 123456, PIN: 1234, Balance: ₹75,000.00");
        System.out.println("Account: 789012, PIN: 5678, Balance: ₹1,25,000.00");
        System.out.println("Account: 345678, PIN: 9012, Balance: ₹37,500.00");
        System.out.println();
        
        System.out.print("Enter Account Number (or 'exit' to quit): ");
        String accountNumber = scanner.nextLine().trim();
        
        if (accountNumber.equalsIgnoreCase("exit")) {
            return false;
        }
        
        Account account = accounts.get(accountNumber);
        if (account == null) {
            System.out.println("Account not found. Please try again.\n");
            return login();
        }
        
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine().trim();
        
        if (account.validatePin(pin)) {
            currentAccount = account;
            System.out.println("\nLogin successful! Welcome, Account: " + accountNumber);
            return true;
        } else {
            System.out.println("Invalid PIN. Please try again.\n");
            return login();
        }
    }
    
    private void showMainMenu() {
        System.out.println("\n==============================================");
        System.out.println("                MAIN MENU");
        System.out.println("==============================================");
        System.out.println("1. Balance Inquiry");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transaction History");
        System.out.println("5. Logout");
        System.out.println("==============================================");
        System.out.print("Select an option (1-5): ");
        
        String choice = scanner.nextLine().trim();
        
        switch (choice) {
            case "1":
                balanceInquiry();
                break;
            case "2":
                depositMoney();
                break;
            case "3":
                withdrawMoney();
                break;
            case "4":
                showTransactionHistory();
                break;
            case "5":
                logout();
                break;
            default:
                System.out.println("Invalid option. Please select 1-5.");
        }
    }
    
    private void balanceInquiry() {
        System.out.println("\n--- BALANCE INQUIRY ---");
        System.out.printf("Account: %s\n", currentAccount.getAccountNumber());
        System.out.printf("Current Balance: ₹%.2f\n", currentAccount.getBalance());
        
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private void depositMoney() {
        System.out.println("\n--- DEPOSIT MONEY ---");
        System.out.printf("Current Balance: ₹%.2f\n", currentAccount.getBalance());
        System.out.print("Enter deposit amount: ₹");
        
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (currentAccount.deposit(amount)) {
                System.out.printf("Deposit successful!\n");
                System.out.printf("Amount Deposited: ₹%.2f\n", amount);
                System.out.printf("New Balance: ₹%.2f\n", currentAccount.getBalance());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format. Please enter a valid number.");
        }
        
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private void withdrawMoney() {
        System.out.println("\n--- WITHDRAW MONEY ---");
        System.out.printf("Current Balance: ₹%.2f\n", currentAccount.getBalance());
        System.out.print("Enter withdrawal amount: ₹");
        
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (currentAccount.withdraw(amount)) {
                System.out.printf("Withdrawal successful!\n");
                System.out.printf("Amount Withdrawn: ₹%.2f\n", amount);
                System.out.printf("Remaining Balance: ₹%.2f\n", currentAccount.getBalance());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format. Please enter a valid number.");
        }
        
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private void showTransactionHistory() {
        System.out.println("\n--- TRANSACTION HISTORY ---");
        System.out.printf("Account: %s\n", currentAccount.getAccountNumber());
        
        if (currentAccount.getTransactionHistory().isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("=".repeat(70));
            System.out.printf("%-10s | %-8s | %-19s | %s\n", "TYPE", "AMOUNT", "DATE & TIME", "BALANCE AFTER");
            System.out.println("=".repeat(70));
            
            // Show last 10 transactions (most recent first)
            var history = currentAccount.getTransactionHistory();
            int start = Math.max(0, history.size() - 10);
            
            for (int i = history.size() - 1; i >= start; i--) {
                System.out.println(history.get(i));
            }
            System.out.println("=".repeat(70));
        }
        
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private void logout() {
        System.out.println("\n--- LOGOUT ---");
        System.out.printf("Thank you for using our ATM service, Account: %s\n", 
                         currentAccount.getAccountNumber());
        currentAccount = null;
        System.out.println("You have been logged out successfully.\n");
    }
}
