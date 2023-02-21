package com.example.csnea;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeScreenController {
    @FXML
    private Button exitbutton;

    //switch to BMI scene
    public void switchtoBMI(ActionEvent event){
        SwitchScenes.changeScene(event, "BMI.fxml", "switchtobmi", true);
    }
    //switch to calorie scene
    public void switchtoCalorie(ActionEvent event){
        SwitchScenes.changeScene(event, "Calorie.fxml", "switchtocalorie", true);
    }
    //switch to targets scene
    public void switchtoTargets(ActionEvent event){
        SwitchScenes.changeScene(event, "Targets.fxml", "switchtotargets", true);
    }
    //switch to exercise scene
    public void switchtoExercise(ActionEvent event){
        SwitchScenes.changeScene(event, "Exercise.fxml", "switchtoexercise", true);
    }
    //exit the application
    public void exit(){
        Stage stage = (Stage) exitbutton.getScene().getWindow();
        stage.close();
    }


}