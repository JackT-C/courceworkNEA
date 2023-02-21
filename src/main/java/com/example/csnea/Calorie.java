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

public class Calorie implements Initializable {
    //all fxml components used:
    @FXML
    private TextField nameoffoodtf;
    @FXML
    private TextField numberofcalstf;
    @FXML
    private TextField fatpercentagetf;
    @FXML
    private TextField proteingramstf;
    @FXML
    private Button AddFoodButton;
    @FXML
    private TableView<CalorieController> foodtable;
    @FXML
    private TableColumn<CalorieController, String> UsernameColumn;
    @FXML
    private TableColumn<CalorieController, String> Nameoffoodcolumn;
    @FXML
    private TableColumn<CalorieController, Float> Numberofcalscolumn;
    @FXML
    private TableColumn<CalorieController, Float> Fatpercentagecolumn;
    @FXML
    private TableColumn<CalorieController, Float> Proteinpercentagecolumn;

    //create observable list to hold database values so that they can be displayed to a tableview (getters and setters in CalorieController class)
    private final ObservableList<CalorieController> userfoods = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddFoodButton.setOnAction(event -> {
            if (!nameoffoodtf.getText().trim().isEmpty() && !numberofcalstf.getText().trim().isEmpty()) {
                //if fat% or protein grams are not known they are set to 0 so the rest can still be added to db
                if (fatpercentagetf.getText().trim().isEmpty()) {
                    fatpercentagetf.setText("0");
                }
                if (proteingramstf.getText().trim().isEmpty()){
                    proteingramstf.setText("0");
                }
                addfoodtodb(event, nameoffoodtf.getText().trim(), numberofcalstf.getText().trim(), fatpercentagetf.getText().trim(), proteingramstf.getText().trim());

            }
            //if the user hasn't filled in the name of the food and the number of calories it cannot be added
            else {
                System.out.println("Please fill in all required information");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in the name of food, and number of calories to add a food");
                alert.show();
            }


        });
    }
    //method for adding food to db
    public void addfoodtodb(ActionEvent event, String nameoffood, String numberofcals, String fatpercentage, String proteingrams) {
        Connection connection;
        PreparedStatement psInsert;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            //values are added to database table via sql statement
            psInsert = connection.prepareStatement("INSERT INTO userfood (Username,nameoffood,numberofcals,fatpercent,proteinpercent) VALUES (?,?,?,?,?)");
            psInsert.setString(1, logincontroller.currentuser);
            psInsert.setString(2, nameoffood);
            psInsert.setFloat(3, Float.parseFloat(numberofcals));
            psInsert.setFloat(4, Float.parseFloat(fatpercentage));
            psInsert.setFloat(5, Float.parseFloat(proteingrams));
            psInsert.executeUpdate();
            displayitemstotable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //method for displaying users db info to a fxml tableview
    public void displayitemstotable() {
        Connection connection;
        PreparedStatement psSelect;
        ResultSet resultSet;
        //sets up tableview columns
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        Nameoffoodcolumn.setCellValueFactory(new PropertyValueFactory<>("nameoffood"));
        Numberofcalscolumn.setCellValueFactory(new PropertyValueFactory<>("numberofcals"));
        Proteinpercentagecolumn.setCellValueFactory(new PropertyValueFactory<>("proteinpercent"));
        Fatpercentagecolumn.setCellValueFactory(new PropertyValueFactory<>("fatpercent"));
        try {
            //fetches the info from the db where the current user has entered values
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            psSelect = connection.prepareStatement("SELECT * FROM userfood WHERE Username = ?");
            psSelect.setString(1, logincontroller.currentuser);
            resultSet = psSelect.executeQuery();
            userfoods.clear();
            //loops through the database and adds values to observable list
            while (resultSet.next()) {
                userfoods.add(new CalorieController(resultSet.getString("Username"),
                        resultSet.getString("nameoffood"),
                        resultSet.getFloat("numberofcals"),
                        resultSet.getFloat("fatpercent"),
                        resultSet.getFloat("proteinpercent")));
                //sets the observable list to be displayed in the tableview
                foodtable.setItems(userfoods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //method for the user removing their items from db
    public void clearitemsfromtable() {
        userfoods.clear();
        foodtable.setItems(FXCollections.observableArrayList());
        Connection connection;
        PreparedStatement psDelete;
        try {
            //deletes all values from the current user
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            psDelete = connection.prepareStatement("DELETE FROM userfood WHERE Username = ?");
            psDelete.setString(1, logincontroller.currentuser);
            psDelete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returntomainmenu(ActionEvent event) {
        SwitchScenes.changeScene(event, "HomeScreen.fxml", "changetomainmenu", true);
    }

    public void returntotargets(ActionEvent event) {
        SwitchScenes.changeScene(event, "Targets.fxml", "changetotargets", true);
    }

}
