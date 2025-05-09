package utils;

import java.io.*;
import java.util.*;
import model.Point;

public class FileReaderUtil {
    public static List<Point> readPointsFromFile(String filename) throws IOException {
        List<Point> points = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 2) {
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                points.add(new Point(x, y));
            }
        }
        br.close();
        return points;
    }
}
