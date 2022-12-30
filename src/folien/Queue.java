package folien;

public class Queue {
    private List first, last;
    // constructor:
    public Queue() {
        first = last = null;
    }
    // object methods:
    public boolean isEmpty() {
        return first == null;
    }
    public int dequeue() {
        int result = first.info;
        if (last == first) last = null;
        first = first.next;
        return result;
    }
    public Queue enqueue(int x) {
        if (first == null) first = last = new List(x);
        else { last = last.next = new List(x); }
        return this;
    }
    public String toString() {
        return List.toString(first);
    }

    public static void main(String[] args) {
        Queue haha = new Queue();
        haha.enqueue(12).enqueue(14).enqueue(15);
        System.out.println(List.merge(haha.first,new List(12,new List(12))));
    }
}