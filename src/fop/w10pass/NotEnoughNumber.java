package fop.w10pass;

@SuppressWarnings("serial")
public class NotEnoughNumber extends NotEnoughException {
    public NotEnoughNumber(int should, int is) {
        super(should, is);
    }

    @Override
    public String toString() {
        return "NotEnoughNumberException: not enough numbers!\n"
                + super.toString();
    }
}