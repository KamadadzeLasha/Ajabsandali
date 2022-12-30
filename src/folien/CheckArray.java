package folien;

public class CheckArray  {
    public static boolean check(Object[] a, Property p) {
        for (Object o : a) if (p.test(o)) {
            return true;
        }
        return false;
    }

    public static void main(String[]  args) {
        Property prop = new Property() {
            @Override
            public boolean test(Object r) {
                return r != null;
            }
            public int r() {
                return 0;
            }
        };
        System.out.println(check(args,prop));
    }
}