package fop.w10pass;

@SuppressWarnings("serial")
public class NotEnoughLowerCase extends NotEnoughLetter {
    public NotEnoughLowerCase(int should, int is) {
        super(should, is);
    }

    @Override
    public String toString() {
        return "NotEnoughLowerCaseException: not enough lower case letters!\n"
                + super.toString();
    }
}