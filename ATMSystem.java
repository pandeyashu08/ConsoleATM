// ATMSystem.java
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ATMSystem {
    private Map<String, Account> accountsByCardNumber;
    private Account currentAccount;
    private Scanner scanner;
    
    public ATMSystem() {
        accountsByCardNumber = new HashMap<>();
        scanner = new Scanner(System.in);
        initializeAccounts();
    }
    
    private void initializeAccounts() {
        // Pre-populated accounts for testing with predefined debit cards
        Account acc1 = new Account("ACC001", "John Doe", 75000.00, "4532123456789012", "1234");
        Account acc2 = new Account("ACC002", "Jane Smith", 125000.00, "4532987654321098", "5678");
        Account acc3 = new Account("ACC003", "Mike Johnson", 37500.00, "4532456789012345", "9012");
        
        accountsByCardNumber.put(acc1.getDebitCard().getCardNumber(), acc1);
        accountsByCardNumber.put(acc2.getDebitCard().getCardNumber(), acc2);
        accountsByCardNumber.put(acc3.getDebitCard().getCardNumber(), acc3);
    }
    
    public void start() {
        System.out.println("==============================================");
        System.out.println("        WELCOME TO SECURE ATM SYSTEM");
        System.out.println("==============================================");
        System.out.println("   Insert your debit card to get started");
        System.out.println("==============================================");
        
        while (true) {
            if (currentAccount == null) {
                if (!authenticate()) {
                    System.out.println("Thank you for using our ATM service!");
                    break;
                }
            } else {
                showMainMenu();
            }
        }
        scanner.close();
    }
    
    private boolean authenticate() {
        System.out.println("\n--- CARD AUTHENTICATION ---");
        System.out.print("Enter your 16-digit debit card number (or 'exit' to quit): ");
        String cardNumber = scanner.nextLine().trim().replaceAll("\\s+", "");
        
        if (cardNumber.equalsIgnoreCase("exit")) {
            return false;
        }
        
        // Validate card number format
        if (!isValidCardNumber(cardNumber)) {
            System.out.println("Invalid card number format. Please enter a 16-digit number.");
            return authenticate();
        }
        
        Account account = accountsByCardNumber.get(cardNumber);
        if (account == null) {
            System.out.println("Card not recognized. Please check your card number and try again.");
            return authenticate();
        }
        
        System.out.print("Enter your 4-digit PIN: ");
        String pin = scanner.nextLine().trim();
        
        if (!isValidPin(pin)) {
            System.out.println("Invalid PIN format. Please enter a 4-digit PIN.");
            return authenticate();
        }
        
        if (account.validateDebitCard(cardNumber, pin)) {
            currentAccount = account;
            System.out.println("\nAuthentication successful!");
            System.out.println("Welcome, " + account.getAccountHolderName());
            System.out.println("Card: " + account.getDebitCard().getMaskedCardNumber());
            return true;
        } else {
            System.out.println("Incorrect PIN. Please try again.\n");
            return authenticate();
        }
    }
    
    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{16}");
    }
    
    private boolean isValidPin(String pin) {
        return pin.matches("\\d{4}");
    }
    
    private void showMainMenu() {
        System.out.println("\n==============================================");
        System.out.println("                MAIN MENU");
        System.out.println("==============================================");
        System.out.println("1. Balance Inquiry");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transaction History");
        System.out.println("5. Remove Card & Exit");
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
        System.out.printf("Account Holder: %s\n", currentAccount.getAccountHolderName());
        System.out.printf("Account Number: %s\n", currentAccount.getAccountNumber());
        System.out.printf("Card: %s\n", currentAccount.getDebitCard().getMaskedCardNumber());
        System.out.printf("Available Balance: ₹%.2f\n", currentAccount.getBalance());
        
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
        System.out.printf("Available Balance: ₹%.2f\n", currentAccount.getBalance());
        System.out.print("Enter withdrawal amount: ₹");
        
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            if (currentAccount.withdraw(amount)) {
                System.out.printf("Withdrawal successful!\n");
                System.out.printf("Amount Withdrawn: ₹%.2f\n", amount);
                System.out.printf("Remaining Balance: ₹%.2f\n", currentAccount.getBalance());
                System.out.println("Please collect your cash and card.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format. Please enter a valid number.");
        }
        
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private void showTransactionHistory() {
        System.out.println("\n--- TRANSACTION HISTORY ---");
        System.out.printf("Account: %s (%s)\n", currentAccount.getAccountNumber(), 
                         currentAccount.getAccountHolderName());
        System.out.printf("Card: %s\n", currentAccount.getDebitCard().getMaskedCardNumber());
        
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
        System.out.println("\n--- REMOVING CARD ---");
        System.out.printf("Thank you for using our ATM service, %s!\n", 
                         currentAccount.getAccountHolderName());
        System.out.println("Please collect your debit card.");
        System.out.println("Have a great day!");
        currentAccount = null;
        System.out.println("Card removed successfully.\n");
    }
}

