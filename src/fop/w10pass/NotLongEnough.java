package fop.w10pass;

@SuppressWarnings("serial")
public class NotLongEnough extends NotEnoughException {
    public NotLongEnough(int should, int is) {
        super(should, is);
    }

    @Override
    public String toString() {
        return "NotLongEnoughException: insufficient length!\n"
                + super.toString();
    }

}