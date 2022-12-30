package fop.w5phone;

public class Entry {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    public Entry(String firstName, String lastName, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
}
