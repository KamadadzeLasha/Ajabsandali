package fop.w8trees;
import java.util.Comparator;
import java.util.function.Predicate;

public class Leaf<T> implements TreeElement<T> {
    @Override
    public TreeElement<T> insert(T value, Comparator<T> comp) {
        return new InnerNode<T>(value);
    }

    @Override
    public void toString(StringBuilder sb) {
        // Intentionally left blank
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T getMin() {
        return null;
    }

    @Override
    public TreeElement<T> remove(T value, Comparator<T> comp) {
        return this;
    }

    @Override
    public boolean contains(T value, Comparator<T> comp) {
        return false;
    }

    @Override
    public int countMatches(Predicate<T> filter) {
        return 0;
    }

    @Override
    public int getAll(Predicate<T> filter, T[] array, int index) {
        return index;
    }
}
