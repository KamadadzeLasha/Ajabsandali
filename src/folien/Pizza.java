package folien;

public class Pizza extends Food{
    private String type;

    public Pizza(int fat, int servings, String type) {
        super(fat, servings);
        this.type = type;
    }
    public int getNum(){
        return 123;
    }
}
