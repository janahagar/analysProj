import java.util.*;
import java.io.*;

import model.Point;
import utils.FileReaderUtil;
import algorithms.GrahamScan;
import algorithms.ChanAlgorithm;
import algorithms.PreparataHongMergeHull;  // Import PreparataHongMergeHull

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("---- Convex Hull Algorithms ----");
            System.out.print("Enter input file path (e.g., input.txt): ");
            String path = scanner.nextLine();

            // Reading the points from the input file
            List<Point> points = FileReaderUtil.readPointsFromFile(path);

            System.out.println("\nChoose an algorithm:");
            System.out.println("1) Graham’s Scan");
            System.out.println("2) Chan’s Algorithm");
            System.out.println("3) Preparata and Hong’s Merge Hull");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            List<Point> hull = new ArrayList<>();

            // Switch case to choose the algorithm
            switch (choice) {
                case 1:
                    GrahamScan graham = new GrahamScan();
                    hull = graham.computeHull(points);  // Call to computeHull() for GrahamScan
                    break;
                case 2:
                    ChanAlgorithm chan = new ChanAlgorithm();
                    hull = chan.computeHull(points);  // Call to computeHull() for Chan's algorithm
                    break;
                case 3:
                   hull = PreparataHongMergeHull.computeHull(points);  // Merging the hulls
               
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
                    System.exit(0);
            }

            // Output the convex hull points
            System.out.println("\nConvex Hull Points:");
            for (Point p : hull) {
                System.out.println(p);  // Print each point in the hull
            }

        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
