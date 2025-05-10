package algorithms;

import model.Point;
import java.util.*;

public class PreparataHongMergeHull {

    public List<Point> computeHull(List<Point> points) {
        if (points.size() <= 3) {
            return new GrahamScan().computeHull(points);
        }

        List<Point> sorted = new ArrayList<>(points);
        Collections.sort(sorted);

        int mid = sorted.size() / 2;
        List<Point> left = new ArrayList<>(sorted.subList(0, mid));
        List<Point> right = new ArrayList<>(sorted.subList(mid, sorted.size()));

        List<Point> leftHull = computeHull(left);
        List<Point> rightHull = computeHull(right);

        return mergeHulls(leftHull, rightHull);
    }

    // Merge by recomputing convex hull of both hulls combined
    private List<Point> mergeHulls(List<Point> left, List<Point> right) {
    List<Point> all = new ArrayList<>();
    all.addAll(left);
    all.addAll(right);
    return new GrahamScan().computeHull(all); // ✅ This guarantees correctness
}



}
