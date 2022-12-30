package folien;

public class Bank {
    public static void main(String[] args) {
        Savings_Account savings =
                new Savings_Account (4321, 5028.45, 0.00);
        Bonus_Saver_Account big_savings =
                new Bonus_Saver_Account (6543, 1475.85, -0.02);
        Checking_Account checking =
                new Checking_Account (9876,269.93, savings);
        savings.deposit (148.04);
        big_savings.deposit (41.52);
        savings.withdraw (725.55);
        big_savings.withdraw (120.38);
        checking.withdraw (320.18);
    }
}
