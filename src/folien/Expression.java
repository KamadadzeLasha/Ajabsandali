package folien;

public abstract class Expression {
    private int value;
    private boolean evaluated = false;
    
    public int getValue() {
        if (!evaluated){
            value = evaluate();
            evaluated = true;
        }
        return value;
    }
    
    abstract protected int evaluate();
}
