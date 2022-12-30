package fop.w9store;

import java.util.Objects;

public class Screw {
    final ScrewDrive head;
    private final double diameter;
    private final double length;
    private double price;

    public Screw(ScrewDrive head, double diameter, double length, double price) {
        this.head = head;
        this.diameter = diameter;
        this.length = length;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screw screw = (Screw) o;
        return Double.compare(screw.diameter, diameter) == 0 && Double.compare(screw.length, length) == 0 && Double.compare(screw.price, price) == 0 && head == screw.head;
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, diameter, length, price);
    }

    public ScrewDrive getHead() {
        return null;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getLength() {
        return length;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
