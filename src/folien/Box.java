package folien;

public class Box<T> {
    private T item;

    public Box(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    // This method uses an unbounded wildcard
    public void inspect(Box<?> box) {
        System.out.println("Box contents: " + box.getItem());
    }

    public static void main(String[] args) {
        Box<Pizza> stringBox = new Box<>(new Pizza(123,12,"yeammy"));
        Box<List> intBox = new Box<>(new List(12));
        intBox.inspect(stringBox);
    }
}
