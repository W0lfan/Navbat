package BatailleNavale.utils;

import BatailleNavale.GameBoard;
import BatailleNavale.Player;
import BatailleNavale.Ship;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Graphical {

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

    public static void paintGrid(Graphics g, Player player) {
        int paddingTop = GameParameters.paddingTop;
        int paddingRight = GameParameters.paddingRight;
        int width = GameParameters.boardWidth;
        int height = GameParameters.boardHeight;
        int caseWidth = GameParameters.caseWidth;
        int caseHeight = GameParameters.caseHeight;


        g.setColor(Color.BLACK);
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < width; i++) {
            g.drawString(
                    Integer.toString(i + 1),
                    (int) ((int) paddingRight * 1.5 + i * caseWidth - 5),
                    paddingTop / 2
            );
            g.drawString(
                    alpha.split("")[i],
                    paddingRight / 2,
                    (int) ((int) paddingTop * 1.5 + i * caseHeight + 5)
            );
            for (int j = 0; j < height; j++) {
                g.drawRect(
                        paddingRight + i * caseWidth,
                        paddingTop + j * caseHeight,
                        caseWidth,
                        caseHeight
                );
            }
        }


        if (player != null) {
            GameBoard gameBoard = GameParameters.getGameBoardOfPlayer(player);
            if (gameBoard != null) {
                ArrayList<String> m = gameBoard.getMiss();
                for (String missCell : m) {
                    Integer[] xY = Functionnal.coordinatesViaCellGame(missCell);
                    g.drawRect(
                            paddingRight + xY[0] * caseWidth,
                            paddingTop + xY[1] * caseHeight,
                            caseWidth,
                            caseHeight
                    );
                }
            }


            for (Ship s : player.ships) {
                Graphical.paintShip(
                        s.getWidth(),
                        s.getHeight(),
                        paddingRight+(s.getX()-1)*caseWidth,
                        paddingTop+(s.getY()-1)*caseHeight,
                        g
                );
            }
        }
    }

}
