package com.example.csnea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class signup {
    @FXML
    Button cancelbutton;
    @FXML
    Button signInButton;


    public static void signUpUser(ActionEvent event, String Username, String Password, String weight, String height, String activeHours, String avCalIntake,
                                  String targetWeight, String targetactiveHours, String targetacCalIntake){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckIfUserExists = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schema", "root", "Battlefront2");
            psCheckIfUserExists = connection.prepareStatement("SELECT * FROM useraccounts WHERE Username = ?");
            psCheckIfUserExists.setString(1, Username);
            resultSet = psCheckIfUserExists.executeQuery();

            if (resultSet.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("user already exists");
                alert.show();
            }
            else {
                psInsert = connection.prepareStatement("INSERT INTO useraccounts (Username,Password,weight,height,activeHours,avCalIntake) VALUES (?, ?, ?, ?, ?, ?)");
                psInsert.setString(1, Username);
                psInsert.setString(2, Password);
                psInsert.setFloat(3, Float.parseFloat(weight));
                psInsert.setFloat(4, Float.parseFloat(height));
                psInsert.setFloat(5, Float.parseFloat(activeHours));
                psInsert.setFloat(6, Float.parseFloat(avCalIntake));
                psInsert.executeUpdate();

                psInsert = connection.prepareStatement("INSERT INTO usertargets (targetWeight, targetactiveHours, targetacCalIntake) VALUES (?, ?, ?)");
                psInsert.setFloat(1, Float.parseFloat(targetWeight));
                psInsert.setFloat(2, Float.parseFloat(targetactiveHours));
                psInsert.setFloat(3, Float.parseFloat(targetacCalIntake));
                psInsert.executeUpdate();

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psCheckIfUserExists != null){
                try {
                    psCheckIfUserExists.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psInsert != null){
                try {
                    psInsert.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
