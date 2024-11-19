import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket client = new Socket("192.168.38.98", 4444)) {
            System.out.println("Connecté au serveur !");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            Scanner y = new Scanner(System.in);

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
}
