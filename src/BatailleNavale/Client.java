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

    public boolean connected = false;

    public static void main(String[] args) {
        Client c = new Client();
        GameParameters.addPlayer(c);
        Frame f = Graphical.init(c);
        try (Socket client = new Socket("localhost", 4444)) {
            System.out.println("Connecté au serveur !");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            Scanner y = new Scanner(System.in);

            c.connected=true;
            c.paint(c.getGraphics());
            while (true) {
                String receivedMessage = in.readLine();
                if (receivedMessage != null && !receivedMessage.isEmpty()) {
                    System.out.println("Message reçu : " + receivedMessage);
                }

                String messageToSend = y.nextLine();
                out.println(messageToSend);
                System.out.println("Message envoyé !");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void paint(Graphics g) {
        Graphical.paint(g);
    }
}
