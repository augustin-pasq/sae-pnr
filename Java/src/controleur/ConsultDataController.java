package controleur;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsultDataController extends InteractivePage {

    @Override
    public void goBack(ActionEvent event) {
        Main.switchScene("HomePage", backButton);
    }

    @Override
    public void goHome(ActionEvent event) {
        Main.switchScene("HomePage", homeButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
