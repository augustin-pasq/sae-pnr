package controleur;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Entrypoint for the application
 *
 * @author Groupe SAE PNR 1D1
 */
public class Main extends Application implements Initializable {
    /**
     * Entrypoint for the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start the application with the Login Screen
     * @param primaryStage the primary stage
     * @throws IOException if the application fails to start
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Scene scene = loadScene("Login");
        // primaryStage.getIcons().setAll(new Image(getClass().getResource("@../../../../data/Logo_PNR.png").toExternalForm())); // Application logo
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.setTitle("PNR");
        primaryStage.show();
    }

    /**
     * Inhereted form Initializable
     * @see javafx.fxml.Initializable
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Load a scene from a FXML file
     * @param name the name of the scene
     */
    private Scene loadScene(String name) throws IOException {
        // Load FXML file
        URL pathFXML = getClass().getResource("../vue/" + name + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(pathFXML);
        AnchorPane root = (AnchorPane) fxmlLoader.load();

        // Add the stylesheet of the page
        Scene scene = new Scene(root);
        URL pathCSS = getClass().getResource("../vue/Style" + name +".css");
        scene.getStylesheets().addAll(pathCSS.toExternalForm());

        return scene;
    }

    /**
     * Switch the current scene to a new one
     * @param name the name of the new scene
     * @param target An element belonging to the current scene
     */
    public static void switchScene(String name, Control target) {
        Stage appStage = (Stage) target.getScene().getWindow();
        Main main = new Main();
        try {
            Scene scene = main.loadScene(name);
            appStage.setScene(scene);
            System.out.println("scene switched to " + name);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Switch the current scene to a new one
     * @param name the name of the new scene
     * @param event the event that triggered the switch
     */
    public static void switchScene(String name, ActionEvent event) {
        Button target = (Button) event.getSource();
        switchScene(name, target);
    }
}