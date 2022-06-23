package controleur;

import java.net.URL;
import java.util.ResourceBundle;

public class SuccessController extends InteractivePage {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.homeButton.setOnAction(super::goHome);
    }
}
