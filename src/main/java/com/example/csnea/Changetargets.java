package com.example.csnea;
//imports used:

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Changetargets implements Initializable {
    //fxml components used:
    @FXML
    private TextField TargetWeightTF;
    @FXML
    private TextField TargetAHTF;
    @FXML
    private TextField TargetAvCalIntakeTF;
    @FXML
    private Button submitchangesbutton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        submitchangesbutton.setOnAction(event -> {
            //if the user hasn't entered a value then it is individually set to 0
            if (TargetWeightTF.getText().trim().isEmpty()) {
                TargetWeightTF.setText("0");
            }
            if (TargetAHTF.getText().trim().isEmpty()) {
                TargetWeightTF.setText("0");
            }
            if (TargetAvCalIntakeTF.getText().trim().isEmpty()) {
                TargetAvCalIntakeTF.setText("0");
            }
            float targetweight = Float.parseFloat(TargetWeightTF.getText().trim());
            float targetactivehours = Float.parseFloat(TargetAHTF.getText().trim());
            float targetavcalintake = Float.parseFloat(TargetAvCalIntakeTF.getText().trim());


            updatetargetstodb(event, targetweight, targetactivehours, targetavcalintake);

        });
    }

    //uploads the updated targets to the database
    public void updatetargetstodb(ActionEvent event, float targetweight, float targetactivehours, float targetavcalintake) {
        Connection connection;
        PreparedStatement psUpdate;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            //updates the database and changes the values related to the current user
            psUpdate = connection.prepareStatement(
                    "UPDATE usertargets " +
                            "SET targetweight = '" + targetweight +
                            "', targetactivehours = '" + targetactivehours +
                            "', targetavcalintake = '" + targetavcalintake +
                            "' WHERE Username = '" + logincontroller.currentuser + "'");
            psUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //returns to main menu
    public void cancelbuttonOnAction(ActionEvent event) {
        SwitchScenes.changeScene(event, "Targets.fxml", "switchtotargets", true);
    }
}
