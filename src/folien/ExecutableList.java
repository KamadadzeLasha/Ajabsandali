package folien;

public class ExecutableList<E extends Food & Executable> {
    E element;
    ExecutableList<E> next;

    public static void main(String[] args) {

    }

    void executeAll() {
        element.execute();
        if (next != null) {
            next.executeAll();
        }
    }
}
