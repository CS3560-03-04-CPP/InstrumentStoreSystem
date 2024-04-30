package music.system;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import music.system.SystemClasses.Employee;


/**
 * Application Class, SignInPage: This class controls the behavior of the sign-in page of the application.
 * 
 * The First user GUI interaface you see which has the ability to continue if given a proper employee or manager login/password stored in the database.
 * 
 */
public class SignInPage {

    public static int currentUserID;
    public static String userName;

    @FXML
    private Button button;
    @FXML
    private Text wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void userLogin(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        checkLogin();
    }

    // Method to validate user login credentials
    private void checkLogin() throws IOException {
        

        // SQL query to check if username and password match
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set parameters for the query
            preparedStatement.setString(1, username.getText().toLowerCase());
            preparedStatement.setString(2, password.getText().toLowerCase());

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if any rows were returned
            if (resultSet.next()) {
                // If a row was returned, login is successful
                int userID = resultSet.getInt("employeeID");
                userName = resultSet.getString("username");

                setCurrentUser(userID); // Set the current user id
                wrongLogin.setText("Success!");
            } else {
                // If no rows were returned, login failed
                wrongLogin.setText("Wrong username or password!");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }

        String query2 =  "SELECT * FROM employee WHERE users_employeeID = ?";

        try (Connection connection2 = DatabaseManager.getConnection();
            PreparedStatement preparedStatement2 = connection2.prepareStatement(query2)) {

            preparedStatement2.setInt(1, getCurrentUser());

            ResultSet resultSet2 = preparedStatement2.executeQuery();

            if (resultSet2.next()) {
                // Fetch data from the current row
                String name = resultSet2.getString("name");
                String position = resultSet2.getString("position");
                String employeeID = resultSet2.getString("users_employeeID");
    
                // Create a new Employee object or do something with the data
                @SuppressWarnings("unused")
                Employee employee = new Employee(Integer.parseInt(employeeID), name, position);
                App.setRoot("InventoryPage");
            }

        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }
    }

    // Setters and getters
    public void setCurrentUser(int userID){currentUserID = userID;}

    public int getCurrentUser(){return currentUserID;}
}
