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
        int[][] map = new int[width][height];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = randomInt(-1, 9);
            }
        }
        printPlayground(map);
        int startingX = readInt("Starting position x:");
        int startingY = readInt("Starting position y:");
        int direction = readInt("Direction of view at the beginning:");
        int iceBlock = readInt("Ice blocks at the beginning:");
        if (startingY < 0 || startingY >= height || startingX < 0 || startingX >= width || direction < 0 || direction > 3 || iceBlock < 0 || iceBlock > 10) {
            write("Invalid start values.");
            return;
        }
        char instruction = readChar("Enter instruction:");
        while (instruction != 'e') {
            if (instruction == 'a') {
                printPlayground(map);
            } else if (instruction == 'r') {
                direction = (direction + 3) % 4;
            } else if (instruction == 'l') {
                direction = (direction + 1) % 4;
            } else if (instruction == 's') {
                switch (direction) {
                    case 0 -> {
                        if (startingX + 1 > height) {
                            write("Carol cannot leave the field.");
                        } else if (Math.abs(map[startingX + 1][startingY] - map[startingX][startingY]) > 1) {
                            write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
                        } else {
                            startingX++;
                        }
                    }
                    case 1 -> {
                        if (startingY - 1 < 0) {
                            write("Carol cannot leave the field.");
                        } else if (Math.abs(map[startingX][startingY - 1] - map[startingX][startingY]) > 1) {
                            write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
                        } else {
                            startingY--;
                        }
                    }
                    case 2 -> {
                        if (startingX - 1 < 0) {
                            write("Carol cannot leave the field.");
                        } else if (Math.abs(map[startingX - 1][startingY] - map[startingX][startingY]) > 1) {
                            write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
                        } else {
                            startingX--;
                        }
                    }
                    case 3 -> {
                        if (startingY + 1 > width) {
                            write("Carol cannot leave the field.");
                        } else if (Math.abs(map[startingX][startingY + 1] - map[startingX][startingY]) > 1) {
                            write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
                        } else {
                            startingY++;
                        }
                    }
                }
            } else if (instruction == 'n') {
                if (iceBlock > 10) {
                    write("Carol can't take a block of ice, she's already carrying ten.");
                } else if (map[startingX][startingY] == -1) {
                    write("Carol cannot take blocks of ice in the water.");
                } else {
                    switch (direction) {
                        case 0 -> {
                            if (startingX + 1 > height) {
                                write("Carol cannot take blocks of ice off the field.");
                            } else if (map[startingX + 1][startingY] == -1) {
                                write("Carol can't take a block of ice, there aren't any left.");
                            } else {
                                map[startingX + 1][startingY]--;
                                iceBlock++;
                            }
                        }
                        case 1 -> {
                            if (startingY - 1 < 0) {
                                write("Carol cannot take blocks of ice off the field.");
                            } else if (map[startingX][startingY - 1] == -1) {
                                write("Carol can't take a block of ice, there aren't any left.");
                            } else {
                                map[startingX][startingY - 1]--;
                                iceBlock++;
                            }
                        }
                        case 2 -> {
                            if (startingX - 1 < 0) {
                                write("Carol cannot take blocks of ice off the field.");
                            } else if (map[startingX - 1][startingY] == -1) {
                                write("Carol can't take a block of ice, there aren't any left.");
                            } else {
                                map[startingX - 1][startingY]--;
                                iceBlock++;
                            }
                        }
                        case 3 -> {
                            if (startingY + 1 > width) {
                                write("Carol cannot take blocks of ice off the field.");
                            } else if (map[startingX][startingY + 1] == -1) {
                                write("Carol can't take a block of ice, there aren't any left.");
                            }else {
                                map[startingX][startingY + 1]--;
                                iceBlock++;
                            }
                        }
                    }
                }
            }else if (instruction == 'p') {
                if (iceBlock == 0) {
                    write("Carol can't lay a block of ice because she isn't carrying one.");
                } else if (map[startingX][startingY] == -1) {
                    write("Carol cannot lay blocks of ice in the water.");
                } else {
                    switch (direction) {
                        case 0 -> {
                            if (startingX + 1 > height) {
                                write("Carol cannot put blocks of ice off the field of play.");
                            } else if (map[startingX + 1][startingY] == 9) {
                                write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
                            } else {
                                map[startingX + 1][startingY]++;
                                iceBlock--;
                            }
                        }
                        case 1 -> {
                            if (startingY - 1 < 0) {
                                write("Carol cannot put blocks of ice off the field of play.");
                            } else if (map[startingX][startingY - 1] == 9) {
                                write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
                            } else {
                                map[startingX][startingY - 1]++;
                                iceBlock--;
                            }
                        }
                        case 2 -> {
                            if (startingX - 1 < 0) {
                                write("Carol cannot put blocks of ice off the field of play.");
                            } else if (map[startingX - 1][startingY] == 9) {
                                write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
                            } else {
                                map[startingX - 1][startingY]++;
                                iceBlock--;
                            }
                        }
                        case 3 -> {
                            if (startingY + 1 > width) {
                                write("Carol cannot put blocks of ice off the field of play.");
                            } else if (map[startingX][startingY + 1] == 9) {
                                write("Carol can't lay a block of ice, there are already ten blocks of ice on the field.");
                            }else {
                                map[startingX][startingY + 1]++;
                                iceBlock--;
                            }
                        }
                    }
                }
            }else {
                write("Unknown instruction!");
            }
            instruction = readChar("Enter instruction:");
        }
    }
}
