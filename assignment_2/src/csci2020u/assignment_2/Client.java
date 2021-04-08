/**
 * Tony Ling
 * STU #: 100747421
 * 
 * CSCI2020 Assignment 2
 * 
 */

package csci2020u.assignment_2;

import java.util.*;
import java.io.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class file is the main executable file that is used to display
 * the javafx UI and to upload the user's selected files from their 
 * local storage to the remote server or download files from the remote
 * server to the user's local storage.
 */
public class Client extends Application {

    final private String fileDIR = "src/csci2020u/assignment_2/shared/";
    public static ListView<String> serverView = new ListView<String>();
    public static ListView<String> localView = new ListView<String>();
    public static boolean selectedDownloadButton = false;

    /**
     * This is the start function which contains all of the javafx functions
     * and the UI and their functionalities. The UI contrains in this class
     * are buttons, various panes and labels.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("File Sharer v1.0");

        Button dirButton = new Button("DIR");
        Button downloadButton = new Button("Download");
        Button uploadButton = new Button("Upload");
        Button openFileButton = new Button("Open File");
        Button deleteFileButton = new Button("Delete FIle");
        Button clearFileListButton = new Button("Clear Local List");

        // The function of this button is to display the files to listview.
        dirButton.setOnAction(e -> {
            addFilesToList();
        });

        // The function of this button is to download the files from the server
        // to the local file storage (in this case, the folder is called "shared").
        downloadButton.setOnAction(e -> {
            selectedDownloadButton = true;
            ClientHandler.downloadFile();
        });

        // The function of this button is to upload the selected file to the server
        // from the local file storage.
        uploadButton.setOnAction(e -> {
            ObservableList deleteFile = localView.getSelectionModel().getSelectedItems();
            
            for(Object o : deleteFile) {
                ClientHandler.uploadFile(o.toString());
            }
        });

        // The function of this button is to open the file's content from the local
        // stoage and display the content in a new scene.
        openFileButton.setOnAction(e -> {
            ObservableList viewFile = localView.getSelectionModel().getSelectedItems();
            
            for(Object o : viewFile) {
                readFile(o.toString());
            }
        });

        // The function of this button is to delete the selected file from the local storage.
        deleteFileButton.setOnAction(e -> {
            ObservableList deleteFile = localView.getSelectionModel().getSelectedItems();
            
            for(Object o : deleteFile) {
                deleteFile(o.toString());
            }
        });

        // The function of this button is to clear the content from the localview.
        clearFileListButton.setOnAction(e -> {
            localView.getItems().clear();
        });

        addFilesToList();
        serverView.getItems().add("test1.txt");
        serverView.getItems().add("test2.csv");

        VBox vb = new VBox();
        VBox serverVB = new VBox();
        serverVB.setSpacing(10);
        VBox localVB = new VBox();
        HBox hb = new HBox();
        GridPane gp = new GridPane();

        Label serverLabel = new Label("Server File List: ");
        Label localLabel = new Label("Local File List: ");

        serverVB.getChildren().addAll(serverLabel, serverView);
        localVB.getChildren().addAll(localLabel, localView);

        gp.add(dirButton, 0, 0);
        gp.add(downloadButton, 1, 0);
        gp.add(uploadButton, 2, 0);
        gp.add(openFileButton, 3, 0);
        gp.add(deleteFileButton, 4, 0);
        gp.add(clearFileListButton, 5, 0);

        hb.getChildren().addAll(serverVB, localVB);
        vb.getChildren().addAll(gp, hb);

        Scene scene = new Scene(vb);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // The function addFilesToList() is to read a folder and display the file names
    // into the local listview.
    public void addFilesToList() {
        File DIR = new File(fileDIR);
        File[] listOfFiles = DIR.listFiles();

        for(File file : listOfFiles) {
            if(file.isFile()) {
                localView.getItems().add(file.getName());
            }
        }
    }

    // The function readFile(String file) is to take a file name as input and
    // read through the file's content and print the content into another scene.
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

    // The function deleteFile(String file) is to delete the selected file from the localview
    public void deleteFile(String file) {
        File delFile = new File(fileDIR + file);
        if(delFile.delete()) {
            System.out.println("Deleted the file: " + delFile.getName());
        } else {
            System.out.println("Failed");
        }
        localView.getItems().clear();
        addFilesToList();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
