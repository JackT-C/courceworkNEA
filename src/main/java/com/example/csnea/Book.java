package com.example.csnea;
//imports:

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book implements Initializable {
    //all fxml components used:

    @FXML
    private TableView<BookingController> bookingtable;
    @FXML
    private TableColumn<BookingController, String> usernameColumn;
    @FXML
    private TableColumn<BookingController, String> BookingTypeColumn;
    @FXML
    private TableColumn<BookingController, Date> dateColumn;
    @FXML
    private TableColumn<BookingController, Time> timeColumn;

    @FXML
    private Button addBookingButton;
    @FXML
    private TextField timeInput;
    @FXML
    private TextField dateInput;
    @FXML
    private TextField exerciseTypeInput;

    //create observable list to hold database values so that they can be displayed to a tableview (getters and setters in BookingController class)
    private final ObservableList<BookingController> userbookings = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {

        addBookingButton.setOnAction(event -> {
            //only if all values are filled in will they be added to db
            if (!timeInput.getText().trim().isEmpty() && !dateInput.getText().trim().isEmpty() && !exerciseTypeInput.getText().trim().isEmpty()) {
                //checks if date and time formats are correct
                String dateinput = dateInput.getText().trim();
                String timeinput = timeInput.getText().trim();
                //if not then display an error and show correct format
                if (!isDateFormat(dateinput) || !isTimeFormat(timeinput)) {
                    System.out.println("time/date are in the wrong format");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("time/date are in the wrong format, please use YYYY-MM-DD and HH:MM:SS");
                    alert.show();
                }
                else {
                    addBookingtodb(event, exerciseTypeInput.getText().trim(), dateInput.getText().trim(), timeInput.getText().trim());
                }
            } else {
                System.out.println("Please fill in all required information");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill in the time, date, and booking type values");
                alert.show();
            }
        });
    }

    //uses regular expression to check the date format is correct (matches mysql server)
    //regular expression string is defined as ^\\d{4}-\\d{2}-\\d{2}$, where ^ and $ represent the start and end of the string
    //this matches any string that that has 4 digits, a hyphen, 2 more digits, another hyphen, and then 2 more digits (in date format year, month, day)
    private boolean isDateFormat(String input) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    //uses regular expression to check the time format is correct (matches mysql server)
    //regular expression string is defined as ^\\d{2}:\\d{2}:\\d{2}$, where ^ and $ represent the start and end of the string
    //this matches any string that has 2 digits, a colon, 2 more digits, another colon, then another 2 digits (in time format hours minutes seconds)
    private boolean isTimeFormat(String input) {
        String regex = "^\\d{2}:\\d{2}:\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }


    //adds values to database
    public void addBookingtodb(ActionEvent event, String booking_type, String booking_date, String booking_time) {
        Connection connection;
        PreparedStatement psInsert;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            psInsert = connection.prepareStatement("INSERT INTO userbookings (Username, booking_type, booking_date, booking_time) VALUES (?,?,?,?)");
            //adds the bookings to the current user logged in
            psInsert.setString(1, logincontroller.currentuser);
            psInsert.setString(2, booking_type);
            psInsert.setDate(3, Date.valueOf((booking_date)));
            psInsert.setTime(4, Time.valueOf((booking_time)));
            psInsert.executeUpdate();
            showbookings();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //method for outputting the values to a fxml tableview
    public void showbookings() {
        Connection connection;
        PreparedStatement psSelect;
        ResultSet resultSet;
        //tableview columns are set up
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("booking_date"));
        BookingTypeColumn.setCellValueFactory(new PropertyValueFactory<>("booking_type"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("booking_time"));
        try {
            //current users bookings are fetched from the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            psSelect = connection.prepareStatement("SELECT * FROM userbookings WHERE Username = ?");
            psSelect.setString(1, logincontroller.currentuser);
            resultSet = psSelect.executeQuery();
            //observable list is cleared
            userbookings.clear();
            //loops through the db and adds values to observable list
            while (resultSet.next()) {
                userbookings.add(new BookingController(resultSet.getString("Username"),
                        resultSet.getString("booking_type"),
                        resultSet.getDate("booking_date"),
                        resultSet.getTime("booking_time")));
                //sets the tableview to display the observable list
                bookingtable.setItems(userbookings);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //method for deleting all the current users bookings
    public void cleartable() {
        userbookings.clear();
        bookingtable.setItems(FXCollections.observableArrayList());
        Connection connection;
        PreparedStatement psDelete;
        try {
            //clears the users bookings
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            psDelete = connection.prepareStatement("DELETE FROM userbookings WHERE Username = ?");
            psDelete.setString(1, logincontroller.currentuser);
            psDelete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //switch back to targets
    public void exitbuttononclick(ActionEvent event) {
        SwitchScenes.changeScene(event, "Targets.fxml", "returntotargets", true);
    }
}

