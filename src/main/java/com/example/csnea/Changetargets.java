package com.example.csnea;

import javafx.event.ActionEvent;

public class Changetargets {

    public void cancelbuttonOnAction(ActionEvent event) {
        DatabaseConnection.changeScene(event, "Targets.fxml", "switchtotargets", true);
    }
}
