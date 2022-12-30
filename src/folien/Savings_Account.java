package folien;

public class Savings_Account extends Bank_Account{
    protected double interest_rate;
    
    public Savings_Account(int ID, double Initial, double interest_rate) {
        super(ID, Initial);
        this.interest_rate = interest_rate;
    }
    public void add_interest(){
        balance *= (1 + interest_rate);
        System.out.print("Interest added to account: "+ account
                +"\nNew balance:\t"+ balance +"\n\n");
    }
}
