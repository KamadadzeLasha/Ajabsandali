package folien;

public class Checking_Account extends Bank_Account{
    private Savings_Account overdraft;
    
    public Checking_Account(int ID, double Initial, Savings_Account overdraft) {
        super(ID, Initial);
        this.overdraft = overdraft;
    }
    public boolean withdraw(double amount) {
        if (super.withdraw(amount)) return true;
        System.out.print("Using overdraft...\n");
        if (overdraft.withdraw(amount-balance)) {
            balance = 0;
            System.out.print("New balance on account "+
                    account +": 0\n\n");
            return true;
        }
        System.out.print("Overdraft source insufficient.\n\n");
        return false;
    }
}
