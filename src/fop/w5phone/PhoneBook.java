package fop.w5phone;

public class PhoneBook {
    private Entry[] entries;

    public PhoneBook(Entry[] entries) {
        this.entries = entries;
    }

    public String find(String firstName, String lastName) {
        int lowerBound = 0;
        int upperBound = entries.length - 1;
        while(lowerBound <= upperBound){
            int searchIndex = (lowerBound + upperBound) / 2;
            int comp = lastName.compareTo(entries[searchIndex].getLastName());
            if(comp == 0) {
                comp = firstName.compareTo(entries[searchIndex].getFirstName());
            }
            if(comp < 0){
                upperBound = searchIndex - 1;
                continue;
            }
            if(comp > 0){
                lowerBound = searchIndex + 1;
                continue;
            }
            return entries[searchIndex].getPhoneNumber();
        }
        return null;
    }
}