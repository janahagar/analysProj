import java.util.*;
import java.io.*;

import model.Point;
import utils.FileReaderUtil;
import algorithms.GrahamScan;
import algorithms.ChanAlgorithm;
//import algorithms.PreparataHongMergeHull;  // Import PreparataHongMergeHull

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
                    // Step 1: Sort the points
                    Collections.sort(points);  // Sort the points based on the x and y coordinates
                    
                    // Step 2: Split the points into two halves
                    List<Point> firstHalf = points.subList(0, points.size() / 2);
                    List<Point> secondHalf = points.subList(points.size() / 2, points.size());
                    
                    // Step 3: Apply GrahamScan or Chan's Algorithm for each half
                    GrahamScan graham2 = new GrahamScan();
                    List<Point> hull1 = graham2.computeHull(firstHalf);
                    List<Point> hull2 = graham2.computeHull(secondHalf);
                    
                    System.out.println("Hull 1: " + hull1);
                    System.out.println("Hull 2: " + hull2);

                    // Step 4: Merge the two hulls using Preparata and Hong’s Merge Hull
                   // PreparataHongMergeHull mergeHull = new PreparataHongMergeHull();
                   // hull = mergeHull.mergeHull(hull1, hull2);  // Merging the hulls

                
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
