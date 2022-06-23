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
 * Controller for the ConsultHippocampeObs page
 *
 * @author Groupe SAE PNR 1D1
 */
public class ConsultHippocampeObsController extends InteractivePage {

    /**
     * The observation to display
     */
    private static ArrayList<String> observation;

    @FXML
    /**
     * The last name of the observer
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
     * The spec of the seahorse
     */
    private Label especeHippocampe;

    @FXML
    /**
     * The sexe of the seahorse
     */
    private Label sexe;

    @FXML
    /**
     * The temperature of the water
     */
    private Label eau;

    @FXML
    /**
     * The type of fishing of the seahorse
     */
    private Label peche;

    @FXML
    /**
     * The size of the seahorse
     */
    private Label taille;

    @FXML
    /**
     * Indicates if the seahorse is gestant
     */
    private Label gestant;

    /**
     * Set the observation to display
     *
     * @param numObs the id of the observation to display
     */
    public static void setObs(int numObs) {
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromHippocampe WHERE ObsH = " + numObs + ";")
                    .get(1);
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

        nom.setText(observation.get(7));
        prenom.setText(observation.get(8));
        date.setText(observation.get(9));
        heure.setText(observation.get(10));
        coordX.setText(observation.get(11));
        coordY.setText(observation.get(12));
        especeHippocampe.setText(observation.get(1));
        sexe.setText(observation.get(2));
        eau.setText(observation.get(3));
        peche.setText(observation.get(4));
        taille.setText(observation.get(5));
        gestant.setText(observation.get(6));
    }

    /**
     * Handle the back button
     *
     * @param event the event
     */
    public void goBack(ActionEvent event) {
        Main.goBack(event);
    }
}
