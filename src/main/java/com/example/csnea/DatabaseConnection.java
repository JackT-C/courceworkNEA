package com.example.csnea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "schema";
        String databaseUser = "Jack";
        String databasePassword = "Battlefront2";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }

    public static void changeScene (ActionEvent event, String fxmlFile, String title, Boolean fullscreen){
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(DatabaseConnection.class.getResource(fxmlFile));
            root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.setFullScreen(fullscreen);
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
