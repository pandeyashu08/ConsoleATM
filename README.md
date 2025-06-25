# Secure ATM Simulation System

A console-based ATM (Automated Teller Machine) simulation built in Java with enhanced security features including debit card authentication and PIN verification. The system provides secure banking operations with Indian Rupee currency support.

## 🔐 Security Features

- **Debit Card Authentication**: 16-digit card number verification
- **PIN Protection**: 4-digit PIN validation for each transaction
- **Masked Card Display**: Card numbers shown as `4532 **** **** 9012` for privacy
- **Session Management**: Secure login/logout with card removal simulation
- **Account Privacy**: No demo accounts displayed publicly
- **Input Validation**: Comprehensive format checking for cards and PINs

## 🏦 Banking Features

- **Balance Inquiry**: Check current account balance securely
- **Deposit Money**: Add funds with card+PIN authentication
- **Withdraw Money**: Withdraw funds with balance verification
- **Transaction History**: View recent transaction records with timestamps
- **Multi-Account Support**: Each account has unique debit card and PIN
- **Indian Currency**: Full INR (₹) support with proper formatting

## 📁 Project Structure

```
ATMSimulation/
├── Transaction.java    - Transaction record management
├── DebitCard.java      - Debit card and PIN management
├── Account.java        - Account data and operations
├── ATMSystem.java      - Main ATM interface and security logic
├── Main.java          - Application entry point
└── README.md          - Project documentation
```

## 🚀 Quick Start

### Prerequisites
- Java JDK 8 or higher
- Any Java IDE (IntelliJ, Eclipse, VS Code, Cursor)
- Terminal/Command Prompt access

### Installation and Setup

1. Create project folder:
   ```bash
   mkdir ATMSimulation
   cd ATMSimulation
   ```

2. Copy the source files:
   - Transaction.java
   - DebitCard.java
   - Account.java
   - ATMSystem.java
   - Main.java

### Running the Application

**Method 1: Command Line**
```bash
javac *.java
java Main
```

**Method 2: Using IDE**
1. Open your IDE
2. Import/Open the project folder
3. Run Main.java

## 💳 Test Debit Cards

The system includes secure test accounts (card details are not displayed in terminal):

| Account Holder | Account Number | Debit Card Number | PIN  | Balance      |
|---------------|----------------|-------------------|------|--------------|
| John Doe      | ACC001         | 4532123456789012  | 1234 | ₹75,000.00   |
| Jane Smith    | ACC002         | 4532987654321098  | 5678 | ₹1,25,000.00 |
| Mike Johnson  | ACC003         | 4532456789012345  | 9012 | ₹37,500.00   |

> **Note**: For security, these card details are only provided in documentation and are not displayed in the application interface.

## 📖 Usage Guide

### 1. Card Authentication
```
Enter your 16-digit debit card number: 4532123456789012
Enter your 4-digit PIN: 1234
```

### 2. Main Menu Options
```
1. Balance Inquiry     - Check current balance
2. Deposit Money       - Add funds to account  
3. Withdraw Money      - Remove funds from account
4. Transaction History - View recent transactions
5. Remove Card & Exit  - End session securely
```

### 3. Sample Operations

**Authentication Success:**
```
Authentication successful!
Welcome, John Doe
Card: 4532 **** **** 9012
```

**Balance Check:**
```
Account Holder: John Doe
Account Number: ACC001
Card: 4532 **** **** 9012
Available Balance: ₹75,000.00
```

**Secure Deposit:**
```
Current Balance: ₹75,000.00
Enter deposit amount: ₹5000
Deposit successful!
Amount Deposited: ₹5,000.00
New Balance: ₹80,000.00
```

**Secure Withdrawal:**
```
Available Balance: ₹80,000.00
Enter withdrawal amount: ₹2000
Withdrawal successful!
Amount Withdrawn: ₹2,000.00
Remaining Balance: ₹78,000.00
Please collect your cash and card.
```

## 🏗️ Architecture

### Class Overview
- **Transaction**: Immutable transaction records with timestamps
- **DebitCard**: Secure card number and PIN management
- **Account**: User account with integrated debit card security
- **ATMSystem**: Main application controller with authentication
- **Main**: Application entry point

### Security Design Patterns
- **Card-Based Authentication**: Two-factor security (card + PIN)
- **Data Encapsulation**: Private card details with controlled access
- **Input Validation**: Format verification for cards and PINs
- **Session Management**: Secure card insert/remove simulation
- **Privacy Protection**: Masked card number display

## 🔒 Security Implementation

### Authentication Flow
1. **Card Input**: 16-digit card number validation
2. **PIN Verification**: 4-digit PIN authentication
3. **Session Creation**: Secure account access
4. **Transaction Authorization**: PIN required for all operations
5. **Session Termination**: Secure card removal

### Data Protection
- Card numbers masked in display (`4532 **** **** 9012`)
- PINs never displayed or logged
- Account details hidden from public view
- Secure in-memory storage only

## 🛠️ Technical Details

### Requirements
- **Java Version**: JDK 8+
- **Memory**: Minimal (< 50MB)
- **Dependencies**: None (Java standard library only)
- **Security**: Card-based authentication system

### Key Libraries Used
- `java.util.Scanner` - Secure user input handling
- `java.util.HashMap` - Encrypted account storage
- `java.util.ArrayList` - Transaction audit trail
- `java.time.LocalDateTime` - Transaction timestamps
- `java.util.Random` - Secure card/PIN generation

## 🔧 Troubleshooting

### Common Issues

**"Invalid card number format"**
- Ensure exactly 16 digits
- Remove any spaces or dashes
- Check for typos in card number

**"Invalid PIN format"**
- PIN must be exactly 4 digits
- No letters or special characters
- Check caps lock is off

**"Card not recognized"**
- Verify card number is correct
- Check against test cards provided
- Ensure no extra spaces

**Authentication Issues**
- Card number and PIN must match exactly
- Case-sensitive validation
- Try test cards from documentation

## 🚀 Future Enhancements

- **Advanced Security**: Biometric authentication, card encryption
- **Banking Features**: Fund transfers, bill payments, mini statements
- **Account Management**: Multiple account types, joint accounts
- **Limits & Controls**: Daily limits, transaction restrictions
- **Integration**: Database connectivity, real banking APIs
- **User Interface**: GUI application, mobile app version
- **Reporting**: Detailed statements, tax reports
- **Multi-language**: Regional language support

## 📋 Test Scenarios

### Security Testing
1. **Invalid Card Numbers**: Test with wrong formats
2. **Incorrect PINs**: Verify PIN validation
3. **Card Not Found**: Test with non-existent cards
4. **Session Security**: Verify proper logout/card removal

### Functional Testing
1. **Balance Operations**: Check all balance inquiries
2. **Deposit Testing**: Various deposit amounts
3. **Withdrawal Testing**: Including insufficient funds
4. **Transaction History**: Verify proper recording

## 📄 License

This project is created for educational purposes demonstrating secure banking system implementation. Feel free to use, modify, and distribute for learning purposes.

## 👨‍💻 Development

**Secure ATM Simulation System** - Enhanced Console Based Banking Application with Debit Card Authentication

### Key Security Improvements
- ✅ Debit card authentication system
- ✅ PIN-based security for all operations  
- ✅ Masked card number display
- ✅ No public display of demo accounts
- ✅ Secure session management
- ✅ Input validation and error handling

---

### 📞 Support

For issues or questions:
1. Check the troubleshooting section above
2. Verify your Java installation and version
3. Ensure all source files are properly saved
4. Test with provided debit card credentials
5. Check card number and PIN format requirements

**Remember**: This is a simulation system for educational purposes. In production banking systems, additional security layers including encryption, HSM modules, and regulatory compliance would be required.