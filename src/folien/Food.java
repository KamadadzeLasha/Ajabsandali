package folien;

public class Food {
    private int CALORIES_PER_GRAM, fat, servings;
    
    public Food(int fat, int servings) {
        this.fat = fat;
        this.CALORIES_PER_GRAM = 9;
        this.servings = servings;
    }
    public int calories(){
        return fat*CALORIES_PER_GRAM;
    }
    public int calories_per_serving() {
        return (calories() / servings);
    }
}
