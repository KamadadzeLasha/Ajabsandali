package fop.w10pass;

@SuppressWarnings("serial")
public class NotEnoughUpperCase extends NotEnoughLetter {
    public NotEnoughUpperCase(int should, int is) {
        super(should, is);
    }

    @Override
    public String toString() {
        return "NotEnoughUpperCaseException: not enough upper case letters!\n"
                + super.toString();
    }
}