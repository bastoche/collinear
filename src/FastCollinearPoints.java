import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {

    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
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

        for (int i = 0; i < sortedPoints.length; i++) {
            Point p = sortedPoints[i];
//            StdOut.println("p " + p);
            Comparator<Point> comparator = p.slopeOrder();
            Point[] pointsSortedUsingSlopeOrder = Arrays.copyOf(sortedPoints, sortedPoints.length);
            Arrays.sort(pointsSortedUsingSlopeOrder, i + 1, pointsSortedUsingSlopeOrder.length, comparator);
            int j = i + 1;
            while (j < pointsSortedUsingSlopeOrder.length) {
                Point q = pointsSortedUsingSlopeOrder[j];
//                StdOut.println("q " + q);
                double slope = p.slopeTo(q);
//                StdOut.println("slope " + slope);
                int sameSlopeCount = 1;
                LineSegment lineSegment = null;
                int k = j + 1;
                while (k < pointsSortedUsingSlopeOrder.length && p.slopeTo(pointsSortedUsingSlopeOrder[k]) == slope) {
//                    StdOut.println("same slope for point " + pointsSortedUsingSlopeOrder[k]);
                    if (++sameSlopeCount >= 3) {
//                        StdOut.println("found segment between " + p + " and " + pointsSortedUsingSlopeOrder[k]);
                        lineSegment = new LineSegment(p, pointsSortedUsingSlopeOrder[k]);
                    }
                    ++k;
                }
                if (lineSegment != null) {
//                    StdOut.println("store segment " + lineSegment);
                    segmentList.add(lineSegment);
                }
                j = k;
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

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
