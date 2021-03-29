package csci2020u.assignment_2;

import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("File Sharer");

        Button dirButton = new Button("DIR");
        Button downloadButton = new Button("Download");
        Button uploadButton = new Button("Upload");

        ListView<String> serverView = new ListView<String>();
        ListView<String> localView = new ListView<String>();
        
        dirButton.setOnAction(e -> {
            System.out.println("testing 1");
        });

        downloadButton.setOnAction(e -> {
            System.out.println("testing 2");
        });

        uploadButton.setOnAction(e -> {
            System.out.println("testing 3");
        });

        VBox vb = new VBox();
        HBox hb = new HBox();
        GridPane gp = new GridPane();

        gp.add(dirButton, 0, 0);
        gp.add(downloadButton, 1, 0);
        gp.add(uploadButton, 2, 0);

        hb.getChildren().addAll(serverView, localView);
        vb.getChildren().addAll(gp, hb);

        Scene scene = new Scene(vb);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
