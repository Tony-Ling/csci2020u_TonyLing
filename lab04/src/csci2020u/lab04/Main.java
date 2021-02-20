/**
 * Tony Ling
 * Stu #: 100747421
 * CSCI 2020U - Lab 4
 * 2/20/2021
 */
package csci2020u.lab04;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lab 04 Solution");
        
        Label userName = new Label("Username:");
        Label passWord = new Label("Password:");
        Label fullName = new Label("Full Name:");
        Label eMail = new Label("E-Mail");
        Label phoneNumber = new Label("Phone #:");
        Label dateOfBirth = new Label("Date of Birth:");

        TextField textUserName = new TextField();
        TextField textFullName = new TextField();
        TextField textEMail = new TextField();
        TextField textPhoneNumber = new TextField();

        PasswordField passwordField = new PasswordField();

        DatePicker datePicker = new DatePicker();

        Button button = new Button("Register");
        button.setOnAction(action -> {
            System.out.println(textUserName.getText() + "\n" + passwordField.getText()
            + "\n" + textFullName.getText() + "\n" + textEMail.getText() + "\n"
            + textPhoneNumber.getText() + "\n" + datePicker.getValue());
        });

        GridPane grid = new GridPane();

        grid.add(userName, 0, 0);
        grid.add(textUserName, 1, 0);
        textUserName.setPromptText("Username");
        grid.add(passWord, 0, 1);
        grid.add(passwordField, 1, 1);
        passwordField.setPromptText("Password");
        grid.add(fullName, 0, 2);
        grid.add(textFullName, 1, 2);
        textFullName.setPromptText("Full Name");
        grid.add(eMail, 0, 3);
        grid.add(textEMail, 1, 3);
        textEMail.setPromptText("Email");
        grid.add(phoneNumber, 0, 4);
        grid.add(textPhoneNumber, 1, 4);
        textPhoneNumber.setPromptText("Phone Number");
        grid.add(dateOfBirth, 0, 5);
        grid.add(datePicker, 1, 5);
        grid.add(button, 1, 6);

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}