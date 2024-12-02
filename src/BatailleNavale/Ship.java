package BatailleNavale;

import java.awt.*;

public class Ship extends Canvas {
    private int width;
    private int height;
    private int x;
    private int y;
    private String defBoard;

    public Ship(int w, int h, int x, int y, String d) {

        if (w<=0 && h<=0) throw new IllegalArgumentException("Error: width or height of the ship must be positive.");

        this.width=w;
        this.height=h;
        this.x=x;
        this.y=y;
        this.defBoard=d;


    }

    public void paint(Graphics graphics) {
        graphics.drawString("BRUH",60,60);
    }


    public String toString() {
        return String.format("Ship is size %dx%d at (%d,%d)",this.width,this.height,this.x,this.y);
    }

    public String getDefBoard() { return this.defBoard; }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
