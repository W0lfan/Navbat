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

    public static void main(String[] args) {

        Serveur s = new Serveur();
        GameParameters.addPlayer(s);
        try (ServerSocket server = new ServerSocket(4444);
             Socket client = server.accept()) {

            System.out.println("BatailleNavale.Client connecté !");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            Scanner y = new Scanner(System.in);
            s.in = in;
            s.out = out;

            while (true) {

                String receivedMessage = in.readLine();
                if (receivedMessage != null && !receivedMessage.isEmpty()) {
                    if ("Ready".equals(receivedMessage)) {
                        System.out.println("STARTING FIGHT");
                        s.phase="Fight";
                        out.println("Fight");
                        s.c2.repaint();
                    }
                    System.out.println("Message reçu : " + receivedMessage);
                    System.out.println("Message reçu : " + receivedMessage=="Ready");

                }






            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
