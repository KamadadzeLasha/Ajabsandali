package fop.w10pass;

public class NotEnoughException extends Exception {
    private final int is;
    private final int should;

    public NotEnoughException(int should, int is){
        this.should = should;
        this.is = is;
    }

    @Override
    public String toString() {
        return "There Should be " + should + ", but there is " + is;
    }
}
