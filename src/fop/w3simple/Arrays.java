package fop.w3simple;

public class Arrays extends MiniJava {
    public static void print(int[] arr) {
        writeConsole("{");
        if (arr.length > 0) {
            writeConsole(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                writeConsole(", " + arr[i]);
            }
        }
        writeConsole("}");
    }
    
    public static void minAndMax(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        int min = arr[0];
        int max = arr[0];
    
        for (int j : arr) {
            if (j < min)
                min = j;
            if (j > max)
                max = j;
        }
        writeConsole("Minimum = " + min + ", Maximum = " + max);
    }
    
    public static int[] invert(int[] arr) {
        int[] inverted = new int[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            inverted[inverted.length - 1 - i] = arr[i];
        }
        
        return inverted;
    }
    
    public static int[] cut(int[] arr, int len) {
        if (len <= 0) {
            return new int[0];
        }
        
        int[] result = new int[len];
        
        for (int i = 0; i < len && i < arr.length; i++) {
            result[i] = arr[i];
        }
        
        return result;
    }
    
    public static int[] linearize(int[][] arr) {
        int len = 0;
    
        for (int[] value : arr) {
            len = len + value.length;
        }
        
        int[] linearized = new int[len];
        int linIndex = 0;
    
        for (int[] ints : arr) {
            for (int anInt : ints) {
                linearized[linIndex++] = anInt;
            }
        }
        
        return linearized;
    }
}
