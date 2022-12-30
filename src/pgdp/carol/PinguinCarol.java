package pgdp.carol;

import pgdp.MiniJava;

public class PinguinCarol extends MiniJava {
    public static void main(String[] args) {
        int width = readInt("Enter the playing field width:");
        int height = readInt("Enter pitch height:");
        if (width <= 0 || height <= 0) {
            write("The width and height of the playing field must be greater than zero.");
            return;
        }
        int[][] array = new int[width][height];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = randomInt(-1, 9);
            }
        }
        printPlayground(array);
        int startingX = readInt("Starting position x:");
        int startingY = readInt("Starting position y:");
        int direction = readInt("Direction of view at the beginning:");
        int iceBlock = readInt("Ice blocks at the beginning:");
        if (startingY < 0 || startingY >= height || startingX < 0 || startingX >= width || direction < 0 || direction > 3 || iceBlock < 0 || iceBlock > 10) {
            write("Invalid start values.");
            return;
        }
        char instruction = readChar("Enter instruction:");
        
    }
}
