package algorithms;

import model.Point;
import java.util.*;

public class PreparataHongMergeHull {

    // Compute the convex hull of a set of points using Jarvis March (Gift Wrapping).
    public static List<Point> computeHull(List<Point> points) {
        JarvisMarch jarvisMarch = new JarvisMarch();
        return jarvisMarch.computeHull(points);
    }

    // Find the upper tangent between two convex hulls
    private static int upperTangent(List<Point> hull1, List<Point> hull2) {
        int p1 = 0; // Start from the leftmost point of hull1
        int p2 = 0; // Start from the leftmost point of hull2

        // Find the rightmost point of hull1 and the leftmost point of hull2
        for (int i = 1; i < hull1.size(); i++) {
            if (hull1.get(i).x > hull1.get(p1).x) {
                p1 = i;
            }
        }

        for (int i = 1; i < hull2.size(); i++) {
            if (hull2.get(i).x < hull2.get(p2).x) {
                p2 = i;
            }
        }

        // Find the upper tangent by checking the tangency condition
        while (true) {
            boolean foundUpperTangent = false;
            // Move counter-clockwise on hull1
            while (ccw(hull2.get(p2), hull1.get(p1), hull1.get((p1 + 1) % hull1.size()))) {
                p1 = (p1 + 1) % hull1.size();
                foundUpperTangent = true;
            }
            // Move counter-clockwise on hull2
            while (ccw(hull1.get(p1), hull2.get(p2), hull2.get((p2 + 1) % hull2.size()))) {
                p2 = (p2 + 1) % hull2.size();
                foundUpperTangent = true;
            }
            if (!foundUpperTangent) {
                break;
            }
        }
        return p1; // Return the index of the upper tangent
    }

    // Find the lower tangent between two convex hulls
    private static int lowerTangent(List<Point> hull1, List<Point> hull2) {
        int p1 = 0;
        int p2 = 0;

        for (int i = 1; i < hull1.size(); i++) {
            if (hull1.get(i).x > hull1.get(p1).x) {
                p1 = i;
            }
        }

        for (int i = 1; i < hull2.size(); i++) {
            if (hull2.get(i).x < hull2.get(p2).x) {
                p2 = i;
            }
        }

        while (true) {
            boolean foundLowerTangent = false;
            while (ccw(hull1.get(p1), hull2.get(p2), hull2.get((p2 + 1) % hull2.size()))) {
                p2 = (p2 + 1) % hull2.size();
                foundLowerTangent = true;
            }

            while (ccw(hull2.get(p2), hull1.get(p1), hull1.get((p1 + 1) % hull1.size()))) {
                p1 = (p1 + 1) % hull1.size();
                foundLowerTangent = true;
            }

            if (!foundLowerTangent) {
                break;
            }
        }

        return p2;
    }

    // Check if three points are in counter-clockwise order
    private static boolean ccw(Point p1, Point p2, Point p3) {
        return (p3.y - p1.y) * (p2.x - p1.x) > (p2.y - p1.y) * (p3.x - p1.x);
    }

    // Merge the two convex hulls by finding the upper and lower tangents
    private static List<Point> mergeHulls(List<Point> hull1, List<Point> hull2) {
        // Find the upper tangent
        int upper = upperTangent(hull1, hull2);
        int lower = lowerTangent(hull1, hull2);

        List<Point> mergedHull = new ArrayList<>();

        // Add points from the first hull starting from the upper tangent
        for (int i = upper; i < hull1.size(); i++) {
            mergedHull.add(hull1.get(i));
        }

        // Add points from the second hull starting from the lower tangent
        for (int i = lower; i < hull2.size(); i++) {
            mergedHull.add(hull2.get(i));
        }

        return mergedHull;
    }

    // Main method to calculate the convex hull using the merge approach
    public static List<Point> convexHull(List<Point> points) {
        System.out.println("Initial points: " + points);
        Collections.sort(points);

        List<Point> firstHalf = points.subList(0, points.size() / 2);
        List<Point> secondHalf = points.subList(points.size() / 2, points.size());

        List<Point> hull1 = computeHull(firstHalf);
        List<Point> hull2 = computeHull(secondHalf);

        return mergeHulls(hull1, hull2);
    }
}
