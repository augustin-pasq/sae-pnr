package controleur;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = loadScene("Login");
        // primaryStage.getIcons().setAll(new Image(getClass().getResource("@../../../../data/Logo_PNR.png").toExternalForm())); // Application logo
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.setTitle("PNR");
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private Scene loadScene(String name) throws IOException {
        // Load FXML file
        URL pathFXML = getClass().getResource("../vue/" + name + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(pathFXML);
        AnchorPane root = (AnchorPane) fxmlLoader.load();

        // Add the stylesheet of the page
        Scene scene = new Scene(root);
        URL pathCSS = getClass().getResource("../vue/StyleLogin.css");
        scene.getStylesheets().addAll(pathCSS.toExternalForm());

        return scene;
    }

    public static void switchScene(String name, ActionEvent event) {
        Button target = (Button) event.getSource();
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
}