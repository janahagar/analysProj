import java.util.*;
import java.io.*;

import model.Point;
import utils.FileReaderUtil;
import algorithms.GrahamScan;
import algorithms.PreparataHongMergeHull;
import algorithms.ChanAlgorithm;
import utils.ConvexHullVisualizerSwing;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("---- Convex Hull Algorithms ----");
            System.out.print("Enter input file path (e.g., input.txt): ");
            String path = scanner.nextLine();

            // Read input points
            List<Point> points = FileReaderUtil.readPointsFromFile(path);

            System.out.println("\nChoose an algorithm:");
            System.out.println("1) Graham’s Scan");
            System.out.println("2) Chan’s Algorithm");
            System.out.println("3) Preparata and Hong’s Merge Hull");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            List<Point> hull = new ArrayList<>();

            switch (choice) {
                case 1:
                    hull = new GrahamScan().computeHull(points);
                    break;
                case 2:
                    hull = new ChanAlgorithm().computeHull(points);
                    break;
                case 3:
                    hull = new PreparataHongMergeHull().computeHull(points);
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
                    System.exit(0);
            }

            System.out.println("\nConvex Hull Points:");
            for (Point p : hull) {
                System.out.println(p);
            }

            // 🎯 Launch Swing visualization (no JavaFX required)
            ConvexHullVisualizerSwing.showVisualizer(points, hull);

        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
