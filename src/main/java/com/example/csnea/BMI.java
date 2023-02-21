package com.example.csnea;
//imports:

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BMI {
    //all fxml components used:
    @FXML
    private TextField HeightTextField;
    @FXML
    private TextField WeightTextField;
    @FXML
    private Label BMI_label;
    @FXML
    private Label category_label;
    @FXML
    private Label error_label;
    @FXML
    Button HomeScreen;


    public void bmicalc(){
        //outputs error message to label and alert if empty values
        if (WeightTextField.getText().trim().isEmpty() || HeightTextField.getText().trim().isEmpty()){
            error_label.setText("please enter correct values for Height and Weight ");
        }
        else {
            //calculates BMI
            float x = Float.parseFloat(HeightTextField.getText());
            float y = Float.parseFloat(WeightTextField.getText());
            float bmi = y/(x * x);
            BMI_label.setText(""+bmi);
            //outputs a category
            if (bmi <= 18.5){
                category_label.setText("Underweight");
            }
            else if (bmi <= 24.9){
                category_label.setText("Normal weight");
            }
            else if (bmi <= 29.9){
                category_label.setText("Overweight");
            }
            else {
                category_label.setText("Obese");
            }
        }
    }
    //resets all textfields
    public void reset(){
        BMI_label.setText("");
        category_label.setText("");
        error_label.setText("");
    }
    public void switchtomainmenu(ActionEvent event){
        SwitchScenes.changeScene(event, "HomeScreen.fxml", "switchtomain", true);
    }
}
