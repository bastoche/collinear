import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FastCollinearPointsTest {
    @Test
    void constructor_null() {
        assertThrows(IllegalArgumentException.class, () ->
            new FastCollinearPoints(null)
        );
    }

    @Test
    void constructor_nullPoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            Point[] points = {null};
            new FastCollinearPoints(points);
        });
    }

    @Test
    void constructor_repeatedPoint() {
        assertThrows(IllegalArgumentException.class, () -> {
            Point repeatedPoint = new Point(0,0);
            Point[] points = {repeatedPoint, new Point(1, 1), repeatedPoint};
            new FastCollinearPoints(points);
        });
    }

    @Test
    void constructor_noMutation() {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point[] points = {p1, p0};
        new FastCollinearPoints(points);
        assertEquals(p1, points[0]);
    }

    @Test
    void oneSegment() {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(3, 3);
        Point[] points = {p0, p1, p2, p3};
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(1, fastCollinearPoints.numberOfSegments());
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
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(2, fastCollinearPoints.numberOfSegments());
    }

    @Test
    void oneSegmentWithFivePoints() {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(3, 3);
        Point p4 = new Point(4, 4);
        Point[] points = {p0, p1, p2, p3, p4};
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(1, fastCollinearPoints.numberOfSegments());
    }
}