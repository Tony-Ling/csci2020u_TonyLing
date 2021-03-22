package csci2020u.lab08;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


public class Main extends Application {
    
	@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 08 Solutions");

        TableView<StudentRecord> tableView = new TableView<>();

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
            System.out.println("test");
        });

        openItem.setOnAction(e -> {
            System.out.println("test");
        });

        saveItem.setOnAction(e -> {
            System.out.println("test");
        });

        saveAsItem.setOnAction(e -> {
            System.out.println("test");
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

    public static void main(String[] args) {
        launch(args);
    }
}