package BatailleNavale;

import BatailleNavale.utils.GameParameters;
import BatailleNavale.utils.Graphical;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Player {


    public static void main(String[] args) {
        Client c = new Client();
        GameParameters.addPlayer(c);
        try (Socket client = new Socket("localhost", 4444)) {
            System.out.println("Connecté au serveur !");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            Scanner y = new Scanner(System.in);

            c.in = in;
            c.out = out;

            while (true) {

                String receivedMessage = in.readLine();
                if (receivedMessage != null && !receivedMessage.isEmpty()) {
                    if (receivedMessage == "Ready" && c.phase != "Ready") {
                        c.phase = "Fight";
                        out.println("Fight");
                        c.c2.repaint();
                    }
                    System.out.println("Message reçu : " + receivedMessage);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
