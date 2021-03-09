package csci2020u.assignment1;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public File file;
    public String[] fileList;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Spam Master 3000");

        
        TextField dir = new TextField();
        String startDir = "D:/Codes/csci2020u_TonyLing/assignment1/data/train/ham";
        dir.setText(startDir);
        dir.setPromptText("ie. C:/aaa/bbb/ccc");
    

        Button confirmDirButton = new Button("Confirm Directory");
        confirmDirButton.setOnAction(value -> {
            file = new File(dir.getText());
            fileList = file.list();
        });
        
        file = new File(dir.getText());
        fileList = file.list();
        List<String> fileNameList = Arrays.asList(fileList);
        List<String> fileDirectoryList = new ArrayList<>();
        List<Float> spamProbList = new ArrayList<>();

        for (int i = 0; i < fileNameList.size(); i++) {
            fileDirectoryList.add(file.getName());
            spamProbList.add(0.0f);
        }

        TableView<Integer> table = new TableView<>();
        for (int i = 0; i < fileNameList.size(); i++) {
            table.getItems().add(i);
        }

        TableColumn<Integer, String> fileCol = new TableColumn<>("File");
        fileCol.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(fileNameList.get(rowIndex));
        });

        TableColumn<Integer, String> pathCol = new TableColumn<>("Actual Class");
        pathCol.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyStringWrapper(fileDirectoryList.get(rowIndex));
        });

        TableColumn<Integer, Number> spamCol = new TableColumn<>("Spam Probability");
        spamCol.setCellValueFactory(cellData -> {
            Integer rowIndex = cellData.getValue();
            return new ReadOnlyFloatWrapper(spamProbList.get(rowIndex));
        });

        table.getColumns().addAll(fileCol, pathCol, spamCol);

        Button clearDataButton = new Button("Clear Data");
        clearDataButton.setOnAction(value -> {
            table.getItems().clear();
        });

        VBox vb = new VBox(dir, confirmDirButton, table, clearDataButton);

        Scene scene = new Scene(vb, 475, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
