package com.example.csnea;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField UserNameField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private TextField WeightTextField;
    @FXML
    private TextField HeightTextField;
    @FXML
    private TextField WeeklyActiveHoursTF;
    @FXML
    private TextField AvCalIntakeTF;
    @FXML
    private TextField TargetWeightTF;
    @FXML
    private TextField TargetAHTF;
    @FXML
    private TextField TargetAvCalIntakeTF;
    @FXML
    private Button signInButton;
    @FXML
    private Button cancelbutton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!PasswordField.getText().trim().isEmpty() && !UserNameField.getText().trim().isEmpty() && !WeightTextField.getText().trim().isEmpty()
                && !HeightTextField.getText().trim().isEmpty() && !WeeklyActiveHoursTF.getText().trim().isEmpty() && !AvCalIntakeTF.getText().trim().isEmpty()
                && !TargetWeightTF.getText().trim().isEmpty() && !TargetAHTF.getText().trim().isEmpty() && !TargetAvCalIntakeTF.getText().trim().isEmpty()){

                    signup.signUpUser(event, UserNameField.getText(), PasswordField.getText(), WeightTextField.getText(), HeightTextField.getText(),
                            WeeklyActiveHoursTF.getText(), AvCalIntakeTF.getText(), TargetWeightTF.getText(), TargetAHTF.getText(), TargetAvCalIntakeTF.getText());
                    try {
                        switchtologin();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to sign up");
                    alert.show();
                }
            }
        });
    }

    public void cancelbuttonOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginscreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) cancelbutton.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    public void switchtologin() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("loginscreen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) cancelbutton.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
