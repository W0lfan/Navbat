package BatailleNavale;

import BatailleNavale.utils.Functionnal;
import BatailleNavale.utils.GameParameters;
import BatailleNavale.utils.Graphical;
import BatailleNavale.utils.errors.ShipPilingUp;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;



public class Player extends Canvas {
    public ArrayList<Ship> ships = new ArrayList<>();
    public ArrayList<String> busyCells = new ArrayList<>();
    public String phase = "Placement";

    public BufferedReader in;
    public PrintWriter out;

    public Canvas c1;
    public Canvas c2;

    private final int playerID;


    public int getPlayerID() {
        return this.playerID;
    }

    public Player(int id) {
        Player t = this;
        /*
            Ici l'usage de variable statique pour incrémenter l'ID posait problème,
            alors j'ai décidé de directement faire en sorte que le code choisisse l'ID
            via le constructeur
         */
        this.playerID=id;

        Canvas canvas1 = new Canvas() {
            @Override
            public void paint(Graphics g) {
                Graphical.paintGrid(g, t);
            }
        };

        Canvas canvas2 = new Canvas() {
            @Override
            public void paint(Graphics g) {
                g.drawString(
                        String.format("Phase: %s", GameParameters.getPhase()),
                        GameParameters.paddingRight, GameParameters.windowHeight - 50
                );
                if (t.phase == "Placement") {
                    g.setColor(Color.BLACK);
                    if (t.ships.size() < GameParameters.shipsSize.length) {
                        g.drawString("Veuillez placer le bateau suivant:",GameParameters.paddingRight, 50);
                        Integer[] size = Functionnal.missingShip(t);
                        Graphical.paintShip(
                                size[0], size[1], GameParameters.paddingRight, 100, g
                        );
                    } else {
                        g.drawString("EN ATTENTE DU JOUEUR ENNEMI",GameParameters.paddingRight, 50);
                        out.println("Ready");
                        t.phase="Fight";
                        System.out.println("Message ready envoyé");
                        this.repaint();
                    }
                } else if (t.phase == "Fight" ) {
                    System.out.println("Drawing fight");
                    if (GameParameters.amIPlaying(t)) {
                        g.drawString("EN COMBAT! Choisissez une case",GameParameters.paddingRight, GameParameters.windowHeight-200);
                    } else {
                        g.drawString("EN COMBAT! Votre ennemi choisi une case",GameParameters.paddingRight, GameParameters.windowHeight-200);
                    }
                    Graphical.paintGrid(
                            g, null
                    );
                }

            }
        };


        Frame f = new Frame();

        f.setSize(GameParameters.windowWidth, GameParameters.windowHeight);
        f.setLayout(new GridLayout(1, 2));

        canvas1.setBackground(Color.LIGHT_GRAY);
        canvas2.setBackground(Color.WHITE);
        this.c1=canvas1;
        this.c2=canvas2;
        // Add canvases to the frame
        f.add(canvas1);
        f.add(canvas2);
        f.setTitle("Bataille Navale");

        // Handle window closing
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

        canvas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println(String.format("Click on (%d,%d)", e.getX(), e.getY()));
                Integer[] size = Functionnal.missingShip(t);

                Integer[] nearestCase = Functionnal.nearestCase(e.getX(), e.getY(),size[0],size[1] );
                if (nearestCase != null) {
                    Integer[] cellsCoordinates =  Functionnal.cellViaCoordinates(e.getX(), e.getY());
                    String charCode = Functionnal.cellGameViaCoordinates(cellsCoordinates[0],cellsCoordinates[1]);
                    System.out.println(charCode);
                    if (!t.busyCells.contains(charCode)) {
                        try {
                            t.addShip(
                                    size[0],size[1],
                                    cellsCoordinates[0],cellsCoordinates[1],
                                    charCode
                            );
                            canvas2.repaint();
                        } catch (ShipPilingUp ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                canvas1.repaint();
            }
        });


        canvas2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println(String.format("Click on (%d,%d)", e.getX(), e.getY()));
                Integer[] size = Functionnal.missingShip(t);

                Integer[] nearestCase = Functionnal.nearestCase(e.getX(), e.getY(),size[0],size[1] );
                if (nearestCase != null) {
                    Integer[] cellsCoordinates =  Functionnal.cellViaCoordinates(e.getX(), e.getY());
                    String charCode = Functionnal.cellGameViaCoordinates(cellsCoordinates[0],cellsCoordinates[1]);
                    System.out.println(charCode);
                    if (!t.busyCells.contains(charCode)) {
                        try {
                            t.addShip(
                                    size[0],size[1],
                                    cellsCoordinates[0],cellsCoordinates[1],
                                    charCode
                            );
                            canvas2.repaint();
                        } catch (ShipPilingUp ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                canvas1.repaint();
            }
        });

        f.setVisible(true);
    }


    public void addShip(int width, int height, int x, int y, String p) throws ShipPilingUp {
        if (this.shipPilesUp(width, height, x, y)) throw new ShipPilingUp(width,height,x,y);
        else  {
            ships.add(new Ship(width,height,x,y,p));
            busyCells.add(p);
        };
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



}
