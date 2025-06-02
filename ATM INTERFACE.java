import java.util.Scanner;

public class ATMSimulation {
    private static class BankAccount {
        private String accountNumber;
        private String accountHolder;
        private double balance;
        private int pin;

        public BankAccount(String accountNumber, String accountHolder, double initialBalance, int pin) {
            this.accountNumber = accountNumber;
            this.accountHolder = accountHolder;
            this.balance = initialBalance;
            this.pin = pin;
        }

        public boolean verifyPin(int enteredPin) {
            return this.pin == enteredPin;
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                return true;
            }
            return false;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            }
        }

        public double getBalance() {
            return balance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getAccountHolder() {
            return accountHolder;
        }
    }

    private static class ATM {
        private BankAccount currentAccount;
        private Scanner scanner;

        public ATM() {
            this.scanner = new Scanner(System.in);
        }

        public void start() {
            System.out.println("\n╔════════════════════════════════╗");
            System.out.println("║   WELCOME TO NEON BANK ATM    ║");
            System.out.println("╚════════════════════════════════╝");

            // Simulate account creation (in real app, this would come from a database)
            BankAccount demoAccount = new BankAccount("100120023003", "John Doe", 5000.00, 1234);

            if (authenticateUser(demoAccount)) {
                currentAccount = demoAccount;
                showMainMenu();
            } else {
                System.out.println("\n⚠️ Authentication failed. Please try again later.");
            }

            scanner.close();
        }

        private boolean authenticateUser(BankAccount account) {
            System.out.print("\n🔒 Please enter your 4-digit PIN: ");
            try {
                int enteredPin = Integer.parseInt(scanner.nextLine());
                return account.verifyPin(enteredPin);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private void showMainMenu() {
            boolean exit = false;

            while (!exit) {
                System.out.println("\n════════════ MAIN MENU ════════════");
                System.out.println("1. 💵 Withdraw Cash");
                System.out.println("2. 💰 Deposit Funds");
                System.out.println("3. 📊 Check Balance");
                System.out.println("4. ℹ️  Account Information");
                System.out.println("5. 🚪 Exit");
                System.out.print("\nPlease select an option (1-5): ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        withdrawCash();
                        break;
                    case "2":
                        depositFunds();
                        break;
                    case "3":
                        checkBalance();
                        break;
                    case "4":
                        showAccountInfo();
                        break;
                    case "5":
                        exit = true;
                        System.out.println("\nThank you for using NEON BANK ATM. Have a nice day! 👋");
                        break;
                    default:
                        System.out.println("\n⚠️ Invalid option. Please try again.");
                }
            }
        }

        private void withdrawCash() {
            System.out.println("\n══════════ WITHDRAWAL ══════════");
            System.out.println("Available denominations: $20, $50, $100");
            System.out.print("Enter amount to withdraw: $");

            try {
                double amount = Double.parseDouble(scanner.nextLine());

                if (amount <= 0) {
                    System.out.println("\n⚠️ Amount must be positive.");
                    return;
                }

                if (amount % 20 != 0 && amount % 50 != 0 && amount % 100 != 0) {
                    System.out.println("\n⚠️ Please enter amount in valid denominations ($20, $50, $100).");
                    return;
                }

                if (currentAccount.withdraw(amount)) {
                    System.out.printf("\n✅ Success! $%.2f has been withdrawn.\n", amount);
                    System.out.printf("New balance: $%.2f\n", currentAccount.getBalance());
                } else {
                    System.out.println("\n⚠️ Transaction failed. Insufficient funds or invalid amount.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n⚠️ Invalid amount. Please enter numbers only.");
            }
        }

        private void depositFunds() {
            System.out.println("\n════════════ DEPOSIT ════════════");
            System.out.print("Enter amount to deposit: $");

            try {
                double amount = Double.parseDouble(scanner.nextLine());

                if (amount <= 0) {
                    System.out.println("\n⚠️ Amount must be positive.");
                    return;
                }

                currentAccount.deposit(amount);
                System.out.printf("\n✅ Success! $%.2f has been deposited.\n", amount);
                System.out.printf("New balance: $%.2f\n", currentAccount.getBalance());
            } catch (NumberFormatException e) {
                System.out.println("\n⚠️ Invalid amount. Please enter numbers only.");
            }
        }

        private void checkBalance() {
            System.out.println("\n══════════ BALANCE ═══════════");
            System.out.printf("Current balance: $%.2f\n", currentAccount.getBalance());
        }

        private void showAccountInfo() {
            System.out.println("\n════════ ACCOUNT INFO ════════");
            System.out.println("Account Holder: " + currentAccount.getAccountHolder());
            System.out.println("Account Number: " + currentAccount.getAccountNumber());
            System.out.printf("Current Balance: $%.2f\n", currentAccount.getBalance());
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}