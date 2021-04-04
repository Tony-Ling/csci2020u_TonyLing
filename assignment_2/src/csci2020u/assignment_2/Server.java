/**
 * Tony Ling
 * STU #: 100747421
 * 
 * CSCI2020 Assignment 2
 * 
 */

package csci2020u.assignment_2;

import java.io.*;
import java.net.*;

/**
 * This class file contains the socket server and the threads which is 
 * used to excute the ClientHandler class.
 */
public class Server {

    private static ServerSocket serverSocket;
    private static Socket clientSocket = null;
    private final static int socketPort = 1212;
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

                ClientHandler ch = new ClientHandler(clientSocket);

                Thread t = new Thread(ch);

                t.start();

            } catch(Exception e) {
                System.err.println("Connection Error.");
                serverSocket.close();
                System.exit(1);
            }
        }
    }
}
