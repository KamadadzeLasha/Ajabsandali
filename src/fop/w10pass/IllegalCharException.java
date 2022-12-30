package fop.w10pass;

@SuppressWarnings("serial")

public class IllegalCharException extends Exception {
    private final char used;


    public IllegalCharException(char used) {
        this.used = used;
    }

    private String escapeSpecial(char used) {
        return switch (used) {
            case '\n' -> "line break (\\n)";
            case '\t' -> "tab (\\t)";
            case '\r' -> "carriage return (\\r)";
            case ' ' -> "whitespace";
            case '\b' -> "backspace (\\b)";
            case '\f' -> "formfeed (\\f)";
            default -> Character.toString(used);
        };
    }

    @Override
    public String toString() {
        return "IllegalCharacterException: the following character is not allowed " + escapeSpecial(used);
    }

}