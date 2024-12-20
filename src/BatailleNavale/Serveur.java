package BatailleNavale;

import BatailleNavale.utils.GameParameters;
import BatailleNavale.utils.Graphical;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serveur extends Player {
    public Serveur() {
        super(0); // Ensure the parent constructor is called
        System.out.println("Server created with ID: " + getPlayerID());
    }


    public static void main(String[] args) {

        Serveur s = new Serveur();
        GameParameters.addPlayer(s);
        try (ServerSocket server = new ServerSocket(4444);
             Socket client = server.accept()) {

            System.out.println("BatailleNavale.Client connecté !");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            s.in = in;
            s.out = out;

            while (true) {
                s.manageWhile(in);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
