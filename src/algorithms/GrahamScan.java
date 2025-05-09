package algorithms;

import model.Point;
import java.util.*;

// checked and tested
public class GrahamScan {

    public List<Point> computeHull(List<Point> points) {
        if (points.size() < 3) return new ArrayList<>(points);

        // Step 1: Find the lowest point (smallest y, then smallest x) o(n) --> bnscan all points
        Point p0 = Collections.min(points, Comparator.comparing((Point p) -> p.y).thenComparing(p -> p.x));

        // Step 2: Sort points by polar angle with respect to p0 O(n log n)--> bnsort kol el pointss
        points.sort((a, b) -> {
            double angleA = polarAngle(p0, a);
            double angleB = polarAngle(p0, b);
            if (angleA == angleB) {
                return Double.compare(distance(p0, a), distance(p0, b));
            } else {
                return Double.compare(angleA, angleB);
            }
        });

        // Step 3: Create hull stack o(n) --> el points is only pushed mra whda w max poped mra whda
        Stack<Point> hull = new Stack<>();
        for (Point pt : points) {
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), pt) != 1) {
                hull.pop(); // remove non-left turn
            }
            hull.push(pt);
        }

        return new ArrayList<>(hull);
    }

    // Returns orientation: 0 = colinear, 1 = counterclockwise (left), 2 = clockwise (right)
    private int orientation(Point a, Point b, Point c) {
        double val = (b.y - a.y) * (c.x - b.x) - 
                     (b.x - a.x) * (c.y - b.y);
        if (val == 0) return 0;
        return (val < 0) ? 1 : 2;
    }

    // Calculate polar angle between p0 and p1
    private double polarAngle(Point p0, Point p1) {
        return Math.atan2(p1.y - p0.y, p1.x - p0.x);
    }

    // Euclidean distance squared (no sqrt needed for comparison)
    private double distance(Point a, Point b) {
        return Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2);
    }
}
//  soo time complexity= O(n log n) + O(n) + O(n) =  O(n log n) 
//  space complexity= O(n)  as worst case all ponts lie on hull stack also 
// it is in place sorting algo as we do not need any extra stack 
// corrctness
// . Does the sorted order matter if two or more points have the same x-coordinate?
// · What happens if there are three or more collinear points
// , in particular on the convex hull?
