package com.example.csnea;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class HomeScreenController {
    @FXML

    Button food, BMI, Targets, exercise;


    public void switchtoBMI() throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getResource("BMI.fxml"));
        Scene scene1 = new Scene(root1);
        Stage window = (Stage) BMI.getScene().getWindow();
        window.setScene(scene1);
        window.show();
        window.setFullScreen(true);
    }
    public void switchtoCalorie() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Calorie.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) food.getScene().getWindow();
        window.setScene(scene);
        window.show();
        window.setFullScreen(true);
    }
    public void switchtoBook() throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("Book.fxml"));
        Scene scene2 = new Scene(root2);
        Stage window = (Stage) Targets.getScene().getWindow();
        window.setScene(scene2);
        window.show();
        window.setFullScreen(true);
    }
    public void switchtoExercise() throws Exception{
        Parent root3 = FXMLLoader.load(getClass().getResource("Exercise.fxml"));
        Scene scene3 = new Scene(root3);
        Stage window = (Stage) exercise.getScene().getWindow();
        window.setScene(scene3);
        window.show();
        window.setFullScreen(true);
    }


}