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

    @FXML
    /**
     * The last naame of the observer
     */
    private Label nom;

    @FXML
    /**
     * The first name of the observer
     */
    private Label prenom;

    @FXML
    /**
     * The date of the observation
     */
    private Label date;

    @FXML
    /**
     * The time of the observation
     */
    private Label heure;

    @FXML
    /**
     * The X Lambert93 coordinates of the observation
     */
    private Label coordX;

    @FXML
    /**
     * The Y Lambert93 coordinates of the observation
     */
    private Label coordY;

    @FXML
    /**
     * The nature of the observation
     */
    private Label nature;

    @FXML
    /**
     * The amount of items (nature attribute) observed
     */
    private Label nombre;

    @FXML
    /**
     * Indicates if the nid is already observed
     */
    private Label dejaObserve;

    @FXML
    /**
     * The ID of the nest
     */
    private Label idNid;

    @FXML
    /**
     * The name of the beach
     */
    private Label plage;

    @FXML
    /**
     * The reason why the observation was stopped
     */
    private Label raisonStop;

    @FXML
    /**
     * The amount of flight in the nest
     */
    private Label nbEnvols;

    @FXML
    /**
     * Indicates if the nest is protected
     */
    private Label protege;

    @FXML
    /**
     * The code of the male ring
     */
    private Label male;

    @FXML
    /**
     * The code of the female ring
     */
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
