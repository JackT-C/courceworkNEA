package com.example.csnea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BMI {

    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;

    @FXML
    Button HomeScreen;

    public void bmicalc(){
        if (textField2.getText().trim().isEmpty() || textField1.getText().trim().isEmpty()){
            label3.setText("please enter correct values for Height and Weight ");
        }
        else {
            float x = Float.parseFloat(textField1.getText());
            float y = Float.parseFloat(textField2.getText());
            float bmi = y/(x * x);
            label1.setText(""+bmi);

            if (bmi <= 18.5){
                label2.setText("Underweight");
            }
            else if (bmi <= 24.9){
                label2.setText("Normal weight");
            }
            else if (bmi <= 29.9){
                label2.setText("Overweight");
            }
            else {
                label2.setText("Obese");
            }
        }
    }
    public void reset(){
        label1.setText("");
        label2.setText("");
        label3.setText("");
    }
    public void switchtomainmenu(ActionEvent event){
        DatabaseConnection.changeScene(event, "HomeScreen.fxml", "switchtomain", true);
    }
}
