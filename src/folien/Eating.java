package folien;

public class Eating {
    public static void main (String[] args) {
        Pizza special = new Pizza(275,12,"lamazi");
        System.out.print("Calories per serving: " +
                special.calories_per_serving());
    }
}
