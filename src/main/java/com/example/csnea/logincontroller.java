package com.example.csnea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class logincontroller {
    public static String currentuser;
    @FXML
    Button cancelButton;
    @FXML
    Button loginButton;
    @FXML
    Button signUpbutton;
    @FXML
    Label loginMessageLabel;
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;

    //closes the application if the user cancels login
    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    //switch to the signup scene on button press
    public void signUpbuttonOnAction(ActionEvent event){
        SwitchScenes.changeScene(event, "SignUp.fxml", "switchtosignup", false);
    }
    //checks if the fields for logging in are empty or not
    public void loginButtonOnAction(){
        if (!usernameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty()){
            logincheck();
        }
        else {
            loginMessageLabel.setText("Please enter username and password");
        }

    }
    public void logincheck(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            Statement statement = connection.createStatement();
            //checks the database for the username and password
            ResultSet queryResult = statement.executeQuery("SELECT count(1) FROM useraccounts WHERE Username = '" + usernameTextField.getText() + "' AND Password = '" + passwordTextField.getText() + "'");

            while (queryResult.next()){
                //if the username and password match then set the current user to the one logged in and switch to main menu
                if (queryResult.getInt(1) == 1){
                    currentuser = usernameTextField.getText();
                    switchtomainmenu();
                }
                else {
                    loginMessageLabel.setText("Details incorrect, Try again");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void switchtomainmenu() throws Exception {
        Parent root1 = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        Scene scene1 = new Scene(root1);
        Stage window = (Stage) loginButton.getScene().getWindow();
        window.setScene(scene1);
        window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        window.setFullScreen(true);
        window.show();
    }
}
