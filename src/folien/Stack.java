package folien;

public class Stack {
    static {
        System.out.println("asdsdaf");
    }
    private List l;
    // constructor:
    public Stack() {
        l = null;
    }
    // object methods:
    public boolean isEmpty() {
        return l == null;
    }
    public int pop() {
        int result = l.info;
        l = l.next;
        return result;
    }
    public void push(int a) {
        l = new List(a,l);
    }
    public String toString() {
        return List.toString(l);
    }
}
