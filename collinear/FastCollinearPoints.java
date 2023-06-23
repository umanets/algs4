/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;

public class FastCollinearPoints {
    private int count = 0;
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
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

        Point[] pointsAux = new Point[pivots.length];
        for (int i = 0; i < pivots.length; i++) pointsAux[i] = pivots[i];

        for (int i = 0; i < n; i++) {
            Arrays.sort(pointsAux, pivots[i].slopeOrder());

            int slopeStart = 1;
            int slopeEnd = 3;

            while (slopeEnd < n) {
                if (pivots[i].slopeTo(pointsAux[slopeStart]) == pivots[i].slopeTo(
                        pointsAux[slopeEnd])) {
                    slopeEnd++;
                }
                else {
                    if (slopeEnd - slopeStart >= 3) {
                        addSegment(slopeStart, slopeEnd, pointsAux, pivots[i]);
                    }
                    slopeStart = slopeEnd - 1;
                    slopeEnd = slopeStart + 2;
                }
            }
            if (slopeEnd - slopeStart >= 3) {
                addSegment(slopeStart, slopeEnd, pointsAux, pivots[i]);
            }
        }

        resize(count);
    }

    private void addSegment(int slopeStart, int slopeEnd, Point[] pointsAux, Point pivot) {
        Arrays.sort(pointsAux, slopeStart, slopeEnd);

        if (count == lineSegments.length) resize((count + 1) * 2);

        if (pivot.compareTo(pointsAux[slopeStart]) < 0)
            lineSegments[count++] = new LineSegment(pivot,
                                                    pointsAux[slopeEnd - 1]);
    }

    public int numberOfSegments() {
        return count;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(lineSegments, lineSegments.length);
    }

    private void resize(int capacity) {
        lineSegments = java.util.Arrays.copyOf(lineSegments, capacity);
    }

    public static void main(String[] args) {
    }
}
