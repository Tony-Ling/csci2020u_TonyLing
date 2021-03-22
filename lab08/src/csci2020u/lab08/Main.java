package csci2020u.lab08;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Main extends Application {

    public static File currentFilename = new File("src/csci2020u/lab08/StudentRecord.csv");
    public TableView<StudentRecord> tableView = new TableView<>();

	@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 08 Solutions");

        TableColumn SIDCol = new TableColumn("SID");
        SIDCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("SID"));

        TableColumn assignCol = new TableColumn("Assignment");
        assignCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("Assignment"));

        TableColumn mExamCol = new TableColumn("Midterm");
        mExamCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("midTerm"));

        TableColumn fExamCol = new TableColumn("Final Exam");
        fExamCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("finalExam"));

        TableColumn fMarkCol = new TableColumn("Final Mark");
        fMarkCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("finalMark"));

        TableColumn lGradeCol = new TableColumn("Letter Grade");
        lGradeCol.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("letterGrade"));
        
        tableView.setItems(DataSource.getAllMarks());
        tableView.getColumns().addAll(SIDCol, assignCol, mExamCol, fExamCol, fMarkCol, lGradeCol);
        
        Menu menu = new Menu("File");

        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As");
        MenuItem exitItem = new MenuItem("Exit");

        newItem.setOnAction(e -> {
            tableView.getItems().clear();
        });

        openItem.setOnAction(e -> {
            try {
                load();
            } catch(Exception e1) {
                e1.printStackTrace();
            }
        });

        saveItem.setOnAction(e -> {
            try {
                save(currentFilename);
            } catch(Exception e1) {
                e1.printStackTrace();
            }
        });

        saveAsItem.setOnAction(e -> {
            FileChooser fc = new FileChooser();
            File newFile = fc.showSaveDialog(primaryStage);
            if(!newFile.getName().contains(".")) {
                newFile = new File(newFile.getAbsolutePath() + ".csv");
            }
            currentFilename = newFile;
            try {
                save(currentFilename);
            } catch(Exception e1) {
                e1.printStackTrace();
            }
        });

        exitItem.setOnAction(e -> {
            System.exit(0);
        });

        TextField textSID = new TextField();
        textSID.setPromptText("SID");
        TextField textAssign = new TextField();
        textAssign.setPromptText("Assignment / 100");
        TextField textMidterm = new TextField();
        textMidterm.setPromptText("Midterm / 100");
        TextField textFExam = new TextField();
        textFExam.setPromptText("Final Exam / 100");

        Text labelSID = new Text("SID:");
        Text labelAssign = new Text("Assignment:");
        Text labelMidterm = new Text("Midterm:");
        Text labelFExam = new Text("Final Exam:");

        Button add = new Button("Add");
        add.setOnAction(e -> {
            tableView.setItems(DataSource.addMarks(textSID.getText(), Float.parseFloat(textAssign.getText()),
            Float.parseFloat(textMidterm.getText()), Float.parseFloat(textFExam.getText())));
        });

        GridPane gp = new GridPane();
        gp.setVgap(5);
        gp.setHgap(5);
        gp.add(labelSID, 0, 0);
        gp.add(textSID, 1, 0);
        gp.add(labelAssign, 2, 0);
        gp.add(textAssign, 3, 0);
        gp.add(labelMidterm, 0, 1);
        gp.add(textMidterm, 1, 1);
        gp.add(labelFExam, 2, 1);
        gp.add(textFExam, 3, 1);
        gp.add(add, 1, 2);

        menu.getItems().addAll(newItem, openItem, saveItem, saveAsItem, exitItem);
        MenuBar mb = new MenuBar();
        mb.getMenus().add(menu);
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(mb, tableView, gp);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void save(File file) throws Exception {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for (StudentRecord sr : DataSource.marks) {
                String text = sr.getSID() + "," + sr.getAssignment() + "," + sr.getMidTerm()
                + "," + sr.getFinalExam() + "\n";

                writer.write(text);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

    private void load() throws Exception {
        try {
            Scanner scanner = new Scanner(currentFilename);
            while (scanner.hasNext()) {
                String data = scanner.next();
                String[] values = data.split(",");
                tableView.setItems(DataSource.addMarks(values[0], Float.parseFloat(values[1]),
                Float.parseFloat(values[2]), Float.parseFloat(values[3])));
            }
            scanner.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}