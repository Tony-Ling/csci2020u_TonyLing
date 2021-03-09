package csci2020u.assignment1;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public File file;
    public String[] fileList;

    // Javafx stage
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Spam Master 3000");
        
        // change string here to access different directory
        file = new File("D:/Codes/csci2020u_TonyLing/assignment1/data/train/ham");
        fileList = file.list();
        // List to store file names
        List<String> fileNameList = Arrays.asList(fileList);
        // List to store directory names
        List<String> fileDirectoryList = new ArrayList<>();
        // List to store probability of spam
        List<Double> spamProbList = new ArrayList<>();

        // add directory name and probability of spam into list
        for (int i = 0; i < fileNameList.size(); i++) {
            fileDirectoryList.add(file.getName());
            spamProbList.add(getSpamProbability(fileNameList));
        }

        // increasing size of table according to number of files in directory
        TableView<Integer> table = new TableView<>();
        for (int i = 0; i < fileNameList.size(); i++) {
            table.getItems().add(i);
        }

        // creating file column
        TableColumn<Integer, String> fileCol = new TableColumn<>("File");
        fileCol.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(fileNameList.get(rowIndex));
        });

        // creating class column
        TableColumn<Integer, String> pathCol = new TableColumn<>("Actual Class");
        pathCol.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(fileDirectoryList.get(rowIndex));
        });

        // creating spam column
        TableColumn<Integer, Number> spamCol = new TableColumn<>("Spam Probability");
        spamCol.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyDoubleWrapper(spamProbList.get(rowIndex));
        });

        // adding all the columns to table
        table.getColumns().addAll(fileCol, pathCol, spamCol);

        // button to clear data from table
        Button clearDataButton = new Button("Clear Data");
        clearDataButton.setOnAction(value -> {
            table.getItems().clear();
        });

        VBox vb = new VBox(table, clearDataButton);

        Scene scene = new Scene(vb, 475, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public double getSpamProbability(List<String> fileNameList) {
        
        return 0.0000;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
