package BatailleNavale;

import BatailleNavale.utils.Functionnal;
import BatailleNavale.utils.GameParameters;
import BatailleNavale.utils.Graphical;
import BatailleNavale.utils.errors.ShipPilingUp;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.Function;

public class Player extends Canvas {
    public ArrayList<Ship> ships = new ArrayList<>();
    boolean connected = false;

    public void addShip(int width, int height, int x, int y) throws ShipPilingUp {
        if (this.shipPilesUp(width, height, x, y)) throw new ShipPilingUp(width,height,x,y);
        else ships.add(new Ship(width,height,x,y));
    }


    public boolean shipPilesUp(int width, int height, int x, int y) {
        int i = 0;
        // This flag ensures that if some ships are getting piled up, shipPilesUp returns true
        boolean flag = false;
        ArrayList<Integer[]> framesTakenByNewShip = busyFrames(
                width, height, x, y
        );

        while (i<ships.size() && !flag)  {
            Ship ship = ships.get(i);
            ArrayList<Integer[]> framesTakenByShip = busyFrames(
                    ship.getWidth(),
                    ship.getHeight(),
                    ship.getX(),
                    ship.getY()
            );

            if (Functionnal.listContainsElementOfOtherList(
                    framesTakenByShip, framesTakenByNewShip
            )) {
                flag=true;
            }


            i++;
        }


        return flag;
    }

    public static ArrayList<Integer[]> busyFrames(int width, int height, int x, int y) {
        ArrayList<Integer[]> frames = new ArrayList<>();

        for (int i = x; i < x+width; i++) {
            for (int j = y; j < y+height; j++) {
                Integer[] k = {i,j};
                frames.add(k);
            }
        }

        return frames;
    }



    public void paint(Graphics g) {
        Graphical.paint(g);
        if (!this.connected) {
            g.setColor(Color.RED);
            g.drawString("Aucun joueur n'est connecté",10, GameParameters.windowHeight-50);
        } else {
            g.setColor(Color.GREEN);
            g.drawString("Un joueur est connecté",10,GameParameters.windowHeight-50);
        }

        if (GameParameters.getPhase() == "Placement") {
            g.setColor(Color.BLACK);
            System.out.println(this.ships);
            if (this.ships.size() < GameParameters.shipsSize.length) {
                g.drawString("Veuillez placer le bateau suivant:",(GameParameters.windowWidth/3)*2, 50);
                Integer[] size = Functionnal.missingShip(this);
                Graphical.paintShip(
                    size[0], size[1], (GameParameters.windowWidth/3)*2, 100, g
                );
            }
        }
    }
}
