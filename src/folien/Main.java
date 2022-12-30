package folien;

public class Main {
    public static void main(String[] args) {
        Planet.MA.getRadius();
        Human lasha = new Human(Gender.Male, 18, "lasha");

        String alloc = "alloc";
        String all = "alloc";
        Human human = new Human(Gender.Male, 12, "lasha");
        Human human1 = new Human(Gender.Male, 12, "lasha");
        System.out.println(human1.hashCode() + "\n" + human.hashCode());
        System.out.println(human1.equals(human));
    }
}