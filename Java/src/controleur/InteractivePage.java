package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Super-class for all interactive pages.
 * Implements back and home buttons.
 *
 * @author Groupe SAE PNR 1D1
 */
public abstract class InteractivePage implements Initializable {
    /**
     * The back button
     */
    @FXML
    public Button backButton;

    /**
     * The home button
     */
    @FXML
    public Button homeButton;

    /**
     * Whether the user is admin or not
     */
    public boolean isAdmin = false;

    /**
     * Go to the home page
     *
     * @param event the event that triggered the method
     */
    public void goHome(final ActionEvent event) {
        if (isAdmin) {
            Main.switchScene("AdminPanel", homeButton);
        } else {
            Main.switchScene("HomePage", homeButton);
        }
    }

    /**
     * Initialise the page with the back and home buttons actions
     *
     * @param url            the url of the page
     * @param resourceBundle the resource bundle of the page
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.backButton.setOnAction(Main::goBack);
        this.homeButton.setOnAction(this::goHome);
    }
}
