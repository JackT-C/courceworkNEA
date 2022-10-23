package com.example.csnea;

import javafx.event.ActionEvent;

public class TargetController {

    public void changetargets(ActionEvent event){
        DatabaseConnection.changeScene(event, "changetargets", "switchtotargetchange", false);
    }

    public void switchtomainmenu(ActionEvent event){
        DatabaseConnection.changeScene(event, "HomeScreen.fxml", "switchtomain", true);
    }

    public void switchtobooking(ActionEvent event){
        DatabaseConnection.changeScene(event, "Booking.fxml", "switchtobooking", true);
    }
}
