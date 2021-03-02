package csci2020u.lab05;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Main extends Application {
    
	@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 05 Solutions");

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
    
        VBox vbox = new VBox();
        vbox.getChildren().addAll(tableView);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}