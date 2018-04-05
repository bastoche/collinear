import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    @Test
    void compareTo_largerY() {
        Point point = new Point(0, 0);
        assertEquals(-1, point.compareTo(new Point(0, 1)));
    }

    @Test
    void compareTo_smallerY() {
        Point point = new Point(0, 0);
        assertEquals(1, point.compareTo(new Point(0, -1)));
    }


    @Test
    void compareTo_sameYLargerX() {
        Point point = new Point(0, 0);
        assertEquals(-1, point.compareTo(new Point(1, 0)));
    }

    @Test
    void compareTo_sameYSmallerX() {
        Point point = new Point(0, 0);
        assertEquals(1, point.compareTo(new Point(-1, 0)));
    }

    @Test
    void compareTo_equal() {
        Point point = new Point(0, 0);
        assertEquals(0, point.compareTo(new Point(0, 0)));
    }

    @Test
    void slopeTo() {
        Point point = new Point(0, 0);
        assertEquals(3.0 / 2, point.slopeTo(new Point(2, 3)));
    }

    @Test
    void slopeTo_horizontal() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        assertEquals(0.0,  p1.slopeTo(p2));
        assertEquals(0.0,  p2.slopeTo(p1));
    }

    @Test
    void slopeTo_verticalUpwards() {
        Point point = new Point(0, 0);
        assertEquals(Double.POSITIVE_INFINITY, point.slopeTo(new Point(0, 1)));
    }

    @Test
    void slopeTo_degenerateSegment() {
        Point point = new Point(0, 0);
        assertEquals(Double.NEGATIVE_INFINITY, point.slopeTo(new Point(0, 0)));
    }

    @Test
    void slopeOrder() {
        Point point = new Point(0, 0);
        Comparator<Point> comparator = point.slopeOrder();
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(1, 1);
        Point p4 = new Point(1, 2);
        Point p5 = new Point(0, 1);
        Point[] sortedPoints = {p1, p2, p3, p4, p5};
        Point[] points = {p5, p4, p3, p2, p1};
        Arrays.sort(points, comparator);
        assertArrayEquals(sortedPoints, points);
    }
}
