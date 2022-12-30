package fop.w7geo;

public class Rectangle extends BaseArea {
    private double width;
    private double height;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double circumference() {
        return 2 * width + 2 * height;
    }

    @Override
    public double area() {
        return width * height;
    }

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isSquare() {
        return height == width;
    }

    @Override
    public Square toSquare() {
        if (isSquare()) {
            return new Square(width);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "rectangle{" + "width=" + width + ", height=" + height + super.toString() + '}';
    }

}