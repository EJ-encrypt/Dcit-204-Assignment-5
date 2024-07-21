package algorithms.divideandconquer;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    // Helper function to calculate the distance between two points
    private double dist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    // Function to find the smallest distance between two points in points[] of size n
    private double bruteForce(Point[] points, int n) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (dist(points[i], points[j]) < min) {
                    min = dist(points[i], points[j]);
                }
            }
        }
        return min;
    }

    // Function to find the smallest distance recursively
    private double closestUtil(Point[] px, Point[] py, int n) {
        // If there are only two or three points, then use brute force
        if (n <= 3) {
            return bruteForce(px, n);
        }

        // Find the middle point
        int mid = n / 2;
        Point midPoint = px[mid];

        // Divide points into two halves
        Point[] pyl = new Point[mid];
        Point[] pyr = new Point[n - mid];
        int li = 0, ri = 0;
        for (int i = 0; i < n; i++) {
            if (py[i].x <= midPoint.x && li < mid) {
                pyl[li++] = py[i];
            } else {
                pyr[ri++] = py[i];
            }
        }

        // Recursively find the smallest distances in both arrays
        double dl = closestUtil(px, pyl, mid);
        double dr = closestUtil(Arrays.copyOfRange(px, mid, n), pyr, n - mid);

        // Find the smaller of two distances
        double d = Math.min(dl, dr);

        // Build an array strip[] that contains points close (closer than d) to the line passing through the middle point
        Point[] strip = new Point[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(py[i].x - midPoint.x) < d) {
                strip[j++] = py[i];
            }
        }

        // Find the closest points in strip[] and return the minimum of d and closest distance in strip[]
        return Math.min(d, stripClosest(strip, j, d));
    }

    // Function to find the smallest distance in the strip of size j
    private double stripClosest(Point[] strip, int j, double d) {
        double min = d;
        Arrays.sort(strip, 0, j, Comparator.comparingDouble(p -> p.y));

        // Compare each point with its neighbors in the strip sorted by y coordinate
        for (int i = 0; i < j; ++i) {
            for (int k = i + 1; k < j && (strip[k].y - strip[i].y) < min; ++k) {
                if (dist(strip[i], strip[k]) < min) {
                    min = dist(strip[i], strip[k]);
                }
            }
        }
        return min;
    }

    // Function to find the smallest distance
    public double closest(Point[] points, int n) {
        // Sort array of points according to x coordinates
        Point[] px = Arrays.copyOf(points, n);
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));

        // Sort array of points according to y coordinates
        Point[] py = Arrays.copyOf(points, n);
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));

        // Call closestUtil() recursively
        return closestUtil(px, py, n);
    }

    // Example usage
    public static void main(String[] args) {
        Point[] points = { new Point(2, 3), new Point(12, 30), new Point(40, 50), new Point(5, 1), new Point(12, 10),
                new Point(3, 4) };
        int n = points.length;

        ClosestPair cp = new ClosestPair();
        System.out.println("The smallest distance is " + cp.closest(points, n));
    }
}
