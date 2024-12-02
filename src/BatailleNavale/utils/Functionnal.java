package BatailleNavale.utils;

import BatailleNavale.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Functionnal {
    public static boolean listContainsElementOfOtherList(
            ArrayList<Integer[]> l1,
            ArrayList<Integer[]> l2
    ) {
        boolean flag = false;
        int i = 0;
        while (i < l1.size() && !flag) {
            int j = 0;
            while (j<l2.size() && !flag) {
                if (
                        l1.get(i)[0] == l2.get(j)[0] &&
                        l1.get(i)[1] == l2.get(j)[1]
                ) flag=true;
                j++;
            }
            i++;
        }
        return flag;
    }



    public static boolean availableInPhases(String p) {
        int i = 0;
        while (i < GameParameters.getPhases().length && GameParameters.getPhases()[i] != p) {
            i++;
        }
        return i!=GameParameters.getPhases().length;
    }

    public static Integer[] missingShip(Player p) {
        int i = 0;
        while (i < GameParameters.shipsSize.length && p.ships.size() != i) {
            i++;
        }

        if (i==GameParameters.shipsSize.length) return null;
        else return GameParameters.shipsSize[i];
    }

    public static boolean withinTheRange(
            int cubeLeft,
            int cubeTop,
            int cubeRight,
            int cubeBottom,
            int x,
            int y
    ) {
        return x > cubeLeft && x < cubeRight && y > cubeTop && y < cubeBottom;
    }

    public static Integer[] getCorrespondingCell(int x, int y) {
        int caseWidth = GameParameters.caseWidth;
        int caseHeight = GameParameters.caseHeight;
        int paddingTop = GameParameters.paddingTop;
        int paddingRight = GameParameters.paddingRight;
        int a = GameParameters.boardWidth;
        for (int i = 0 ; i < a;i ++) {
            for (int j = 0; j < a; j++) {
                if (
                        Functionnal.withinTheRange(
                                paddingRight+i*caseWidth,
                                paddingTop+j*caseHeight,
                                paddingRight+i*caseWidth+caseWidth,
                                paddingTop+j*caseHeight+caseHeight,
                                x, y
                        )
                ) {
                    Integer[] k = {
                            paddingRight+i*caseWidth,
                            paddingTop+j*caseHeight
                    };
                    return k;
                }
            }
        }

        return null;
    }


    public static Integer[] nearestCase(int x, int y, int shipWidth, int shipHeight) {
        /*

            I once again have to deal with this ****
            Usual method:
            * Check if the click is within the grid

         */
        int caseWidth = GameParameters.caseWidth;
        int caseHeight = GameParameters.caseHeight;
        int paddingTop = GameParameters.paddingTop;
        int paddingRight = GameParameters.paddingRight;
        int width = GameParameters.boardWidth*caseWidth;
        int height = GameParameters.boardHeight*caseHeight;



        int boardX = paddingRight;
        int boardY = paddingTop;
        int boardXMax = boardX+width;
        int boardYMax = boardY+height;

        System.out.println("-------------");
        System.out.println("Max width : " + (x + shipWidth*caseWidth-5));
        System.out.println("Max height : " + (y+shipHeight*caseHeight-5));
        System.out.println("Width" + shipWidth);
        System.out.println("Height" + shipHeight);
        if (Functionnal.withinTheRange(boardX, boardY, boardXMax, boardYMax, x, y)) {
            System.out.println("Click in the board");
            Integer[] k = Functionnal.getCorrespondingCell(x,y);

            if (Functionnal.withinTheRange(
                    boardX, boardY, boardXMax, boardYMax, k[0] + shipWidth*caseWidth-1, k[1] + shipHeight*caseHeight-1
            )) {
                return k;
            }
            return null;
        }

        return null;
    }

    public static Integer[] cellViaCoordinates(int canvasX, int canvasY) {
        int adjustedCanvasX = canvasX-GameParameters.caseWidth;
        int adjustedCanvasY = canvasY-GameParameters.caseHeight;

        // 400 = 10

        Integer[] k = {
           adjustedCanvasX*10/400+1,
                adjustedCanvasY*10/400+1
        };

        return k;
    }

    public static String cellGameViaCoordinates(int y, int x) {
        String ALPHA = "ABCDEFGHIJ";
        return String.format("%c%d",ALPHA.charAt(x-1),y);
    }
}
