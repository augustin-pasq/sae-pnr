package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public static void main(String[] args) {

    }

    public static void switchScene(String name, ActionEvent event) {
        Button target = (Button) event.getSource();
        Stage appStage = (Stage) target.getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(MainController.class.getResource("@../vue/" + name + ".fxml"));
            Scene scene = new Scene(root);
            appStage.setScene(scene);
            appStage.show();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
