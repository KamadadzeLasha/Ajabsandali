package fop.w3box;
import Minijava.MiniJava;
public class CoverTheTiles extends MiniJava {
    public static void main (String[] args) {
        int k, i, b, rollP1_1, rollP1_2, rollP2_1, rollP2_2, sum1, sum2, tile1_1, tile1_2, tile2_1, tile2_2, credits1, credits2;
        credits1 = credits2 = 0;
        boolean win = false;
        boolean[] tiles = new boolean[ 10 ];
        for (k = 0; k < 10; k++) {
            tiles[ k ] = true;
        }

        for( i = 0; i < 10; i++) {
            rollP1_1 = dice ();
            rollP1_2 = dice ();
            sum1 = rollP1_1 + rollP1_2;
            while (true) {
                write ("Player 1 numbers:");
                write (rollP1_1);
                write (rollP1_2);
                outputTiles (tiles);
                write("Which tiles to cover by player 1? (0 for no valid combination)");
                tile1_1 = readInt ("Tile 1:");
                tile1_2 = readInt ("Tile 2:");
                if(tile1_2 == 0 || tile1_1 == 0) {
                    for (k = 0; k < 10; k++) {
                        if(tiles[ k ]) {
                            credits1 += k;
                        }
                    }
                    break;
                } else if(tile1_1 > 0 && tile1_2 > 0 && tile1_1 != tile1_2 && tile1_1 <= 10 && tile1_2 <= 10 && tiles[ tile1_1 - 1 ] && tiles[ tile1_2 - 1 ] && tile1_1 + tile1_2 == sum1) {
                    tiles[ tile1_1 - 1 ] = tiles[ tile1_2 - 1 ] = false;
                    b = 0;
                    for (k = 0; k < 10; k++) {
                        if(! tiles[ k ]) {
                            b++;
                            if(b == 10) {
                                i = 10;
                                win = true;
                                write (" Player 1 wins!");
                            }
                        }
                    }
                    break;
                }
            }
            if(i == 10) {
                break;
            }
            rollP2_1 = dice ();
            rollP2_2 = dice ();
            sum2 = rollP2_1 + rollP2_2;
            while (true) {
                write ("Player 2 numbers:");
                write (rollP2_1);
                write (rollP2_2);
                outputTiles (tiles);
                write("Which tiles to cover by player 2? (0 for no valid combination)");
                tile2_1 = readInt ("Tile 1:");
                tile2_2 = readInt ("Tile 2:");
                if(tile2_2 == 0 || tile2_1 == 0) {
                    for (k = 0; k < 10; k++) {
                        if(tiles[ k ]) {
                            credits2 += k;
                        }
                    }
                    break;
                } else if(tile2_1 > 0 && tile2_2 > 0 && tile2_1 != tile2_2 && tile2_1 <= 10 && tile2_2 <= 10 && tiles[ tile2_1 - 1 ] && tiles[ tile2_2 - 1 ] && tile2_1 + tile2_2 == sum2) {
                    b = 0;
                    tiles[ tile2_1 - 1 ] = tiles[ tile2_2 - 1 ] = false;
                    for (k = 0; k < 10; k++) {
                        if(! tiles[ k ]) {
                            b++;
                            if(b == 10) {
                                i = 10;
                                win = true;
                                write (" Player 2 wins!");
                            }
                        }
                    }
                    break;
                }
            }
        }
        if(! win) {
            if(credits2 < credits1) {
                write (" Player 2 wins!");
            } else if(credits1 < credits2) {
                write (" Player 1 wins!");
            } else {
                write ("draw!");
            }
        }
    }

    public static void outputTiles (boolean[] tiles) {
        StringBuilder sb = new StringBuilder ("Open tiles: 1:");
        sb.append (tiles[ 0 ]);
        for (int a = 1; a < tiles.length; a++) {
            sb.append (" ").append (a + 1).append (":").append (tiles[ a ]);
        }
        write (sb.toString ());
    }
}