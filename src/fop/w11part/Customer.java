package fop.w11part;

public class Customer implements Runnable {
    BusinessPenguin salespenguin;

    public Customer(BusinessPenguin salesman) {
        this.salespenguin = salesman;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            System.out.println(salespenguin.name + " buys number" + i);
            salespenguin.sellFish(2);
        }
    }
}
