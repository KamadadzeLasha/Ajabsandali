package folien;

public class List {
    public int info;

    public List next;
    // constructors:
    public List (int x, List l) {
        info = x ;
        next = l ;
    }
    public List (int x) {
        info = x;
        next = null;
    }
    public void insert(int x) {
        next = new List(x,next);
    }
    public void delete() {
        if (next != null)
            next = next.next;
    }
    public static boolean isEmpty(List l) {
        return l == null;
    }
    public static String toString(List l) {
        if (l == null)
            return "[]";
        else
            return l.toString();
    }
    public String toString() {
        String result = "["+info;
        for(List t=next; t!=null; t=t.next)
            result = result+", "+t.info;
        return result+"]";
    }
    public static List merge(List a, List b) {
        if (b == null)
            return a;
        if (a == null)
            return b;
        if (b.info > a.info) {
            a.next = merge(a.next, b);
            return a;
        } else {
            b.next = merge(a, b.next);
            return b;
        }
    }
    public static List sort(List a) {
        if (a == null || a.next == null)
            return a;
        List b = a.half(); // divide!
        a = sort(a);
        b = sort(b);
        return merge(a,b);
    }
    public List half() {
        if (next == null) return null;
        List result = next;
        next = next.half();
        return result;
    }

    public static void main(String[] args) {
        List as = new List(12);
        List sa = new List(23,as);
        List asa = new List(15,sa);
        List sas = new List(34,asa);
        List sasa = new List(123,sas);
        as.insert(12);
        as.insert(24);
        as.insert(23);
        System.out.println(sort(sasa));
    }
}