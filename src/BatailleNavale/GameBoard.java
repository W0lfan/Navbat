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
    }

    public List<Rectangle> getRect() {
        return this.filledRectangles;
    }


}
