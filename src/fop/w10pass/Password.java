package fop.w10pass;

public class Password {
    private final int nrUpperShould, nrLowerShould, nrSpecialShould, nrNumbersShould, lengthShould;
    private final char[] illegalChars;

    public Password(int nrUpperShould, int nrLowerShould, int nrSpecialShould, int nrNumbersShould, int lengthShould, char[] illegalChars) {
        this.nrUpperShould = nrUpperShould;
        this.nrLowerShould = nrLowerShould;
        this.nrSpecialShould = nrSpecialShould;
        this.nrNumbersShould = nrNumbersShould;
        this.lengthShould = lengthShould;
        this.illegalChars = illegalChars;
    }

    private static boolean matchesIllegalCharacter(char[] illegalChars, char c) {
        for (char illegalChar : illegalChars) if (c == illegalChar) return true;
        return false;
    }

    public void checkFormat(String pwd) throws IllegalCharException, NotEnoughException {
        int nrUpperIs = 0;
        int nrLowerIs = 0;
        int nrSpecialIs = 0;
        int nrNumbersIs = 0;
        int lengthIs = pwd.length();

        if (pwd.length() < lengthShould) throw new NotLongEnough(lengthShould, lengthIs);


        for (int i = 0; i < lengthIs; i++) {
            // illegal character
            if (matchesIllegalCharacter(illegalChars, pwd.charAt(i))) throw new IllegalCharException(pwd.charAt(i));
                // lower case
            else if (pwd.charAt(i) >= 'a' && pwd.charAt(i) <= 'z') nrLowerIs++;
                // upper case
            else if (pwd.charAt(i) >= 'A' && pwd.charAt(i) <= 'Z') nrUpperIs++;
                // numbers
            else if (pwd.charAt(i) >= '0' && pwd.charAt(i) <= '9') nrNumbersIs++;
                // special character
            else nrSpecialIs++;
        }


        if (nrUpperIs < nrUpperShould) throw new NotEnoughUpperCase(nrUpperShould, nrUpperIs);
        if (nrLowerIs < nrLowerShould) throw new NotEnoughLowerCase(nrLowerShould, nrLowerIs);
        if (nrSpecialIs < nrSpecialShould) throw new NotEnoughSpecial(nrSpecialShould, nrSpecialIs);
        if (nrNumbersIs < nrNumbersShould) throw new NotEnoughNumber(nrNumbersShould, nrNumbersIs);
    }

}