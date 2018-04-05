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

        for (int i = 0; i < sortedPoints.length - 3; i++) {
            Point pi = sortedPoints[i];
            for (int j = i + 1; j < sortedPoints.length - 2; j++) {
                Point pj = sortedPoints[j];
                double slopeIJ = pi.slopeTo(pj);
                for (int k = j + 1; k < sortedPoints.length - 1; k++) {
                    Point pk = sortedPoints[k];
                    double slopeJK = pj.slopeTo(pk);
                    if (slopeIJ != slopeJK) continue;
                    for (int l = k + 1; l < sortedPoints.length; l++) {
                        Point pl = sortedPoints[l];
                        double slopeKL = pk.slopeTo(pl);
                        if (slopeIJ == slopeJK && slopeJK == slopeKL) {
                            segmentList.add(new LineSegment(pi, pl));
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
