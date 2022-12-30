package fop.w9store;

import java.util.HashMap;
import java.util.LinkedList;

public class ScrewStore {
    private HashMap<Screw, Integer> stock;
    private LinkedList<Order> orders;

    public ScrewStore(HashMap stock, LinkedList orders) {
        this.stock = stock;
        this.orders = orders;
    }

    public void addItem(Screw type, int amount) {
        stock.merge(type, amount, Integer::sum);
    }

    public void takeOrder(Screw type, int amount) {
        if (amount > 0){
            orders.add(new Order(type,amount));
        }
    }

    public boolean executeOrder() {
        if (orders.isEmpty()) {
            return false;
        }
        if (stock.get(orders.getFirst().getScrew()) != null) {
            if (stock.get(orders.getFirst().getScrew()) == orders.getFirst().getAmount()) {
                stock.remove(orders.getFirst().getScrew());
                return true;
            }
            if (stock.get(orders.getFirst().getScrew()) > orders.getFirst().getAmount()) {
                stock.put(orders.getFirst().getScrew(), stock.get(orders.getFirst().getScrew()) - orders.getFirst().getAmount());
                return true;
            }
        }
        return false;
    }

    public void inflation(double percent) {
    }

    public int count() {
        int count = 0;
        return count;
    }

    public double value() {
        int value = 0;
        return value;
    }

    public String stockToString() {
        StringBuilder sb = new StringBuilder();
        return sb.toString();
    }

}
