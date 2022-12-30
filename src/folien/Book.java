package folien;

public class Book {
    protected int pages;
    protected String name;
    public Book(int x){
        this.pages = x;
        this.name = "unnamed";
    }
    public Book(String name, int pages){
        this.name = name;
        this.pages = pages;
    }
    public void massage() {
        System.out.print("Number of pages:\t"+pages+"\n");
    }
}
