package folien;

public class Bank_Account {
    protected int account;
    protected double balance;
    
    public Bank_Account(int ID, double Initial) {
        account = ID;
        balance = Initial;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            System.err.println("Amount must be greater than 0");
        }
        else {
            balance += amount;
            System.out.print("Deposit into account " + account
                    + "\n" + "Amount:\t\t" + amount + "\n"
                    + "New balance:\t" + balance + "\n\n");
        }
    }
    public boolean withdraw(double amount) {
        System.out.print("Withdrawal from account " + account
                + "\n" + "Amount:\t\t" + amount + "\n");
        if (amount > balance) {
            System.out.print("Sorry, insufficient funds...\n\n");
            return false;
        }
        balance = balance - amount;
        System.out.print("New balance:\t" + balance + "\n\n");
        return true;
    }
}