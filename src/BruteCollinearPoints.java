import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        List<LineSegment> segmentList = new ArrayList<>();

        for (int i = 0; i < points.length - 3; i++) {
            Point pi = points[i];
            for (int j = i + 1; j < points.length - 2; j++) {
                Point pj = points[j];
                double slopeIJ = pi.slopeTo(pj);
                for (int k = j + 1; k < points.length - 1; k++) {
                    Point pk = points[k];
                    double slopeJK = pj.slopeTo(pk);
                    if (slopeIJ != slopeJK) continue;
                    for (int l = k + 1; l < points.length; l++) {
                        Point pl = points[l];
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
        return segments;
    }
}
