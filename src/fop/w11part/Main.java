package fop.w11part;

public class Main {
    public static void main(String[] args) {
        BusinessPenguin peter = new BusinessPenguin("Peter");
        BusinessPenguin paul = new BusinessPenguin("Paul");

        peter.setPartner(paul);
        paul.setPartner(peter);

        Customer petersCustomer = new Customer(peter);
        Customer paulsCustomer = new Customer(paul);

        Thread pauls = new Thread(paulsCustomer);
        Thread peters = new Thread(petersCustomer);
        pauls.start();
        peters.start();

        try {
            pauls.join();
            peters.join();
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(paul.balance);
        System.out.println(peter.balance);
    }
}
