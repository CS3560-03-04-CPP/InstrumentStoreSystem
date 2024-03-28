package music.system;

import java.io.IOException;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * SignInPage: This class controls the behavior of the sign-in page of the application.
 */
public class SignInPage {

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
   
    // Temporary solution until we implement the database
    if(username.getText().toString().toLowerCase().equals("manager") && password.getText().toString().toLowerCase().equals("manager")
    || username.getText().toString().toLowerCase().equals("employee") && password.getText().toString().toLowerCase().equals("employee")) {
        wrongLogin.setText("Success!");       
        
        
        App.setRoot("InventoryPage");
    }

    else if(username.getText().isEmpty() && password.getText().isEmpty()) {
        wrongLogin.setText("Please enter your data.");
    }


    else {
        wrongLogin.setText("Wrong username or password!");
    }
}


}
