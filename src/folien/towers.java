package folien;

public class towers {
    public static void main(String[] args) {
        move(4, (byte) 0, (byte) 1);
    }
    public static byte free (byte a, byte b) {
        return switch (a + b) {
            case 1 -> 2;
            case 2 -> 1;
            case 3 -> 0;
            default -> -1;
        };
    }
    public static void move (int h, byte a, byte b) {
        if (h > 0){
            byte c = free(a,b);
            move(h-1,a,c);
            System.out.println(a + " move to "+c);
            move(h -1,b,c);
        }
    }
}
