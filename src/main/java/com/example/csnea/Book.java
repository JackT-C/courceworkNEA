package com.example.csnea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Book {
    @FXML
    private Button addButton;
    @FXML
    TableView<BookingController> table;
    @FXML
    TableColumn<BookingController, String> nameColumn;
    @FXML
    TableColumn<BookingController, String> exerciseTypeColumn;
    @FXML
    TableColumn<BookingController, Date> dateColumn;
    @FXML
    TableColumn<BookingController, Time> timeColumn;
    @FXML
    TextField nameInput;
    @FXML
    TextField exerciseTypeInput;
    @FXML
    TextField dateInput;
    @FXML
    TextField timeInput;

    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/fitnessfirst";
    private final String user = "root";
    private final String password = "root";
    private final String insertBookingSQL = "INSERT INTO bookings (name, exercise_type, date, time) VALUES (?, ?, ?, ?)";
    private final String selectAllBookingSQL = "SELECT * FROM bookings";

    private final ObservableList<BookingController> bookings = FXCollections.observableArrayList();
    private final Set<BookingController> exerciseSet = new HashSet<BookingController>();

    public void initialize() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        exerciseTypeColumn.setCellValueFactory(cellData -> cellData.getValue().exerciseTypeProperty());
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        table.setItems(bookings);
    }

    @FXML
    private void addBooking(ActionEvent event) {
        String name = nameInput.getText();
        String exerciseType = exerciseTypeInput.getText();
        java.sql.Date date = java.sql.Date.valueOf(dateInput.getText());
        Time time = Time.valueOf(timeInput.getText());

        BookingController newExercise = new BookingController(name, exerciseType, date, time);
        if (exerciseSet.add(newExercise)) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertBookingSQL);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, exerciseType);
                preparedStatement.setDate(3, date);
                preparedStatement.setTime(4, time);
                preparedStatement.executeUpdate();
                bookings.add(newExercise);

                nameInput.clear();
                exerciseTypeInput.clear();
                dateInput.clear();
                timeInput.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Show an error message to the user that the booking already exists
            System.out.println("Error: Booking already exists with these details!");
        }
    }
    public void exitbuttononclick(ActionEvent event){
        DatabaseConnection.changeScene(event, "Targets.fxml", "switchtotargets", true);
    }
}

