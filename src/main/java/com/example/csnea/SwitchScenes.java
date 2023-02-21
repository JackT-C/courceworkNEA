package com.example.csnea;
//imports used:

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import java.io.IOException;

public class SwitchScenes {

    // main method for switching scenes throughout the project
    public static void changeScene(ActionEvent event, String fxmlFile, String title, Boolean fullscreen) {
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(SwitchScenes.class.getResource(fxmlFile));
            root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setFullScreen(fullscreen);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
