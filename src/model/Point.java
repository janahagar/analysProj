package model;

public class Point implements Comparable<Point> {
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;  // Returns an int
    }

    public double getY() {
        return y;  // Returns an int
    }

    // If you need double values, use the following methods
    public double getXAsDouble() {
        return (double) x;
    }

    public double getYAsDouble() {
        return (double) y;
    }

    @Override
    public int compareTo(Point other) {
        return this.x != other.x ?
            Double.compare(this.x, other.x) :
            Double.compare(this.y, other.y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
