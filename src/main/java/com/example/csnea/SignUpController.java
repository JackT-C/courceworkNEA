package com.example.csnea;

import com.example.csnea.DatabaseConnection;
import com.example.csnea.signup;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signInButton.setOnAction(event -> {
            if (WeightTextField.getText().trim().isEmpty()) {
                WeightTextField.setText("0");
            }
            if (HeightTextField.getText().trim().isEmpty()) {
                HeightTextField.setText("0");
            }
            if (WeeklyActiveHoursTF.getText().trim().isEmpty()) {
                WeeklyActiveHoursTF.setText("0");
            }
            if (AvCalIntakeTF.getText().trim().isEmpty()) {
                AvCalIntakeTF.setText("0");
            }
            if (TargetWeightTF.getText().trim().isEmpty()) {
                TargetWeightTF.setText("0");
            }
            if (TargetAHTF.getText().trim().isEmpty()) {
                TargetAHTF.setText("0");
            }
            if (TargetAvCalIntakeTF.getText().trim().isEmpty()) {
                TargetAvCalIntakeTF.setText("0");
            }


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
