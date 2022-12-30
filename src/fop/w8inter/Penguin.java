package fop.w8inter;

public class Penguin extends Animal implements Comparable, Copyable {
    private String name;
    private double height;

    public Penguin(int age, String name, double height) {
        this.age = age;
        this.name = name;
        this.height = height;
    }

    private int compareStrings(String first, String second) {
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) {
                return Character.compare(first.charAt(i), second.charAt(i));
            }
        }
        return 0;
    }

    @Override
    public int compareTo(Penguin other) {
        return Double.compare(this.height, other.height) != 0 ? Double.compare(this.height, other.height) : this.age != other.age ? Integer.compare(this.age, other.age) : compareStrings(this.name, other.name);
    }

    @Override
    public Penguin copy() {
        return new Penguin(this.age,this.name,this.height);
    }
}
