package music.system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App: This Handles the initialization of javaFX and the properties of the windows created through-out the system. 
 */
public class App extends Application {

    // Static fields for storing scene and primary stage
    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {

        primaryStage = stage;

        // Loading the SignInPage FXML file and creating a scene
        scene = new Scene(loadFXML("SignInPage"), 600, 300);

         // Setting stage properties
        stage.setMaxHeight(400);
        stage.setMaxWidth(660);
        stage.setMinHeight(400);
        stage.setMinWidth(660);
        stage.setTitle("StoreInventorySystem");
        stage.setScene(scene);
        centerStageOnScreen(stage);
        stage.show();
    }

    // Method to set the root of the scene
    static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(root);
    
        if (root instanceof Pane) {
            Pane pane = (Pane) root;
            primaryStage.setWidth(pane.getPrefWidth());
            primaryStage.setHeight(pane.getPrefHeight());
            primaryStage.setMaxHeight(1080);
            primaryStage.setMaxWidth(1920);
            primaryStage.setMinHeight(400);
            primaryStage.setMinWidth(660);
        }
    }

    // Method to center the stage on the screen
    private void centerStageOnScreen(Stage stage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

    // Method to load FXML file
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch();
    }
}