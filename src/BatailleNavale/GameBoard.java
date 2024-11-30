package BatailleNavale;

import BatailleNavale.utils.Graphical;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameBoard extends Canvas {
    private final List<Rectangle> filledRectangles = new ArrayList<>();

    public void addRectangle(int x, int y, int width, int height) {
        filledRectangles.removeAll(filledRectangles);
        filledRectangles.add(new Rectangle(x, y, width, height));
        repaint(); // Trigger a repaint to persist the changes
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Draw the grid (reuse your existing logic if necessary)
        Graphical.paint(g);

        // Draw the filled rectangles
        g.setColor(Color.RED); // Example color for filled cells
        for (Rectangle rect : filledRectangles) {
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }
}
