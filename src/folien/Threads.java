package folien;

public class Threads {
    public static void main(String[] args) {
        Thread thread = new Thread( () -> System.out.println("hello"));
        thread.start();
        Thread thread1 = new Thread( () -> {

        });
    }

}
