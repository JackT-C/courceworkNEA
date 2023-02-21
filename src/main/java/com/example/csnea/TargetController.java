package com.example.csnea;

//imports:
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TargetController implements Initializable {
    //all fxml components used:
    @FXML
    private Label currentweight;
    @FXML
    private Label targetweight;
    @FXML
    private Label progresspercentweight;

    @FXML
    private Label currentcalorieintake;
    @FXML
    private Label targetcalorieintake;
    @FXML
    private Label calorieintakepercentprogress;

    @FXML
    private Label currentAOW;
    @FXML
    private Label targetAOW;
    @FXML
    private Label progresspercentAOW;

    @FXML
    private Label TotalProgressPercent;

    @FXML
    private Button RefreshTargets;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        RefreshTargets.setOnAction(event -> {
            try {
                // Connect to the database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");

                // fetch the current users targets
                PreparedStatement psSelect = connection.prepareStatement("SELECT * FROM usertargets WHERE Username = ?");
                psSelect.setString(1, logincontroller.currentuser);
                ResultSet resultSet = psSelect.executeQuery();

                // Populate the labels with the retrieved data from usertargets
                while (resultSet.next()) {
                    targetweight.setText(resultSet.getString("targetweight"));
                    targetcalorieintake.setText(resultSet.getString("targetavcalintake"));
                    targetAOW.setText(resultSet.getString("targetactivehours"));
                }
                resultSet.close();
                psSelect.close();

                //fetch the current info about the user
                psSelect = connection.prepareStatement("SELECT * FROM useraccounts WHERE Username = ?");
                psSelect.setString(1, logincontroller.currentuser);
                resultSet = psSelect.executeQuery();

                //Populate the labels with the retrieved data from useraccounts
                while (resultSet.next()) {
                    currentweight.setText(resultSet.getString("Weight"));
                    currentcalorieintake.setText(resultSet.getString("AverageCalIntake"));
                    currentAOW.setText(resultSet.getString("ActiveHours"));
                }
                resultSet.close();
                psSelect.close();
                connection.close();

                percentagelabels();
            //prints any errors that may occur
            } catch (SQLException e) {
                e.printStackTrace();
            }
        })
        ;
    }
    //sets the % progress labels and calculates an average
    public void percentagelabels() {
        float weightperectage = Float.parseFloat(currentweight.getText()) / Float.parseFloat(targetweight.getText());
        progresspercentweight.setText(String.format(String.valueOf(weightperectage)));
        float calorieintakepercentage = Float.parseFloat(currentcalorieintake.getText()) / Float.parseFloat(targetcalorieintake.getText());
        calorieintakepercentprogress.setText(String.format(String.valueOf(calorieintakepercentage)));
        float activehourspercentage = Float.parseFloat(currentAOW.getText()) / Float.parseFloat(targetAOW.getText());
        progresspercentAOW.setText(String.format(String.valueOf(activehourspercentage)));
        float averageprogresspercent = (weightperectage + calorieintakepercentage + activehourspercentage)/ 3 ;
        TotalProgressPercent.setText(String.format(String.valueOf(averageprogresspercent)));
    }


    //switch to the target changing scene
    public void changetargets(ActionEvent event) {
        SwitchScenes.changeScene(event, "changetargets.fxml", "switchtotargetchange", false);
    }

    //switch to the main menu scene
    public void switchtomainmenu(ActionEvent event) {
        SwitchScenes.changeScene(event, "HomeScreen.fxml", "switchtomain", true);
    }

    //switch to the booking scene
    public void switchtobooking(ActionEvent event) {
        SwitchScenes.changeScene(event, "Booking.fxml", "switchtobooking", false);
    }
}
