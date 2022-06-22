package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class ConsultHippocampeObs extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML file
        URL pathFXML = getClass().getResource("ConsultHippocampeObs.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(pathFXML);
        AnchorPane root = (AnchorPane) fxmlLoader.load();

        // Add the stylesheet of the page
        Scene scene = new Scene(root);
        URL pathCSS = getClass().getResource("StyleConsultHippocampeObs.css");
        scene.getStylesheets().addAll(pathCSS.toExternalForm());
        // primaryStage.getIcons().setAll(new Image(getClass().getResource("@../../../../data/Logo_PNR.png").toExternalForm())); // Application logo
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.setTitle("PNR");
        primaryStage.show();
    }
}