import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BruteCollinearPointsTest {

    @Test
    void constructor_null() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BruteCollinearPoints(null);
        });
    }

    @Test
    void constructor_nullPoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            Point[] points = {null};
            new BruteCollinearPoints(points);
        });
    }

    @Test
    void constructor_repeatedPoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            Point repeatedPoint = new Point(0,0);
            Point[] points = {repeatedPoint, new Point(1, 1), repeatedPoint};
            new BruteCollinearPoints(points);
        });
    }

    @Test
    void oneSegment() {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(3, 3);
        Point[] points = {p0, p1, p2, p3};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        assertEquals(1, bruteCollinearPoints.numberOfSegments());
    }

    @Test
    void twoSegments() {
        Point p00 = new Point(0, 0);
        Point p01 = new Point(0, 1);
        Point p02 = new Point(0, 2);
        Point p03 = new Point(0, 3);
        Point p10 = new Point(1, 0);
        Point p20 = new Point(2, 0);
        Point p30 = new Point(3, 0);
        Point[] points = {p00, p01, p02, p03, p10, p20, p30};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        assertEquals(2, bruteCollinearPoints.numberOfSegments());
    }

}