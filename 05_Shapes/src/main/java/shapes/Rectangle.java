package shapes;

/**
 * immutable
 *
 * @author Jiri.Turyna
 */
public class Rectangle extends Shape {

    private double a;
    private double b;
    private double area; // vypocitana

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
        this.area = area();
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    private double area() {
        return a * b;
    }

    public double getArea() {
        return area;
    }

    @Override
    public double computeArea() {
        return area;
    }

    @Override
    public String toString() {
        return "Rectangle{" + "a=" + a + ", b=" + b + '}';
    }

}
