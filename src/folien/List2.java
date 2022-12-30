package folien;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class List2<T> {
    public T info;
    public List2<T> next;

    public List2(T x, List2<T> l) {
        info = x;
        next = l;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>((Arrays.stream(new Integer[]{12,23,11,12, 34, 23, 12, 34, 11}).toList()));
        Function<Integer, Integer> toSquare = o -> o * o;
        System.out.println(list.stream().map( x -> x % 2 == 0 ? x/2 : toSquare.apply(x)).toList());
        System.out.println();
        System.out.println();
    }

    public static <T> boolean isEmpty(List2<T> l) {
        return l == null;
    }

    public void insert(T x) {
        next = new List2<>(x, next);
    }

    public void delete() {
        if (next != null) next = next.next;
    }

}