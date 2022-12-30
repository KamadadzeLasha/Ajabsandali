package fop.w2PinPop;
import Minijava.MiniJava;
public class PenguinPopulation extends MiniJava {
    public static void main(String[] args){
        int n, k, young,adult, senior, sum;
        n = readInt("Please insert a number:");
        young = 2;
        adult = 0;
        senior = 0;
        while (n>0){
            k = adult;
            senior = adult;
            adult = young;
            young = 2*young + k;
            n--;
        }
        sum = young + adult + senior;
        write(sum);
    }
}