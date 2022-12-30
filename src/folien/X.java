package folien;

import java.util.ArrayList;
import java.util.List;

public class X {
    public static List<Integer> list = new ArrayList<>();
    static int x = 0 ;
    public X(){
        list.add(x++);
    }
}
