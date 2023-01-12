package com.example.csnea;

import javafx.event.ActionEvent;

public class Calorie {

    public void returntomainmenu(ActionEvent event){
        DatabaseConnection.changeScene(event, "HomeScreen.fxml", "changetomainmenu", true);
    }
    public void returntotargets(ActionEvent event){
        DatabaseConnection.changeScene(event, "Targets.fxml", "changetotargets", true);
    }
}
