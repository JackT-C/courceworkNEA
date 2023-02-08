package com.example.csnea;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeScreenController {
    @FXML
    private Button exitbutton;

    public void switchtoBMI(ActionEvent event){
        DatabaseConnection.changeScene(event, "BMI.fxml", "switchtobmi", true);
    }
    public void switchtoCalorie(ActionEvent event){
        DatabaseConnection.changeScene(event, "Calorie.fxml", "switchtocalorie", true);
    }
    public void switchtoTargets(ActionEvent event){
        DatabaseConnection.changeScene(event, "Targets.fxml", "switchtotargets", true);
    }
    public void switchtoExercise(ActionEvent event){
        DatabaseConnection.changeScene(event, "Exercise.fxml", "switchtoexercise", true);
    }
    public void exit(){
        Stage stage = (Stage) exitbutton.getScene().getWindow();
        stage.close();
    }


}