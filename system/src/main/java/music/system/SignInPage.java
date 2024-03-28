package music.system;

import java.io.IOException;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

private void checkLogin() throws IOException {
   
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
