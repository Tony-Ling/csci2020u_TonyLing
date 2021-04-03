package csci2020u.assignment_2;

import java.io.*;
import java.net.*;

public class Server {

    private static ServerSocket serverSocket;
    private static Socket clientSocket = null;
    private final static int socketPort = 100747421;
    public static void main(String[] args) throws IOException {

        try {
            serverSocket = new ServerSocket(socketPort);
            System.out.println("Starting Server...");
        } catch(Exception e) {
            System.err.println("Unable to Connect to Server.");
            serverSocket.close();
            System.exit(1);
        }

        while(true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Connection with new Client: " + clientSocket);

                Thread t = new Thread(new ClientHandler(clientSocket));

                t.start();

            } catch(Exception e) {
                System.err.println("Connection Error.");
                serverSocket.close();
                System.exit(1);
            }
        }
    }
}
