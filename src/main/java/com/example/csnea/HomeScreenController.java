package com.example.csnea;


import javafx.event.ActionEvent;

public class HomeScreenController {

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


}