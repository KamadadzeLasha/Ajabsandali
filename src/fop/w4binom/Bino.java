package fop.w4binom;

public class Bino extends MiniJava {
    public static int bino(int n, int k) {
        if (n == k || k == 0)
            return 1;
        else
            return bino(n - 1, k - 1) + bino(n - 1, k);
    }
    
    public static void main(String[] args) {
        int n = readInt("n:");
        int k = readInt("k:");
        
        if (n >= k && k >= 0)
            write(bino(n, k));
        else
            write("Invalid input");
    }
}
