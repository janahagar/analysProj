package utils;

import model.Point;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ConvexHullVisualizerSwing extends JPanel {

    private static List<Point> inputPoints;
    private static List<Point> convexHull;

    public static void showVisualizer(List<Point> points, List<Point> hull) {
        inputPoints = points;
        convexHull = hull;

        JFrame frame = new JFrame("Convex Hull Visualizer (Swing)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.add(new ConvexHullVisualizerSwing());
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int offsetX = 50;
        int offsetY = 600;
        int scale = 80;

        // Draw points
        g2.setColor(Color.BLACK);
        for (Point p : inputPoints) {
            int x = (int) (p.x * scale + offsetX);
            int y = (int) (offsetY - p.y * scale);
            g2.fillOval(x - 4, y - 4, 8, 8);
        }

        // Draw convex hull lines
        g2.setColor(Color.RED);
        for (int i = 0; i < convexHull.size(); i++) {
            Point a = convexHull.get(i);
            Point b = convexHull.get((i + 1) % convexHull.size());

            int x1 = (int) (a.x * scale + offsetX);
            int y1 = (int) (offsetY - a.y * scale);
            int x2 = (int) (b.x * scale + offsetX);
            int y2 = (int) (offsetY - b.y * scale);

            g2.drawLine(x1, y1, x2, y2);
        }
    }
}
