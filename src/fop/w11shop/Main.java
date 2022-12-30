package fop.w11shop;

public class Main {
    public static void main(String[] args) {

        BookShop shop = new BookShop();
        shop.addBooksInStore(7500);

        Customer peter = new Customer("Peter", shop);
        Customer paul = new Customer("Pauls", shop);

        Thread petersCustomerThread = new Thread(peter);
        Thread paulsCustomerThread = new Thread(paul);

        petersCustomerThread.start();
        paulsCustomerThread.start();

        try {
            petersCustomerThread.join();
            paulsCustomerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Something went wrong. Interrupted!");
            return;
        }


        shop.printSummary();
        peter.printSummary();
        paul.printSummary();
    }
}