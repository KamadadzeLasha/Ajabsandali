package folien;

public interface Predicate<T> {
    boolean test(T t);

    default Predicate<T> negate() {
        return (T t) -> !test(t);
    }

    default Predicate<T> and(Predicate< ? super T > other) {
        return (T t) -> this.test(t) && other.test(t);
    }
}
