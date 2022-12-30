package fop.w8trees;
import java.util.Comparator;
import java.util.function.Predicate;

public class InnerNode<T> implements TreeElement<T> {
    private T info;
    private TreeElement<T> left;
    private TreeElement<T> right;

    public InnerNode(T info) {
        this.info = info;
        left = new Leaf<>();
        right = new Leaf<>();
    }

    @Override
    public InnerNode<T> insert(T value, Comparator<T> comp) {
        if (comp.compare(value, info) < 0)
            left = left.insert(value, comp);
        else
            right = right.insert(value, comp);
        return this;
    }

    @Override
    public void toString(StringBuilder sb) {
        left.toString(sb);
        if (sb.length() != 0)
            sb.append(",");
        sb.append(info);
        right.toString(sb);
    }

    @Override
    public int size() {
        return 1 + left.size() + right.size();
    }

    @Override
    public T getMin() {
        if (left instanceof Leaf)
            return info;
        return left.getMin();
    }

    @Override
    public TreeElement<T> remove(T value, Comparator<T> comp) {
        if (comp.compare(value, info) < 0)
            left = left.remove(value, comp);
        else if (comp.compare(value, info) > 0)
            right = right.remove(value, comp);
        else {
            if (right instanceof Leaf)
                return left;
            info = right.getMin();
            right = right.remove(info, comp);
        }
        return this;
    }

    @Override
    public boolean contains(T value, Comparator<T> comp) {
        if (comp.compare(info, value) < 0)
            return right.contains(value, comp);
        if (comp.compare(info, value) > 0)
            return left.contains(value, comp);
        return true;
    }

    @Override
    public int countMatches(Predicate<T> filter) {
        return (filter.test(info) ? 1 : 0) + left.countMatches(filter) + right.countMatches(filter);
    }

    @Override
    public int getAll(Predicate<T> filter, T[] array, int index) {
        index = left.getAll(filter, array, index);
        if (filter.test(info))
            array[index++] = info;
        index = right.getAll(filter, array, index);
        return index;
    }
}
