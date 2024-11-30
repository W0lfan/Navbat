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
        Frame f = Graphical.init(s);
        try (ServerSocket server = new ServerSocket(4444);
             Socket client = server.accept()) {

            System.out.println("BatailleNavale.Client connecté !");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            Scanner y = new Scanner(System.in);

            s.connected=true;
            s.repaint();

            while (true) {
                // Envoi de message si l'utilisateur a saisi quelque chose
                String messageToSend = y.nextLine();
                out.println(messageToSend);
                System.out.println("Message envoyé !");



                String receivedMessage = in.readLine();
                if (receivedMessage != null && !receivedMessage.isEmpty()) {
                    System.out.println("Message reçu : " + receivedMessage);
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
