package com.example.csnea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Exercise implements Initializable {
    @FXML
    private TextField typeofexercisetf;
    @FXML
    private TextField caloriesburnttextfield;
    @FXML
    private TextField hoursexercisingtf;
    @FXML
    private Button AddExerciseButton;
    @FXML
    private TableView<ExerciseController> exercisetable;
    @FXML
    private TableColumn<ExerciseController, String> Usernamecolumn;
    @FXML
    private TableColumn<ExerciseController, String> Typeofexercisecolumn;
    @FXML
    private TableColumn<ExerciseController, Float> Caloriesburntcolumn;
    @FXML
    private TableColumn<ExerciseController, Float> Hoursspentcolumn;

    private ObservableList<ExerciseController> userexercises = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddExerciseButton.setOnAction(event -> {
            if (!hoursexercisingtf.getText().trim().isEmpty() && !caloriesburnttextfield.getText().trim().isEmpty() && !typeofexercisetf.getText().trim().isEmpty()){


                addexercisetodb(event, typeofexercisetf.getText().trim(), caloriesburnttextfield.getText().trim(), hoursexercisingtf.getText().trim());

            }
            else {
                System.out.println("Please fill in all required information");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in calories burnt, exercise type and hours exercising to add an exercise");
                alert.show();
            }
        });}

    public void switchtotargets(ActionEvent event){
        DatabaseConnection.changeScene(event, "Targets.fxml", "switchtotargets", true);
    }
    public void switchtomainmenu(ActionEvent event){
        DatabaseConnection.changeScene(event, "HomeScreen.fxml", "switchtomainmenu", true);
    }


    public void addexercisetodb(ActionEvent event, String typeofexercise, String caloriesburnt, String hoursspent){
        Connection connection;
        PreparedStatement psInsert;
        try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            psInsert = connection.prepareStatement("INSERT INTO userexercise (Username,typeofexercise,caloriesburnt,hoursspent) VALUES (?,?,?,?)");
            psInsert.setString(1, logincontroller.currentuser);
            psInsert.setString(2, typeofexercise);
            psInsert.setFloat(3, Float.parseFloat(caloriesburnt));
            psInsert.setFloat(4, Float.parseFloat(hoursspent));
            psInsert.executeUpdate();
            displaytableitems();

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void displaytableitems(){
        Connection connection;
        PreparedStatement psSelect;
        ResultSet resultSet;
        Usernamecolumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        Typeofexercisecolumn.setCellValueFactory(new PropertyValueFactory<>("typeofexercise"));
        Caloriesburntcolumn.setCellValueFactory(new PropertyValueFactory<>("caloriesburnt"));
        Hoursspentcolumn.setCellValueFactory(new PropertyValueFactory<>("hoursspent"));
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst","root", "root");
            psSelect = connection.prepareStatement("SELECT * FROM userexercise");
            resultSet = psSelect.executeQuery();
            userexercises.clear();
            while (resultSet.next()){
                userexercises.add(new ExerciseController(resultSet.getString("Username")
                        , resultSet.getString("typeofexercise")
                        , resultSet.getFloat("caloriesburnt")
                        , resultSet.getFloat("hoursspent")));
                exercisetable.setItems(userexercises);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void cleartableitems(){
        userexercises.clear();
        exercisetable.setItems(FXCollections.observableArrayList());
        Connection connection;
        PreparedStatement psDelete;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            psDelete = connection.prepareStatement("DELETE FROM userexercise");
            psDelete.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


}