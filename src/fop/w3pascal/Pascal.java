package fop.w3pascal;

class Pascal extends MiniJava {
    
    public static int[][] pascal(int n) {
        if (n == 0){
            return new int[0][0];
        }
        int[][] array = new int[n][];
        for (int i = 0; i < n; i++) {
            array[i] = new int[i+1];
        }
        for (int i = 0; i < array.length; i++) {
            array[i][0] = 1;
            array[i][array[i].length-1] = 1;
            for (int j = 1; j < array[i].length-1; j++) {
                array[i][j] = array[i-1][j] + array[i-1][j-1];
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int n = readInt("Please insert number of rows:");
        int[][] array = pascal(n);
        for (int i = 0; i < array.length; i++) {
            writeConsole("n="+i+"\t");
            for (int j = 0; j < array[i].length; j++) {
                    writeConsole(array[i][j]);
                    if (j!=array[i].length-1){
                        writeConsole("\t");
                    }
            }
            writeLineConsole();
        }
    }
}
