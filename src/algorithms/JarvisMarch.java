package algorithms;

import model.Point;
import java.util.*;

public class JarvisMarch {

    public List<Point> computeHull(List<Point> points) {
        int n = points.size();
        if (n < 3) return points;

        List<Point> hull = new ArrayList<>();

        // Find the leftmost point O(n) --> bnsearch through all points fbn3dy 3lehom kolhom
        Point start = points.get(0);
        for (Point p : points) {
            if (p.x < start.x || (p.x == start.x && p.y < start.y)) {
                start = p;
            }
        }

        // do-while loop O(h) --> ws case all points lie on hull so hn run them n times h = n
        Point onHull = start;
        do {
            hull.add(onHull);
            Point next = points.get(0);
            
            // for loop O(n) --> bnsearch through all points fbn3dy 3lehom kolhom
            for (Point candidate : points) {
                if (candidate == onHull) continue;
                int o = orientation(onHull, next, candidate);
                if (next == onHull || o == 1 || (o == 0 && distance(onHull, candidate) > distance(onHull, next))) {
                    next = candidate;
                }
            }

            onHull = next;
        } while (!onHull.equals(start));

        return hull;
    }

    private int orientation(Point p1, Point p2, Point p3) {
        double val = (p2.y - p1.y) * (p3.x - p2.x) - 
                     (p2.x - p1.x) * (p3.y - p2.y);
        if (val == 0) return 0;
        return (val < 0) ? 1 : 2;
    }

    private double distance(Point p1, Point p2) {
        double dx = p2.x - p1.x;
        double dy = p2.y - p1.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

// time complexity= O(n^2) as we have to check all points for each point on hull --> 

// Space Complexity: O(n)
