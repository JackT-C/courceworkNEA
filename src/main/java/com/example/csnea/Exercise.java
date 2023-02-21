package com.example.csnea;
//imports used:

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

import static com.example.csnea.ExerciseTypes.exerciseTypes;


public class Exercise implements Initializable {
    //all fxml components used:
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

    //create an observable list to store values from the database, getters and setters in exercise controller class
    private final ObservableList<ExerciseController> userexercises = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddExerciseButton.setOnAction(event -> {
            //adds and sorts the valid exercises in the arraylist
            exerciseTypes.add("walking");
            exerciseTypes.add("gym");
            exerciseTypes.add("running");
            exerciseTypes.add("athletics");
            exerciseTypes.add("football");
            exerciseTypes.add("swimming");
            exerciseTypes.add("cycling");
            exerciseTypes.add("tennis");
            exerciseTypes.add("golf");
            exerciseTypes.add("hockey");
            exerciseTypes.add("badminton");
            exerciseTypes.add("basketball");
            ExerciseTypes.mergeSort(exerciseTypes, 0, exerciseTypes.size() - 1);
            exerciseCheck(event);

            //if the user hasn't filled in calories since they may not know it is set to 0 so they can add other values to db
            if (caloriesburnttextfield.getText().trim().isEmpty()) {
                caloriesburnttextfield.setText("0");
            }

            //only if the required values are filled in then they will be added to the database
            if (!hoursexercisingtf.getText().trim().isEmpty() && !typeofexercisetf.getText().trim().isEmpty()) {


                addexercisetodb(event, typeofexercisetf.getText().trim(), caloriesburnttextfield.getText().trim(), hoursexercisingtf.getText().trim());

            }
            //if not then show an error so that they can enter needed values
            else {
                System.out.println("Please fill in all required information");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in exercise type and hours exercising to add an exercise");
                alert.show();
            }
        });
    }

    //switch to target scene
    public void switchtotargets(ActionEvent event) {
        SwitchScenes.changeScene(event, "Targets.fxml", "switchtotargets", true);
    }

    //switch to main menu
    public void switchtomainmenu(ActionEvent event) {
        SwitchScenes.changeScene(event, "HomeScreen.fxml", "switchtomainmenu", true);
    }

    //checks if the user has inputted a valid exercise and if not then switch to the scene which allows the user to input their own and displays valid exercises
    //also makes sure they are not empty so there are not 2 errors at the same time
    public void exerciseCheck(ActionEvent event) {
        if (!exerciseTypes.contains(typeofexercisetf.getText()) && !typeofexercisetf.getText().isEmpty() && !hoursexercisingtf.getText().isEmpty()) {
            SwitchScenes.changeScene(event, "exercisetype.fxml", "exercisetypeerror", false);
        }
    }

    //adds the values to the database
    public void addexercisetodb(ActionEvent event, String typeofexercise, String caloriesburnt, String hoursspent) {
        Connection connection;
        PreparedStatement psInsert;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            psInsert = connection.prepareStatement("INSERT INTO userexercise (Username,typeofexercise,caloriesburnt,hoursspent) VALUES (?,?,?,?)");
            //when inserting the username the current user will be checked and added to the db
            psInsert.setString(1, logincontroller.currentuser);
            psInsert.setString(2, typeofexercise);
            psInsert.setFloat(3, Float.parseFloat(caloriesburnt));
            psInsert.setFloat(4, Float.parseFloat(hoursspent));
            psInsert.executeUpdate();
            displaytableitems();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //displays the values from db to fxml tableview
    public void displaytableitems() {
        Connection connection;
        PreparedStatement psSelect;
        ResultSet resultSet;
        //set up the tableview columns
        Usernamecolumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        Typeofexercisecolumn.setCellValueFactory(new PropertyValueFactory<>("typeofexercise"));
        Caloriesburntcolumn.setCellValueFactory(new PropertyValueFactory<>("caloriesburnt"));
        Hoursspentcolumn.setCellValueFactory(new PropertyValueFactory<>("hoursspent"));
        try {
            //fetches the current user's info from database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            psSelect = connection.prepareStatement("SELECT * FROM userexercise WHERE Username = ?");
            psSelect.setString(1, logincontroller.currentuser);
            resultSet = psSelect.executeQuery();
            //clears observable list
            userexercises.clear();
            //loops through all database values and adds them to observable list
            while (resultSet.next()) {
                userexercises.add(new ExerciseController(resultSet.getString("Username")
                        , resultSet.getString("typeofexercise")
                        , resultSet.getFloat("caloriesburnt")
                        , resultSet.getFloat("hoursspent")));
                //outputs the observable list to the tableview
                exercisetable.setItems(userexercises);
            }
            //prints any errors that may occur from the sql
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //deletes all entries the current user has entered
    public void cleartableitems() {
        userexercises.clear();
        exercisetable.setItems(FXCollections.observableArrayList());
        Connection connection;
        PreparedStatement psDelete;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            psDelete = connection.prepareStatement("DELETE FROM userexercise WHERE Username = ?");
            psDelete.setString(1, logincontroller.currentuser);
            psDelete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}