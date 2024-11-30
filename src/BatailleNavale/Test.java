package BatailleNavale;

import BatailleNavale.utils.Functionnal;
import BatailleNavale.utils.errors.ShipPilingUp;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws ShipPilingUp {
        Client c = new Client();
        c.addShip(1,2,0,0);
        c.addShip(2,1,1,0);


        System.out.println(
                Functionnal.withinTheRange(
                        10,10,20,20,
                        14,14
                )
        );
    }
}
