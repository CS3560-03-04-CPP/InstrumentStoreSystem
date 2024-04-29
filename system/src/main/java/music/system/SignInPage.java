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


/**
 * Application Class, SignInPage: This class controls the behavior of the sign-in page of the application.
 * 
 * The First user GUI interaface you see which has the ability to continue if given a proper employee or manager login/password stored in the database.
 * 
 */
public class SignInPage {

    public static int currentUserID;

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

    public void setCurrentUser(int userID){
        currentUserID = userID;
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
                setCurrentUser(userID); // Set the current user id
                wrongLogin.setText("Success!");
                App.setRoot("InventoryPage");
            } else {
                // If no rows were returned, login failed
                wrongLogin.setText("Wrong username or password!");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
        }
    }
}
