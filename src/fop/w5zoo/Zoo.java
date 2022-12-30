package fop.w5zoo;

public class Zoo {
    private Vivarium[] vivaria;
    
    public Vivarium[] getVivaria() {
        return vivaria;
    }
    
    public Zoo(Vivarium[] vivaria) {
        this.vivaria = vivaria;
    }
    
    public int  getCosts(){
    int cost = 0;
        for (Vivarium vivarium : vivaria) {
            cost += vivarium.getCosts();
        }
        return cost;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (int i = 0; i < vivaria.length; i++) {
            sb.append(vivaria[i]);
            if (i != vivaria.length-1){
                sb.append(", ");
            }
            else {
                sb.append('}');
            }
        }
        return String.valueOf(sb);
    }
}

