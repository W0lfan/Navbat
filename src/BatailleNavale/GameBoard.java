package BatailleNavale;

import BatailleNavale.utils.Graphical;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameBoard extends Canvas {

    private ArrayList<Ship> ships = new ArrayList<>();

    private ArrayList<String> miss = new ArrayList<>();

    public ArrayList<String> getMiss() {
        return this.miss;
    }

    public int getShipsLength() {
        return this.ships.size();
    }

    public int shipIndexInBoard(String pos) {
        int i = 0;
        while (i < ships.size()) {
            Ship s = ships.get(i);
            if (s.getOccupiedCells().containsKey(pos)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean setShipShot(int i, String cell) {
        if (this.ships.get(i) != null && !this.miss.contains(cell) && !this.ships.get(i).isDead()) {
            boolean shotOK = this.ships.get(i).isShot(cell);
            if (!shotOK) {
                this.miss.add(cell);
            }
            return shotOK;
        } else {
            return true;
        }
    }



    public void addShip(Ship ship) {
        this.ships.add(ship);
    }


}
