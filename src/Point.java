import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (that.x == x) {
            if (that.y == y) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        if (that.y == y) {
            return 0.0;
        }

        return ((double) that.y - y)/(that.x - x);
    }

    public int compareTo(Point that) {
        if (y < that.y) return -1;
        if (y > that.y) return 1;
        if (x < that.x) return -1;
        if (x > that.x) return 1;
        return 0;
    }

    public Comparator<Point> slopeOrder() {
        return Comparator.comparingDouble(Point.this::slopeTo);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}