package csci2020u.lab10;

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

public class client extends Application {
    DataOutputStream output;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primaryStage.setTitle("Lab 10 - Client");

        
        Label usernameLabel = new Label("Username:");
        Label messageLabel = new Label("Message:");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        TextField messageField = new TextField();
        messageField.setPromptText("Enter Message");

        Button sendButton = new Button("Send");
        Button exiButton = new Button("Exit");

        sendButton.setOnAction(e -> {
            try {
                output.writeUTF(usernameField.getText() + ": " + messageField.getText() + "\n");
                output.flush();
                messageField.clear();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        });

        exiButton.setOnAction(e -> {
            System.exit(1);
        });

        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setAlignment(Pos.CENTER);

        gp.add(usernameLabel, 0, 0);
        gp.add(usernameField, 1, 0);
        gp.add(messageLabel, 0, 1);
        gp.add(messageField, 1, 1);
        gp.add(sendButton, 0, 2);
        gp.add(exiButton, 0, 3);

        Scene scene = new Scene(gp, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            Socket socket = serverSocket.accept();
            output = new DataOutputStream(socket.getOutputStream());
            readThread task = new readThread(socket, this);
            Thread thread = new Thread(task);
            thread.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
