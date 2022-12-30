package fop.w7geo;

public abstract class BaseArea {
    public abstract double circumference();

    public abstract double area();

    public boolean isSquare() {
        return false;
    }

    public Square toSquare() {
        return null;
    }

    @Override
    public String toString() {
        return "circumference: " + circumference() + "; area: " + area() + "; square?: " + isSquare();
    }
}