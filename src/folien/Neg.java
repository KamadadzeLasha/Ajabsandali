package folien;

public final class Neg extends Expression {
    private Expression arg;
    
    public Neg(Expression a) {
        arg = a;
    }
    
    protected int evaluate() {
        return -arg.getValue();
    }
}
