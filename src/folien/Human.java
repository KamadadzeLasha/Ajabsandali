package folien;

public class Human {
    private Gender gender;
    private int age;
    private String name;

    public Human(Gender gender, int age, String name) {
        this.gender = gender;
        this.age = age;
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
