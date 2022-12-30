package fop.w7geo;

public class Square extends BaseArea {
    private final double length;

    public Square(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    @Override
    public double circumference() {
        return 4 * length;
    }

    @Override
    public double area() {
        return length * length;
    }

    @Override
    public boolean isSquare() {
        return true;
    }

    @Override
    public Square toSquare() {
        return this;
    }

    @Override
    public String toString() {
        return "Square{" + "length=" + length + "; " + super.toString() + '}';
    }
}

