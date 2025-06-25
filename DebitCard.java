// DebitCard.java
import java.util.Random;

class DebitCard {
    private String cardNumber;
    private String pin;
    private String accountNumber;
    
    public DebitCard(String accountNumber) {
        this.accountNumber = accountNumber;
        this.cardNumber = generateCardNumber();
        this.pin = generatePin();
    }
    
    // Constructor for predefined card details (for testing)
    public DebitCard(String accountNumber, String cardNumber, String pin) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }
    
    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNum = new StringBuilder();
        
        // First 4 digits - Bank identifier
        cardNum.append("4532"); // Visa prefix for simulation
        
        // Next 12 digits - Random
        for (int i = 0; i < 12; i++) {
            cardNum.append(random.nextInt(10));
        }
        
        return cardNum.toString();
    }
    
    private String generatePin() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }
    
    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }
    
    public String getCardNumber() { return cardNumber; }
    public String getPin() { return pin; }
    public String getAccountNumber() { return accountNumber; }
    
    public String getMaskedCardNumber() {
        return cardNumber.substring(0, 4) + " **** **** " + cardNumber.substring(12);
    }
}

