package BatailleNavale.utils;

import BatailleNavale.GameBoard;

import java.awt.*;

public abstract class Graphical {

    public static Frame init(Object object) {
        Frame f = new Frame();

        GameBoard gameBoard = new GameBoard();
        f.add(gameBoard);
        f.setSize(GameParameters.windowWidth, GameParameters.windowHeight);
        f.setTitle("Bataille Navale");
        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

        gameBoard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println(String.format("Click on (%d,%d)", e.getX(), e.getY()));

                Integer[] nearestCase = Functionnal.nearestCase(e.getX(), e.getY());
                if (nearestCase != null) {
                    System.out.println("Nearest case is " + nearestCase[0] + " " + nearestCase[1]);
                    gameBoard.addRectangle(
                            nearestCase[0],
                            nearestCase[1],
                            GameParameters.caseWidth,
                            GameParameters.caseHeight
                    );
                }
            }
        });

        f.setVisible(true);

        return f;
    }
    public static void displayConnectionStatus(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.drawString("Connected",0,0);
    }


    public static void paintShip(int width, int height, int x, int y, Graphics g) {
        int caseWidth = GameParameters.caseWidth;
        int caseHeight = GameParameters.caseHeight;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                g.fillRect(
                        x+caseWidth*i,
                        y+caseHeight*j,
                        caseWidth, caseHeight
                );
            }
        }
    }

    public static void paint(Graphics graphics) {
        // For safety, make sure paddings are even
        int paddingTop = GameParameters.paddingTop;
        int paddingRight = GameParameters.paddingRight;
        int width = GameParameters.boardWidth;
        int height = GameParameters.boardHeight;

        int caseWidth = GameParameters.caseWidth;
        int caseHeight = GameParameters.caseHeight;

        graphics.drawString(String.format("Phase: %s",GameParameters.getPhase()),GameParameters.windowWidth-200, GameParameters.windowHeight-50);



        graphics.setColor(Color.BLACK);


        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < width; i++) {
            graphics.drawString(Integer.toString(i+1), (int) ((int) paddingRight*1.5+i*caseWidth - 5),paddingTop/2);
            graphics.drawString(
                    alpha.split("")[i],
                    paddingRight/2,
                    (int) ((int) paddingTop*1.5+i*caseHeight + 5)
            );
            for (int j = 0; j < height; j++) {
                graphics.drawRect(
                        paddingRight + i*caseWidth,
                        paddingTop + j*caseHeight,
                        caseWidth,
                        caseHeight
                );
            }
        }

    }
}
