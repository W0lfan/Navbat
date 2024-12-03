package BatailleNavale;

import BatailleNavale.utils.Functionnal;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Ship extends Canvas {
    private int width;
    private int height;
    private int x;
    private int y;
    private String defBoard;
    private HashMap<String,Boolean> occupiedCells = new HashMap<>();


    public Ship(int w, int h, int x, int y, String d) {
        if (w<=0 && h<=0) throw new IllegalArgumentException("Error: width or height of the ship must be positive.");

        this.width=w;
        this.height=h;
        this.x=x;
        this.y=y;
        this.defBoard=d;

        this.occupiedCells = Functionnal.getAllCellGameViaCoordinates(
                x,y,w,h
        );

        System.out.println(this.toString());
    }

    public void paint(Graphics graphics) {
        graphics.drawString("BRUH",60,60);
    }

    public HashMap<String,Boolean> getOccupiedCells() {
        return this.occupiedCells;
    }

    public String toString() {
        return String.format("Ship is size %dx%d at (%d,%d)\nOccupying cells:%s",this.width,this.height,this.x,this.y, this.occupiedCells.toString());
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



    public boolean isShot(String cell) {
        if (this.occupiedCells.get(cell) == null || this.occupiedCells.get(cell)) {
            return false;
        } else {
            this.occupiedCells.put(cell, true);
            return true;
        }
    }

    public boolean isDead() {
        for (String key : this.occupiedCells.keySet()) {
            if (!this.occupiedCells.get(key)) {
                return false;
            }
        }

        return true;
    }
}
