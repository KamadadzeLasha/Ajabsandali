package folien;


public class MyThread extends Thread {
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.setName("-------------------");
        t2.setName("+++++++++++++++++++");
        t1.start();
        t2.start();
        t2.join();
        t1.join();
    }

    public void hello(String s) {
        System.out.println(getName());
        System.out.println(s);
        System.out.println(gamotvla(s.length()));

    }

    public double gamotvla(int la){
        return Math.cos(la);
    }

    public void run() {
    }
}
