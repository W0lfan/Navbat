package BatailleNavale.utils;

import BatailleNavale.Player;
import BatailleNavale.Ship;

import java.util.ArrayList;

public abstract class GameParameters {
    public static int boardWidth = 10;
    public static int boardHeight = 10;
    public static int windowWidth = 1080;
    public static int windowHeight = 700;

    private static String phase = "Placement";
    private static String[] phases = {
            "Placement",
            "Fight",
            "End"
    };

    public static ArrayList<Player> players = new ArrayList<>();

    public static int paddingTop = 40;
    public static int paddingRight = 40;

    public static int caseWidth = 40;
    public static int caseHeight = 40;


    public static void addPlayer(Player p) {
        players.add(p);
    }
    public static String getPhase() {
        return phase;
    }
    public static String[] getPhases() {
        return phases;
    }

    public static void updatePhase(String p) {
      if (Functionnal.availableInPhases(p)) {
          System.out.println("Changing game phase");
          GameParameters.phase = p;
      } else {
          throw new IllegalArgumentException("Error: the phase you are trying to go to doesn't exist.");
      }
    }

    public static Integer[][] shipsSize = {
            {2,3}, {1,1},{3,3}
    } ;
}
