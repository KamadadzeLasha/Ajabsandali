package folien;

public class generics<T,S,D> extends Generics{
    private T T;
    private S S;
    private D d;

    public generics(Object o, Object o2, T t, S s, D d) {
        super(o, o2);
        T = t;
        S = s;
        this.d = d;
    }

    public static void main(String[] args) {
        Generics asd = ( new generics<>(12,34,23,234,"lasha"));
        System.out.println(((generics)asd).getA());
    }

    public T getT() {
        return T;
    }

    public S getS() {
        return S;
    }

    public D getD() {
        return d;
    }
}
