package com.example.csnea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Exercise {
    @FXML
    private ChoiceBox exercisedropdown;
    @FXML
    private TextField caloriesburnttextfield;
    @FXML
    private TextField hoursexercisingtf;
    @FXML
    private Button AddExerciseButton;

    public void switchtotargets(ActionEvent event){
        DatabaseConnection.changeScene(event, "Targets.fxml", "switchtotargets", true);
    }
    public void switchtomainmenu(ActionEvent event){
        DatabaseConnection.changeScene(event, "HomeScreen.fxml", "switchtomainmenu", true);
    }
    public void additemstochoicebox() {
        exercisedropdown.getItems().addAll("Running", "Cycling", "Swimming", "Gym push", "Gym pull", "Gym legs", "Football",
                "Cricket", "Hockey", "Tennis", "Volleyball", "Table Tennis", "Basketball", "Baseball", "Golf", "Rugby",
                "Badminton", "Boxing/Fighting", "Other");
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddExerciseButton.setOnAction(event -> {
            System.out.println(exercisedropdown.getValue().toString());
            if (!hoursexercisingtf.getText().trim().isEmpty() && !caloriesburnttextfield.getText().trim().isEmpty() && !exercisedropdown.getValue().toString().trim().isEmpty()){


                addexercisetodb(event, exercisedropdown.getValue().toString(), caloriesburnttextfield.getText().trim(), hoursexercisingtf.getText().trim());

            }
            else {
                System.out.println("Please fill in all required information");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in calories burnt, exercise type and hours exercising to add an exercise");
                alert.show();
            }
        });}


    public void addexercisetodb(ActionEvent event, String typeofexercise, String caloriesburnt, String hoursspent){
        Connection connection = null;
        PreparedStatement psInsert = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            psInsert = connection.prepareStatement("INSERT INTO userexercise (typeofexercise,caloriesburnt,hoursspent) VALUES (?,?,?)");
            psInsert.setString(1, typeofexercise);
            psInsert.setFloat(2, Float.parseFloat(caloriesburnt));
            psInsert.setFloat(3, Float.parseFloat(hoursspent));

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


}