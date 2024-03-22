module music.system {
    requires javafx.controls;
    requires javafx.fxml;

    opens music.system to javafx.fxml;
    exports music.system;
}
