import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(sortedPoints);
        for (int i = 0; i < sortedPoints.length - 1; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        List<LineSegment> segmentList = new ArrayList<>();

        for (int index0 = 0; index0 < sortedPoints.length - 3; index0++) {
            Point p0 = sortedPoints[index0];
            for (int index1 = index0 + 1; index1 < sortedPoints.length - 2; index1++) {
                Point p1 = sortedPoints[index1];
                double slope01 = p0.slopeTo(p1);
                for (int index2 = index1 + 1; index2 < sortedPoints.length - 1; index2++) {
                    Point p2 = sortedPoints[index2];
                    double slope12 = p1.slopeTo(p2);
                    if (slope01 != slope12) continue;
                    for (int index3 = index2 + 1; index3 < sortedPoints.length; index3++) {
                        Point p3 = sortedPoints[index3];
                        double slope23 = p2.slopeTo(p3);
                        if (slope01 == slope12 && slope12 == slope23) {
                            segmentList.add(new LineSegment(p0, p3));
                        }
                    }
                }
            }
        }

        segments = new LineSegment[segmentList.size()];
        segmentList.toArray(segments);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }
}
