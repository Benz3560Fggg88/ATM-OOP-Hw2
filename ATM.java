package ATM_Lab2;

import java.util.ArrayList;

public class ATM implements ATMAction {
    public ArrayList<Account> accounts = new ArrayList<>();
    private Manager manager;

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    @Override
    public void checkBalance(Account account) {
        System.out.println("Current Balance: " + account.getBalance());
    }

    @Override
    public void withdraw(Account currentAccount, double amount) {
        if (currentAccount.withdraw(amount)) {
            System.out.println("Withdrawn: " + amount);
            System.out.println("Current balance: " + currentAccount.getBalance()); // แสดงยอดเงินคงเหลือ
        } else {
            System.out.println("Withdrawal failed. Insufficient balance or invalid amount.");
        }
    }
    
    @Override
    public void deposit(Account currentAccount, double amount) {
        if (amount > 0) {
            currentAccount.deposit(amount);
            System.out.println("Deposited: " + amount);
            System.out.println("Current balance: " + currentAccount.getBalance()); // แสดงยอดเงินคงเหลือ
        } else {
            System.out.println("Deposit amount must be greater than 0.");
        }
    }

    @Override
    public void transfer(Account sourceAccount, Account targetAccount, double amount) {
        if (sourceAccount.transfer(targetAccount, amount)) {
            System.out.println("Transfer successful: " + amount + " to " + targetAccount.getFullName());
        } else {
            System.out.println("Transfer failed. Insufficient funds or invalid amount.");
        }
    }
}
