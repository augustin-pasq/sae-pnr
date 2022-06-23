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
 * Controller for the ConsultLoutreObs page
 *
 * @author Groupe SAE PNR 1D1
 */
public class ConsultLoutreObsController extends InteractivePage {

    /**
     * The last name of the observer
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
     * The village where the otter is observed
     */
    @FXML
    private Label commune;

    /**
     * The hamlet where the otter is observed
     */
    @FXML
    private Label lieudit;

    /**
     * Indicates if the index is positive, negative or not determined
     */
    @FXML
    private Label indice;

    /**
     * The observation to display
     */
    private static ArrayList<String> observation;

    /**
     * Inherited method from Initializable
     * @see javafx.fxml.Initializable
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        nom.setText(observation.get(4));
        prenom.setText(observation.get(5));
        date.setText(observation.get(6));
        heure.setText(observation.get(7));
        coordX.setText(observation.get(8));
        coordY.setText(observation.get(9));
        commune.setText(observation.get(1));
        lieudit.setText(observation.get(2));
        indice.setText(observation.get(3));
    }

    /**
     * Set the observation to display
     *
     * @param numObs the id of the observation
     */
    public static void setObs(int numObs) {
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromLoutre WHERE ObsL = " + numObs + ";")
                    .get(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
