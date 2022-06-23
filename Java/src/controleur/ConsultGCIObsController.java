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
 * Controller for the ConsultGCIObs page
 *
 * @author Groupe SAE PNR 1D1
 */
public class ConsultGCIObsController extends InteractivePage {

    /**
     * The observation to display
     */
    private static ArrayList<String> observation;

    /**
     * The last naame of the observer
     */
    @FXML
    private Label nom;

    /**
     * The first name of the observer
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
     * The nature of the observation
     */
    @FXML
    private Label nature;

    /**
     * The amount of items (nature attribute) observed
     */
    @FXML
    private Label nombre;

    /**
     * Indicates if the nid is already observed
     */
    @FXML
    private Label dejaObserve;

    /**
     * The ID of the nest
     */
    @FXML
    private Label idNid;

    /**
     * The name of the beach
     */
    @FXML
    private Label plage;

    /**
     * The reason why the observation was stopped
     */
    @FXML
    private Label raisonStop;

    /**
     * The amount of flight in the nest
     */
    @FXML
    private Label nbEnvols;

    /**
     * Indicates if the nest is protected
     */
    @FXML
    private Label protege;

    /**
     * The code of the male ring
     */
    @FXML
    private Label male;

    /**
     * The code of the female ring
     */
    @FXML
    private Label femelle;

    /**
     * Set the observation to display
     *
     * @param numObs the id of the observation to display
     */
    public static void setObs(int numObs) {
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromGCI WHERE ObsG = " + numObs + ";").get(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initialise the scene
     *
     * @param url            the url of the page
     * @param resourceBundle the resource bundle of the page
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        nom.setText(observation.get(11));
        prenom.setText(observation.get(12));
        date.setText(observation.get(13));
        heure.setText(observation.get(14));
        coordX.setText(observation.get(15));
        coordY.setText(observation.get(16));
        nature.setText(observation.get(1));
        nombre.setText(observation.get(2));
        dejaObserve.setText(observation.get(3));
        idNid.setText(observation.get(4));
        plage.setText(observation.get(5));
        raisonStop.setText(observation.get(6));
        nbEnvols.setText(observation.get(7));
        protege.setText(observation.get(8));
        male.setText(observation.get(9));
        femelle.setText(observation.get(10));
    }

    /**
     * Go back to the previous page
     *
     * @param event the event that triggered the action
     */
    public void goBack(ActionEvent event) {
        Main.goBack(event);
    }
}
