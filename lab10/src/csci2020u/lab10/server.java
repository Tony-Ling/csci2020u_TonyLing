package csci2020u.lab10;

import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.*;

public class server extends Application {
    public static TextArea textArea;
    List<clientHandler> connectionList = new ArrayList<clientHandler>();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lab 10 - Server");
        textArea = new TextArea();
        textArea.setEditable(false);

        ScrollPane sp = new ScrollPane();
        sp.setContent(textArea);
        sp.setPrefViewportWidth(300);
        sp.setPrefViewportHeight(300);

        Button exitButton = new Button("Exit");
        
        exitButton.setOnAction(e -> {
            System.exit(1);
        });

        VBox vb = new VBox();
        vb.getChildren().addAll(sp, exitButton);

        Scene scene = new Scene(vb, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    

        new Thread(() -> {
            try {
                // Create a server socket
                ServerSocket serverSocket = new ServerSocket(4444);
                
                //continous loop
                while (true) {
                    // Listen for a connection request, add new connection to the list
                    Socket socket = serverSocket.accept();
                    clientHandler connection = new clientHandler(socket, this);
                    connectionList.add(connection);

                    //create a new thread
                    Thread thread = new Thread(connection);
                    thread.start();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
