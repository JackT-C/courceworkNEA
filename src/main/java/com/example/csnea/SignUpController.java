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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signInButton.setOnAction(event -> {

            //if the target text fields are empty then set to 0, so the other values can be added to database
            if (TargetWeightTF.getText().trim().isEmpty()) {
                TargetWeightTF.setText("0");
            }
            if (TargetAHTF.getText().trim().isEmpty()) {
                TargetAHTF.setText("0");
            }
            if (TargetAvCalIntakeTF.getText().trim().isEmpty()) {
                TargetAvCalIntakeTF.setText("0");
            }

            //only if the required fields are filled in can the values be added to the database
            if (!PasswordField.getText().trim().isEmpty() && !UserNameField.getText().trim().isEmpty() && !WeightTextField.getText().trim().isEmpty()
            && !HeightTextField.getText().trim().isEmpty() && !WeeklyActiveHoursTF.getText().trim().isEmpty() && AvCalIntakeTF.getText().trim().isEmpty()){


                signup.signUpUser(event, UserNameField.getText(), PasswordField.getText(), WeightTextField.getText(), HeightTextField.getText(),
                        WeeklyActiveHoursTF.getText(), AvCalIntakeTF.getText(), TargetWeightTF.getText(), TargetAHTF.getText(), TargetAvCalIntakeTF.getText());
                //switch to the login screen
                SwitchScenes.changeScene(event, "loginscreen.fxml", "backtologin", false);
            }
            //if the required fields are empty then show an error and get the user to enter details
            else {
                System.out.println("Please fill in required information (targets are still optional)");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in required information (targets are still optional)");
                alert.show();
            }
        });
    }
    //return to the loginscreen if user presses cancel button
    public void cancelbuttonOnAction(ActionEvent event){
        SwitchScenes.changeScene(event, "loginscreen.fxml", "backtologin", false);
    }
}
