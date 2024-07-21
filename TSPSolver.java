package algorithms.greedy;

import java.util.*;

public class TSPSolver {
    public List<Point> findApproximateSolution(Point[] points) {
        List<Point> tour = new ArrayList<>();
        Set<Point> visited = new HashSet<>();
        Point start = points[0];
        Point current = start;
        tour.add(start);
        visited.add(start);

        while (visited.size() < points.length) {
            Point next = findClosestPoint(current, points, visited);
            tour.add(next);
            visited.add(next);
            current = next;
        }

        tour.add(start); // Return to start point
        return tour;
    }

    private Point findClosestPoint(Point current, Point[] points, Set<Point> visited) {
        Point closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Point point : points) {
            if (!visited.contains(point)) {
                double distance = distance(current, point);
                if (distance < minDistance) {
                    minDistance = distance;
                    closest = point;
                }
            }
        }
        return closest;
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(0, 0),
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(4, 4)
        };

        TSPSolver solver = new TSPSolver();
        List<Point> tour = solver.findApproximateSolution(points);

        System.out.println("Tour:");
        for (Point p : tour) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }
    }
}

class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
