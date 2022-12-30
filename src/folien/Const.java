package folien;

public final class Const extends Expression {
    private int n;
    
    public Const(int x) {
        n = x;
    }
    
    protected int evaluate() {
        return n;
    } // end of evaluate()
}
