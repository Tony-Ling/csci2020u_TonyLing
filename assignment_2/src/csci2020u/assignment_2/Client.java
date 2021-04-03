package csci2020u.assignment_2;

import java.util.*;
import java.io.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Client extends Application {

    final private String fileDIR = "src/csci2020u/assignment_2/shared/";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("File Sharer v1.0");

        Button dirButton = new Button("DIR");
        Button downloadButton = new Button("Download");
        Button uploadButton = new Button("Upload");
        Button openFileButton = new Button("Open File");
        Button deleteFileButton = new Button("Delete FIle");

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

        openFileButton.setOnAction(e -> {
            ObservableList viewFile = localView.getSelectionModel().getSelectedItems();
            
            for(Object o : viewFile) {
                readFile(o.toString());
            }
        });

        deleteFileButton.setOnAction(e -> {
            ObservableList deleteFile = localView.getSelectionModel().getSelectedItems();
            
            for(Object o : deleteFile) {
                deleteFile(o.toString());
            }
        });

        File DIR = new File(fileDIR);
        File[] listOfFiles = DIR.listFiles();

        for(File file : listOfFiles) {
            if(file.isFile()) {
                localView.getItems().add(file.getName());
            }
        }

        VBox vb = new VBox();
        HBox hb = new HBox();
        GridPane gp = new GridPane();

        gp.add(dirButton, 0, 0);
        gp.add(downloadButton, 1, 0);
        gp.add(uploadButton, 2, 0);
        gp.add(openFileButton, 3, 0);
        gp.add(deleteFileButton, 4, 0);

        hb.getChildren().addAll(serverView, localView);
        vb.getChildren().addAll(gp, hb);

        Scene scene = new Scene(vb);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void readFile(String file) {
        List<String> read = new ArrayList<String>();
        String line;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileDIR + file));

            while((line = br.readLine()) != null) {
                read.add(line);
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        final StringBuilder sb = new StringBuilder();
        
        for(String element : read) {
            sb.append(element + "\n");
        }

        Text text = new Text();
        text.setText(sb.toString());

        ScrollPane sp = new ScrollPane();
        sp.setPrefSize(450, 450);
        sp.setContent(text);
        
        Scene scene = new Scene(sp, 450, 450);

        Stage viewFile = new Stage();
        viewFile.setTitle("File Viewer");
        viewFile.setScene(scene);
        viewFile.show();
    }

    public void deleteFile(String file) {
        File delFile = new File(fileDIR + file);
        if(delFile.delete()) {
            System.out.println("Deleted the file: " + delFile.getName());
        } else {
            System.out.println("Failed");
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
