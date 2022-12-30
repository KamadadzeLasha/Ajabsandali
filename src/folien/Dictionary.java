package folien;

public class Dictionary extends Book {
    private int defs;
    private int pages;
    
    public Dictionary(int x, int defs) {
        super(x);
        this.pages = 2 * super.pages;
        this.defs = defs;
    }
    public void message(){
        super.massage();
        System.out.print("Number of defs:\t\t" + defs + "\n");
        System.out.print("Defs per page:\t\t" + defs / pages + "\n");
    }
}
