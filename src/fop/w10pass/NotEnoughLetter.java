package fop.w10pass;

public class NotEnoughLetter extends NotEnoughException{
    public NotEnoughLetter(int should, int is) {
        super(should, is);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
