package com.example.csnea;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreenController extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreenController.class.getResource("HomeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void switchtoBMI(MouseEvent e) throws IOException{
        Parent root1 = FXMLLoader.load(getClass().getResource("BMI.fxml"));
        Scene scene1 = new Scene(root1);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }
    public void switchtoCalorie(){

    }
    public void switchtoBook(){

    }
    public void switchtoExercise(){

    }

    public static void main(String[] args) {
        launch();
    }
}