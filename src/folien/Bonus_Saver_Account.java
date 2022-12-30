package folien;

public class Bonus_Saver_Account extends Savings_Account{
    private int penalty;
    
    public Bonus_Saver_Account(int ID, double Initial, double interest_rate) {
        super(ID, Initial, interest_rate);
        this.penalty = 25;
    }
    
    @Override
    public boolean withdraw(double amount) {
        System.out.print("Penalty incurred:\t"+ penalty +"\n");
        return super.withdraw(amount+penalty);
    }
}
