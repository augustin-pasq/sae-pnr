package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modele.donnee.UseDatabase;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the ConsultChouetteObs page
 *
 * @author Groupe SAE PNR 1D1
 */
public class ConsultChouetteObsController extends InteractivePage {

    /**
     * The first name of the observer
     */
    @FXML
    private Label nom;

    /**
     * The last name of the observer
     */
    @FXML
    private Label prenom;
    
    /**
     * The date of the observation
     */
    @FXML
    private Label date;

    /**
     * The time of the observation
     */
    @FXML
    private Label heure;

    /**
     * The X Lambert93 coordinates of the observation
     */
    @FXML
    private Label coordX;

    /**
     * The Y Lambert93 coordinates of the observation
     */
    @FXML
    private Label coordY;

    /**
     * The species of the observation
     */
    @FXML
    private Label especeChouette;

    /**
     * Indicates if the observation protocol is followed
     */
    @FXML
    private Label protocole;

    /**
     * The type of the observation
     */
    @FXML
    private Label typeObs;

    /**
     * The gender of the owl
     */
    @FXML
    private Label sexe;

    /**
     * The observation to display
     */
    private static ArrayList<String> observation;

    /**
     * Initializes the controller class.
     *
     * @param url            the url of the page
     * @param resourceBundle the resource bundle of the page
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        date.setText(observation.get(6));
        heure.setText(observation.get(7));
        coordX.setText(observation.get(8));
        coordY.setText(observation.get(9));
        especeChouette.setText(observation.get(4));
        protocole.setText(observation.get(0));
        typeObs.setText(observation.get(1));
        sexe.setText(observation.get(5));
    }

    /**
     * Sets the observation to display
     * 
     * @param numObs the id of observation to display
     */
    public static void setObs(int numObs) {
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromChouette WHERE numObs = " + numObs + ";")
                    .get(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the back button click
     * 
     * @param event the event that triggered the click
     */
    public void goBack(ActionEvent event) {
        Main.goBack(event);
    }
}
