package folien;

public class Generics<T,A> {
    private T T;
    private A a;

    public Generics(T t, A a) {
        T = t;
        this.a = a;
    }

    public T getT() {
        return T;
    }

    public A getA() {
        return a;
    }
}
