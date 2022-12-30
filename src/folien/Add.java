package folien;

public final class Add extends Expression {
    private Expression left, right;
    
    public Add(Expression l, Expression r) {
        left = l;
        right = r;
    }
    
    protected int evaluate() {
        return left.getValue() + right.getValue();
    } // end of evaluate()
}
