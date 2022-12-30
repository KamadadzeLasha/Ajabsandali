package fop.w10pass;

@SuppressWarnings("serial")
public class NotEnoughSpecial extends NotEnoughException {
    public NotEnoughSpecial(int should, int is) {
        super(should, is);
    }

    @Override
    public String toString() {
        return "NotEnoughSpecialCharException: not enough special characters!\n"
                + super.toString();
    }
}