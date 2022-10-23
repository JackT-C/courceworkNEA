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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class logincontroller {
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

    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void signUpbuttonOnAction(ActionEvent event){
        DatabaseConnection.changeScene(event, "SignUp.fxml", "switchtosignup", false);
    }
    public void loginButtonOnAction(){
        if (!usernameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty()){
            logincheck();
        }
        else {
            loginMessageLabel.setText("Please enter username and password");
        }

    }
    public void logincheck(){
        DatabaseConnection connection = new DatabaseConnection();
        Connection connection1 = connection.getConnection();

        String loginCheck = "SELECT count(1) FROM useraccounts WHERE Username = '" + usernameTextField.getText() + "' AND Password = '" + passwordTextField.getText() + "'";

        try {
            Statement statement = connection1.createStatement();
            ResultSet queryResult = statement.executeQuery(loginCheck);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Details correct, Welcome");
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
        window.show();
        window.setFullScreen(true);
    }
}
