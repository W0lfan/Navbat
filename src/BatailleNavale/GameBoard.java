package BatailleNavale;

import BatailleNavale.utils.Graphical;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameBoard extends Canvas {

    ArrayList<Ship> ships = new ArrayList<>();







    public void addShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }


}
