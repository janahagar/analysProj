package algorithms;

import model.Point;
import java.util.*;

public class ChanAlgorithm {

    // Call this method to compute the convex hull using GrahamScan for subsets, and JarvisMarch for merging
    public List<Point> computeHull(List<Point> points) {
        int n = points.size();
        int m = (int) Math.ceil(Math.sqrt(n)); // Size of each subset

        // Step 1: Divide the points into subsets of size m
        List<List<Point>> subsets = new ArrayList<>();
        for (int i = 0; i < n; i += m) {
            subsets.add(points.subList(i, Math.min(i + m, n)));
        }

        // Step 2: Compute the convex hull for each subset using GrahamScan
        List<List<Point>> hulls = new ArrayList<>();
        for (List<Point> subset : subsets) {
            // Using GrahamScan for each subset
            GrahamScan grahamScan = new GrahamScan();
            List<Point> hull = grahamScan.computeHull(subset);
            hulls.add(hull);
        }

        // Step 3: Merge the hulls from each subset using JarvisMarch
        List<Point> finalHull = mergeHullsUsingJarvisMarch(hulls);

        return finalHull;
    }

    // Method to merge all individual hulls from each subset using JarvisMarch
    private List<Point> mergeHullsUsingJarvisMarch(List<List<Point>> hulls) {
        // Start by using the first hull
        List<Point> mergedHull = new ArrayList<>(hulls.get(0));

        // Iterate through all other hulls and merge them into the first hull
        for (int i = 1; i < hulls.size(); i++) {
            List<Point> hull = hulls.get(i);

            // Combine the current mergedHull and the new hull using JarvisMarch
            JarvisMarch jarvisMarch = new JarvisMarch();
            mergedHull = jarvisMarch.computeHull(mergeTwoHulls(mergedHull, hull));
        }

        return mergedHull;
    }

    // Helper method to merge two hulls
    private List<Point> mergeTwoHulls(List<Point> hull1, List<Point> hull2) {
        List<Point> merged = new ArrayList<>(hull1);
        merged.addAll(hull2);
        return merged;
    }
}

// time complexity= O(n log n) + O(n) + O(n) =  O(n log n)