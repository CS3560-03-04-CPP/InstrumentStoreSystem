package music.system;

import java.io.IOException;

import javafx.fxml.FXML;

public class InventoryPage {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}