package ATM_Lab2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        // Step 1: Setup Manager
        if (atm.getManager() == null) {
            System.out.println("No manager found. Please create a manager account.");
            System.out.print("Enter Manager ID Card: ");
            String idCard = scanner.nextLine();
            System.out.print("Enter Manager Full Name: ");
            String fullName = scanner.nextLine();
            System.out.print("Enter Manager Gender: ");
            String gender = scanner.nextLine();
            System.out.print("Enter Manager Login ID: ");
            String loginID = scanner.nextLine();
            System.out.print("Enter Manager Password: ");
            String password = scanner.nextLine();

            Manager manager = new Manager(idCard, fullName, gender, loginID, password);
            atm.setManager(manager);
            System.out.println("Manager account created successfully!");
        }

        // Step 2: Manager Login
        while (true) {
            System.out.println("\nManager Login");
            System.out.print("Enter Login ID: ");
            String loginID = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (atm.getManager().getLoginID().equals(loginID) &&
                atm.getManager().authenticate(password)) {
                System.out.println("Manager login successful!");
                break;
            } else {
                System.out.println("Invalid Login ID or Password. Try again.");
            }
        }

        // Step 3: Add Accounts
        System.out.print("\nEnter the number of accounts to create (max 5): ");
        int numAccounts = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numAccounts; i++) {
            System.out.println("Enter details for account " + (i + 1));
            System.out.print("ID Card: ");
            String idCard = scanner.nextLine();
            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();
            System.out.print("Gender: ");
            String gender = scanner.nextLine();
            System.out.print("Login ID: ");
            String loginID = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.print("Initial Balance: ");
            double balance = Double.parseDouble(scanner.nextLine());

            atm.accounts.add(new Account(idCard, fullName, gender, loginID, password, balance));
        }

        // Step 4: ATM Operations
        while (true) {
            System.out.println("\nATM ComputerThanyaburi Bank");
            System.out.print("Enter Account Login ID: ");
            String loginID = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            Account currentAccount = null;
            for (Account acc : atm.accounts) {
                if (acc.getLoginID().equals(loginID) && acc.authenticate(password)) {
                    currentAccount = acc;
                    break;
                }
            }

            if (currentAccount == null) {
                System.out.println("Invalid Login ID or Password.");
                continue;
            }

            while (true) {
                System.out.println("\nMenu Service");
                System.out.println("1. Check Balance");
                System.out.println("2. Withdraw");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int option = Integer.parseInt(scanner.nextLine());

                if (option == 1) {
                    atm.checkBalance(currentAccount);
                } else if (option == 2) {
                    System.out.print("Enter amount to withdraw: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    atm.withdraw(currentAccount, amount);
                } else if (option == 3) {
                    System.out.print("Enter amount to deposit: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    atm.deposit(currentAccount, amount);
                } else if (option == 4) {
                    System.out.print("Enter target account Login ID: ");
                    String targetLoginID = scanner.nextLine();
                    Account targetAccount = null;
                    for (Account acc : atm.accounts) {
                        if (acc.getLoginID().equals(targetLoginID)) {
                            targetAccount = acc;
                            break;
                        }
                    }
                    if (targetAccount != null) {
                        System.out.print("Enter amount to transfer: ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        atm.transfer(currentAccount, targetAccount, amount);
                    } else {
                        System.out.println("Target account not found.");
                    }
                } else if (option == 5) {
                    System.out.println("Exiting to main menu...");
                    break;
                } else {
                    System.out.println("Invalid option. Try again.");
                }
            }
        }
    }
}
