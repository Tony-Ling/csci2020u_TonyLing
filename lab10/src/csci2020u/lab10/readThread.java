package csci2020u.lab10;

import java.io.*;
import java.net.*;

import javafx.application.Platform;

public class readThread implements Runnable {
    Socket socket;
    client client;
    DataInputStream input;

    public readThread(Socket socket, client client) {
        this.socket = socket;
        this.client = client;
    }

    @Override
    public void run() {
        while(true) {
            try {
                input = new DataInputStream(socket.getInputStream());
                String message = input.readUTF();
                Platform.runLater(() -> {
                    server.textArea.appendText(message);
                });
            } catch(IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
