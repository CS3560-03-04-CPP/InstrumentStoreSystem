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
    public static String position;
    public Boolean canLogin;

    @FXML
    private Button button;
    @FXML
    private Text wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void userLogin(@SuppressWarnings("exports") ActionEvent event) throws IOException {
        canLogin=false;
        checkLogin();
    }

    // Method to validate user login credentials
    private void checkLogin() throws IOException {
        

        // SQL query to check if username and password match
        String query = "SELECT * FROM users WHERE username = ?";
    
        try (Connection connection = DatabaseManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set parameter for the query
            preparedStatement.setString(1, username.getText().toLowerCase());

            // Execute the query to check if the username exists
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if any rows were returned
            if (resultSet.next()) {
                // If a row was returned, the username exists
                // Now check if the password matches
                String storedPassword = resultSet.getString("password");
                String enteredPassword = password.getText().toLowerCase();
                
                if (storedPassword.equals(enteredPassword)) {
                    // If passwords match, login is successful
                    int userID = resultSet.getInt("employeeID");
                    userName = resultSet.getString("username");
                    setCurrentUser(userID); // Set the current user id
                    wrongLogin.setText("Success!");
                    canLogin = true;
                    
                } else {
                    // If passwords don't match, login failed
                    wrongLogin.setText("Wrong password!");
                }
            } else {
                // If no rows were returned, username doesn't exist
                wrongLogin.setText("Username does not exist!");
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
                position = resultSet2.getString("position");
                String employeeID = resultSet2.getString("users_employeeID");
    
                // Create a new Employee object or do something with the data
                @SuppressWarnings("unused")
                Employee employee = new Employee(Integer.parseInt(employeeID), name, position);
            }

            if (canLogin)
                App.setRoot("InventoryPage");

        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }

    }

    // Setters and getters
    public void setCurrentUser(int userID){currentUserID = userID;}

    public int getCurrentUser(){return currentUserID;}
}
