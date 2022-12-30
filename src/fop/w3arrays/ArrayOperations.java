package fop.w3arrays;

public class ArrayOperations {
    public static int square(int[] v) {
        int sum = 0;
        for (int j : v) {
            sum += j * j;
        }
        return sum;
    }

    public static int[][] scalarMultiplication(int[][] m, int s) {
        if (m.length == 0) {
            return new int[0][0];
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = m[i][j] * s;
            }
        }
        return m;
    }

    public static int[][] transpose(int[][] m) {
        if (m.length == 0 ){
            return new int[0][0];
        }
        int[][] result = new int[m[0].length][m.length];
        for (int i = 0; i < result.length; i++)
            for (int j = 0; j < result[0].length; j++)
                m[i][j] = m[j][i];
        return result;
    }

    public static void printmat(int[][] m) {
        for (int[] ints : m) {
            System.out.print("| ");
            for (int j = 0; j < m[0].length; j++)
                System.out.print(ints[j] + " ");
            System.out.println("| ");
        }
    }

    public static void main(String[] args) {
        int[][] m = new int[10][10];
        for (int i = 0; i < m.length; i++) {
            int k = 0;
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = k++;
            }
        }
        printmat(m);
        System.out.println();
        printmat(scalarMultiplication(m,3));

    }
}
