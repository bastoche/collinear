import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {

    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
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

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
//            StdOut.println("i " + i);
            StdOut.println("p " + p);
            Comparator<Point> comparator = p.slopeOrder();
            Arrays.sort(points, comparator);
            for (int j = 0; j < points.length - 2; j++) {
                Point q = points[j];
//                StdOut.println("j " + j);
                StdOut.println("q " + q);
                double slope = p.slopeTo(q);
                StdOut.println("slope " + slope);
                for (int k = j + 1; k < points.length && p.slopeTo(points[k]) == slope; k++) {
                    StdOut.println("same slope for index " + k);
                    if (k - j >= 2) {
                        StdOut.println("found segment between " + p + " and " + points[k]);
                        segmentList.add(new LineSegment(p, points[k]));
                    }
                }
            }
        }

        segments = new LineSegment[segmentList.size()];
        segmentList.toArray(segments);
    }

    private static final <T> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
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
