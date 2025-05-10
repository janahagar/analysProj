package algorithms;

import model.Point;
import java.util.*;

public class GrahamScan {

    public List<Point> computeHull(List<Point> inputPoints) {
        if (inputPoints.size() < 3) return new ArrayList<>(inputPoints);

        Point p0 = Collections.min(inputPoints, Comparator.comparing((Point p) -> p.y).thenComparing(p -> p.x));

        List<Point> sorted = new ArrayList<>(inputPoints);
        sorted.sort((a, b) -> {
            if (a == p0) return -1;
            if (b == p0) return 1;
            double angleA = polarAngle(p0, a);
            double angleB = polarAngle(p0, b);
            if (angleA == angleB) {
                return Double.compare(distance(p0, a), distance(p0, b));
            }
            return Double.compare(angleA, angleB);
        });

        // ✅ Keep all points, let stack do the work
        List<Point> filtered = new ArrayList<>(sorted);

        Stack<Point> hull = new Stack<>();
        for (Point pt : filtered) {
            while (hull.size() >= 2 &&
                   orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), pt) != 1) {
                hull.pop();
            }
            hull.push(pt);
        }

        return new ArrayList<>(hull);
    }

    private int orientation(Point a, Point b, Point c) {
        double val = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y);
        if (Math.abs(val) < 1e-10) return 0;
        return (val < 0) ? 1 : 2;
    }

    private double polarAngle(Point p0, Point p1) {
        return Math.atan2(p1.y - p0.y, p1.x - p0.x);
    }

    private double distance(Point a, Point b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
}
