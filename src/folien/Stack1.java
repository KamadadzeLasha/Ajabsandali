package folien;

import java.util.Arrays;

public class Stack1 {
    private int sp;
    private int[] a;
    // constructors:
    public Stack1() {
        sp = -1; a = new int[4];
    }
    // object methods:
    public boolean isEmpty() {
        return (sp<0);
    }
    public int pop() {
        int result = a[sp];
        sp--;
        if (sp == a.length/4 && sp>=2) {
            int[] b = new int[2*sp];
            System.arraycopy(a, 0, b, 0, sp + 1);
            a = b;
        }
        return result;
    }
    public void push(int x) {
        ++sp;
        if (sp == a.length) {
            int[] b = new int[2*sp];
            System.arraycopy(a, 0, b, 0, sp);
            a = b;
        }
        a[sp] = x;
    }
    @Override
    public String toString() {
        return "Stack1{" +
                "sp=" + sp +
                ", a=" + Arrays.toString(a) +
                '}';
    }
}