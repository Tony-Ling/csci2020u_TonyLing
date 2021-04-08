package csci2020u.lab10;

import java.io.*;
import javafx.application.Platform;
import java.net.*;

public class clientHandler implements Runnable {
    
    private Socket socket;
    private server server;
    private DataInputStream input;
    private DataOutputStream output;

    public clientHandler(Socket socket, server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

            while(true) {
                String message = input.readUTF();
                Platform.runLater(() -> {
                    csci2020u.lab10.server.textArea.appendText(message);
                });
            }
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        try {
            output.writeUTF(message);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
