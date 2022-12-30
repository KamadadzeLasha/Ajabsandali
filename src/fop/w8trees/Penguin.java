package fop.w8trees;

public class Penguin {
    public static final Penguin p1 = new Penguin("Paul", 5, 1.0);
    public static final Penguin p2 = new Penguin("Peter", 12, 0.75);
    public static final Penguin p3 = new Penguin("Pia", 3, 0.3);
    public static final Penguin p4 = new Penguin("Pascal", 4, 0.37);
    public static final Penguin p5 = new Penguin("Pauline", 8, 0.6);

    private String name;
    private int age;
    private double height;

    public Penguin(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
