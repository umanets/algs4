import java.util.Arrays;

public class BruteCollinearPoints {
    private int count = 0;
    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        lineSegments = new LineSegment[0];

        if (points == null) throw new IllegalArgumentException();
        int n = points.length;

        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("");
        }

        Point[] pivots = java.util.Arrays.copyOf(points, points.length);
        Arrays.sort(pivots);

        for (int i = 1; i < n; i++) {
            if (pivots[i].compareTo(pivots[i - 1]) == 0)
                throw new IllegalArgumentException("");
        }

        if (n < 4) return;

        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                for (int r = q + 1; r < n; r++) {
                    for (int s = r + 1; s < n; s++) {
                        if (collinear(p, q, r, s, pivots)) {
                            if (count == lineSegments.length) resize((count + 1) * 2);
                            lineSegments[count++] = new LineSegment(pivots[p], pivots[s]);
                        }
                    }
                }
            }
        }

        resize(count);
    }

    private boolean collinear(int p, int q, int r, int s, Point[] points) {
        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) &&
                points[p].slopeTo(points[r]) == points[p].slopeTo(points[s]))
            return true;
        return false;
    }

    private void resize(int capacity) {
        lineSegments = java.util.Arrays.copyOf(lineSegments, capacity);
    }

    public int numberOfSegments() {
        return count;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, lineSegments.length);
    }

    public static void main(String[] args) {

    }
}
