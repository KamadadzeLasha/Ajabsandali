package fop.w7geo;

public class RegularPolygon extends BaseArea {
    private int n;
    private double length;

    public double getLength() {
        return length;
    }

    public int getN() {
        return n;
    }

    public RegularPolygon(int n, double length) {
        this.n = n;
        this.length = length;
    }

    @Override
    public double circumference() {
        return n * length;
    }

    @Override
    public double area() {
        return n * Math.pow(length, 2) / (4 * Math.tan(Math.PI / n));
    }

    @Override
    public boolean isSquare() {
        return n == 4;
    }

    @Override
    public Square toSquare() {
        if (isSquare()) {
            return new Square(length);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Ncorner{" + "n = " + n + "; length = " + length + "; " + super.toString() + '}';
    }

}