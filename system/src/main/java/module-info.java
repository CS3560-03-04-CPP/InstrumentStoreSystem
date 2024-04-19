module music.system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;

    opens music.system to javafx.fxml;
    exports music.system;
}
