package fop.w2fac;

public class Factorization extends MiniJava {
    public static void main(String[] args){
        int n = readInt("Please insert a number:" );
        if (n <= 1){
            write( "Error: n>1 expected!");
        }
        else{
            int divisor =2;
            while(n>1){
                if (n%divisor == 0){
                    writeConsole(divisor);
                    n /= divisor;
                    if (n != 1){
                        writeConsole(" ");
                    }
                }
                else {
                    divisor ++;
                }
            }
            writeLineConsole();
        }
    }
}
