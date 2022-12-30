package folien;
public class PolyTest {
    public static void main(String[] args) {
        Food x = new Pizza(201,12,"lamazi");
        Pizza y = (Pizza) new Food(12,1);
        System.out.println(x.calories_per_serving());
        System.out.println(((Pizza)x).getNum());
    }
}