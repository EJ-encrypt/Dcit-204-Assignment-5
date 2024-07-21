package algorithms.divideandconquer;

import java.util.ArrayList;
import java.util.List;

public class Quickhull {

    public List<Point> quickHull(Point[] points) {
        List<Point> convexHull = new ArrayList<>();

        // Find the points with minimum and maximum x-coordinate
        int minIndex = 0, maxIndex = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].x < points[minIndex].x) {
                minIndex = i;
            }
            if (points[i].x > points[maxIndex].x) {
                maxIndex = i;
            }
        }

        // Add the points with min and max x-coordinate to the convex hull
        convexHull.add(points[minIndex]);
        convexHull.add(points[maxIndex]);

        // Recursively find the convex hull on the left and right side of the line formed by min and max points
        findHull(points, points[minIndex], points[maxIndex], 1, convexHull);
        findHull(points, points[minIndex], points[maxIndex], -1, convexHull);

        return convexHull;
    }

    private void findHull(Point[] points, Point p1, Point p2, int side, List<Point> hull) {
        int index = -1;
        double maxDist = 0;

        // Find the point with maximum distance from the line formed by p1 and p2
        for (int i = 0; i < points.length; i++) {
            double dist = findDistance(p1, p2, points[i]);
            if (pointLocation(p1, p2, points[i]) == side && dist > maxDist) {
                index = i;
                maxDist = dist;
            }
        }

        // If no point is found, add p2 to the convex hull
        if (index == -1) {
            if (!hull.contains(p2)) {
                hull.add(p2);
            }
            return;
        }

        // Recursively find the convex hull on both sides of the line formed by p1 and points[index]
        findHull(points, points[index], p1, -pointLocation(points[index], p1, p2), hull);
        findHull(points, points[index], p2, -pointLocation(points[index], p2, p1), hull);

        // Add points[index] to the convex hull
        if (!hull.contains(points[index])) {
            hull.add(points[index]);
        }
    }

    private double findDistance(Point p1, Point p2, Point p) {
        return Math.abs((p.y - p1.y) * (p2.x - p1.x) - (p2.y - p1.y) * (p.x - p1.x));
    }

    private int pointLocation(Point p1, Point p2, Point p) {
        double val = (p.y - p1.y) * (p2.x - p1.x) - (p2.y - p1.y) * (p.x - p1.x);
        return val > 0 ? 1 : (val < 0 ? -1 : 0);
    }

    // Example usage
    public static void main(String[] args) {
        Quickhull qh = new Quickhull();

        Point[] points = { new Point(0, 3), new Point(2, 2), new Point(1, 1), new Point(2, 1), new Point(3, 0),
                new Point(0, 0), new Point(3, 3) };

        List<Point> convexHull = qh.quickHull(points);

        System.out.println("Convex Hull:");
        for (Point point : convexHull) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
    }
}
