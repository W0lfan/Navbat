package BatailleNavale.utils;

import BatailleNavale.GameBoard;
import BatailleNavale.Player;
import BatailleNavale.Ship;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class GameParameters {
    public static int boardWidth = 10;
    public static int boardHeight = 10;
    public static int windowWidth = 1080;
    public static int windowHeight = 700;
    private static int playingPlayer = 0;

    private static HashMap<Integer, GameBoard> gameBoards = new HashMap<>();

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
        gameBoards.put(
                p.getPlayerID(),
                new GameBoard()
        );
    }

    public static void addShipsToGameBoard(Player p) {
        GameBoard g = getGameBoardOfPlayer(p);
        if (g.getShipsLength() == 0) {
            for (Ship s : p.ships) {
                g.addShip(s);
            }
            gameBoards.put(p.getPlayerID(),g);
        }
    }

    public static void repaintAll() {
        for (int i = 0 ; i < players.size(); i++) {
            Player $ = players.get(i);
            $.c1.repaint();
            $.c2.repaint();
        }
    }

    public static GameBoard getGameBoardOfPlayer(Player p) {
        return gameBoards.get(p.getPlayerID());
    }

    public static String getPhase() {
        return phase;
    }
    public static String[] getPhases() {
        return phases;
    }
    public static int getRunningPlayer() { return playingPlayer; }
    public static void newPlayingPlayer() {
        if (playingPlayer == 1) {
            playingPlayer=0;
        } else {
            playingPlayer=1;
        }
        System.out.println("Switched player players to " + playingPlayer);
    }
    public static boolean amIPlaying(int id) {
        return id == playingPlayer;
    }





    public static Integer[][] shipsSize = {
            {1,5}
    } ;


}
