package fop.w4fish;

import Minijava.MiniJava;

import java.util.Random;

public class Penguin extends MiniJava {
    public static int Old_Path_active = 5;
    public static void main(String[] args){
        int n = 10;
        Random random = new Random();
        int pinguRow = 0;
        int pinguColumn = 0;
        int[][] world = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                world[i][j] = random.nextInt(0,3);
            }
        }
        world[n-1][n-1] = 4;


        write(""+isFishReachable(world, pinguRow, pinguColumn));
    }
    public static void printWorld(int[][] world, int pingux, int pinguy) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < world.length ; i++) {
            int k = world.length-world[0].length;
            for (int j = 0; j < world[0].length; j++) {
                k++;
                switch (world[i][j]) {
                    case 0 -> sb.append("L");
                    case 1 -> sb.append("I");
                    case 2 -> sb.append("W");
                    case 3 -> sb.append("S");
                    case 4 -> sb.append("F");
                    case 5 -> sb.append(":D");
                }
                if (i == pingux  && j == pinguy) sb.append("(P)");
                if (k != world.length) sb.append("\t");
                else if ((i+1)*(j+1) != world.length*world[0].length){
                    sb.append("\n");
                }
            }
        }
        write (sb.toString());
    }
    public static boolean isFishReachable(int[][] world, int pinguRow, int pinguColumn){
        printWorld(world, pinguRow, pinguColumn);
        System.out.println();
        if(pinguRow < 0 || pinguColumn < 0 || pinguRow >= world.length || pinguColumn >= world[0].length){
            return false;
        }
        if (world[pinguRow][pinguColumn] == 5){
            return false;
        }
        switch (world[pinguRow][pinguColumn]) {
            case 0:
                world[pinguRow][pinguColumn] = Old_Path_active;
                return isFishReachable(world, pinguRow + 1, pinguColumn) || isFishReachable(world, pinguRow - 1, pinguColumn) || isFishReachable(world, pinguRow, pinguColumn + 1) || isFishReachable(world, pinguRow, pinguColumn - 1);
            case 1:
                world[pinguRow][pinguColumn] = Old_Path_active;
                return isFishReachable(world, pinguRow + 1, pinguColumn + 1) || isFishReachable(world, pinguRow + 1, pinguColumn - 1) || isFishReachable(world, pinguRow - 1, pinguColumn + 1) || isFishReachable(world, pinguRow - 1, pinguColumn - 1);
            case 2:
                world[pinguRow][pinguColumn] = Old_Path_active;
                return isFishReachable(world, pinguRow - 3, pinguColumn + 3) || isFishReachable(world, pinguRow - 3, pinguColumn - 3) || isFishReachable(world, pinguRow + 3, pinguColumn + 3) || isFishReachable(world, pinguRow + 3, pinguColumn - 3);
            case 4:
                return true;
            default: return false;
        }
    }
    public static int[][] generateExampleWorldOne() {
        return new int[][]{
                {2, 3, 3, 3, 3, 3},
                {3, 0, 3, 3, 4, 3},
                {3, 3, 3, 3, 3, 1},
                {3, 3, 3, 0, 1, 3},
                {3, 3, 3, 3, 3, 3}};
    }
    public static int[][] generateExampleWorldTwo(){
        return new int[][] {{0,0,0,2}, {0,0,0,1}, {0,1,3,4}, {3,4,3,3}};
    }
}