package com.example.csnea;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import java.sql.*;

public class signup {

    //main method for signing the user up and adding values to the database
    public static void signUpUser(ActionEvent event, String Username, String Password, String Weight, String Height, String ActiveHours, String AverageCalIntake,
                                  String targetweight, String targetactivehours, String targetavcalintake){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckIfUserExists = null;
        ResultSet resultSet = null;

        try {
            //establish a connection to the mysql database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fitnessfirst", "root", "root");
            //checks if there is already a user with the same username
            psCheckIfUserExists = connection.prepareStatement("SELECT * FROM useraccounts WHERE Username = ?");
            psCheckIfUserExists.setString(1, Username);
            resultSet = psCheckIfUserExists.executeQuery();

            //if they exist show an alert
            if (resultSet.isBeforeFirst()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("user already exists");
                alert.show();
            }
            //otherwise add values to database:
            else {
                psInsert = connection.prepareStatement("INSERT INTO useraccounts (Username,Password,Weight,Height,ActiveHours,AverageCalIntake) VALUES (?, ?, ?, ?, ?, ?)");
                psInsert.setString(1, Username);
                psInsert.setString(2, Password);
                psInsert.setFloat(3, Float.parseFloat(Weight));
                psInsert.setFloat(4, Float.parseFloat(Height));
                psInsert.setFloat(5, Float.parseFloat(ActiveHours));
                psInsert.setFloat(6, Float.parseFloat(AverageCalIntake));
                psInsert.executeUpdate();

                psInsert = connection.prepareStatement("INSERT INTO usertargets (Username,targetweight,targetactivehours,targetavcalintake) VALUES (?, ?, ?, ?)");
                psInsert.setString(1, Username);
                psInsert.setFloat(2, Float.parseFloat(targetweight));
                psInsert.setFloat(3, Float.parseFloat(targetactivehours));
                psInsert.setFloat(4, Float.parseFloat(targetavcalintake));
                psInsert.executeUpdate();

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
