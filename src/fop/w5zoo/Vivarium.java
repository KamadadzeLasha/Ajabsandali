package fop.w5zoo;

public class Vivarium {
    private Animal[] inhabitants;
    private int area;
    private int constructionYear;
    
    public Vivarium(Animal[] inhabitants, int area, int constructionYear) {
        this.inhabitants = inhabitants;
        this.area = area;
        this.constructionYear = constructionYear;
    }
    
    public Animal[] getInhabitants() {
        return inhabitants;
    }
    
    public int getArea() {
        return area;
    }
    
    public int getConstructionYear() {
        return constructionYear;
    }
    public int getCosts(){
        int cost = 0;
        for (Animal inhabitant : inhabitants) {
            cost += inhabitant.getFoodCosts();
        }
        return  cost + 900 + area * 100 + area * (2021 - constructionYear) * 5;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inhabitants.length; i++) {
            sb.append(inhabitants[i]);
            if (i != inhabitants.length - 1) {
                sb.append(", ");
            }
        }
        return "[area: " + area + ", constructionYear: " + constructionYear + ", animals: " + sb + ']';
    }
}
