package com.example.csnea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signInButton.setOnAction(event -> {

            if (!PasswordField.getText().trim().isEmpty() && !UserNameField.getText().trim().isEmpty()){


                signup.signUpUser(event, UserNameField.getText(), PasswordField.getText(), WeightTextField.getText(), HeightTextField.getText(),
                        WeeklyActiveHoursTF.getText(), AvCalIntakeTF.getText(), TargetWeightTF.getText(), TargetAHTF.getText(), TargetAvCalIntakeTF.getText());

                DatabaseConnection.changeScene(event, "loginscreen.fxml", "backtologin", false);
            }
            else {
                System.out.println("Please fill in username and password information");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in username and password information to sign up");
                alert.show();
            }
        });
    }

    public void cancelbuttonOnAction(ActionEvent event){
        DatabaseConnection.changeScene(event, "loginscreen.fxml", "backtologin", false);
    }
}
