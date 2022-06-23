package controleur;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the succes message popup
 */
public class SuccessController extends InteractivePage {

    /**
     * Inherited method from Initializable
     * @see javafx.fxml.Initializable
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.homeButton.setOnAction(super::goHome);
        this.backButton.setOnAction(super::goHome);
    }
}
