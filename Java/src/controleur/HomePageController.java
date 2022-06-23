package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the HomePage page
 */
public class HomePageController implements Initializable {

    /**
     * The button to go to the ConsultData pages
     */
    @FXML
    public Button consulter;

    /**
     * The button to go to the Edit pages
     */
    @FXML
    public Button modifier;

    /**
     * The button to go to the Data pages
     */
    @FXML
    public Button saisir;

    /**
     * The button to logout
     */
    @FXML
    public Button logout;

    /**
     * Move the current page to the correct page, which is determined by the button clicked
     * 
     * @param event the event
     */
    public void move(final ActionEvent event) {
        Button target = (Button) event.getSource();
        switch (target.getId()) {
            case "Saisir" -> {
                Data data = new Data("Data");
                Main.switchScene("SpeciesChoice", target, data);
            }
            case "Modifier" -> {
                Data data = new Data("Edit");
                Main.switchScene("SpeciesChoice", target, data);
            }
            case "Consulter" -> Main.switchScene("ConsultData", target);
            case "logout" -> Main.switchScene("Login", target);
            default -> System.err.println("Error: unknown button \"" + target.getText() + "\"");
        }
    }

    /**
     * Inherited method from Initializable
     * @see javafx.fxml.Initializable
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
