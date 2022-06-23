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
 * Controller for the ConsultBatracienObs page
 *
 * @author Groupe SAE PNR 1D1
 */
public class ConsultBatracienObsController extends InteractivePage {

    /**
     * The observation to display
     */
    private static ArrayList<String> observation;

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
     * The X coordinate of the observation
     */
    @FXML
    private Label coordX;

    /**
     * The Y coordinate of the observation
     */
    @FXML
    private Label coordY;

    /**
     * The specie of the observation
     */
    @FXML
    private Label especeBatracien;

    /**
     * The number of adults of the observation
     */
    @FXML
    private Label nbAdultes;

    /**
     * The number of amplexus of the observation
     */
    @FXML
    private Label nbAmplexus;

    /**
     * The number of clutch of the observation
     */
    @FXML
    private Label nbPontes;

    /**
     * The number of tadpole of the observation
     */
    @FXML
    private Label nbTetards;

    /**
     * The water temperature of the observation
     */
    @FXML
    private Label temperature;

    /**
     * The sky condition of the observation
    */
    @FXML
    private Label ciel;

    /**
     * The air temperature of the observation
     */
    @FXML
    private Label meteoTemperature;

    /**
     * The wind intensity of the observation	
     */
    @FXML
    private Label vent;

    /**
     * The rain intensity of the observation
     */
    @FXML
    private Label pluie;

    /**
     * The temporary humid area value of the observation
     */
    @FXML
    private Label temporaire;

    /**
     * The depth of the area
     */
    @FXML
    private Label profondeur;

    /**
     * The surface of the area
     */
    @FXML
    private Label surface;

    /**
     * The tide's type of the area
     */
    @FXML
    private Label maree;

    /**
     * The slope of the area
     */
    @FXML
    private Label pente;

    /**
     * The opening of the area
     */
    @FXML
    private Label ouverture;

    /**
     * The vegetation of the area
     */
    @FXML
    private Label natureVegetation;

    /**
     * The vegetation's type of the area
     */
    @FXML
    private Label vegetation;

    /**
     * Sets the observation to display
     *
     * @param numObs the observation number
     */
    public static void setObs(int numObs) {
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromBatracien WHERE ObsB = " + numObs + ";").get(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url            the url of the page
     * @param resourceBundle the resource bundle of the page
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        nom.setText(observation.get(22));
        prenom.setText(observation.get(23));
        date.setText(observation.get(24));
        heure.setText(observation.get(25));
        coordX.setText(observation.get(26));
        coordY.setText(observation.get(27));
        especeBatracien.setText(observation.get(1));
        nbAdultes.setText(observation.get(2));
        nbAmplexus.setText(observation.get(3));
        nbPontes.setText(observation.get(4));
        nbTetards.setText(observation.get(5));
        temperature.setText(observation.get(6));
        ciel.setText(observation.get(7));
        meteoTemperature.setText(observation.get(8));
        vent.setText(observation.get(9));
        pluie.setText(observation.get(10));
        temporaire.setText(observation.get(13));
        profondeur.setText(observation.get(14));
        surface.setText(observation.get(15));
        maree.setText(observation.get(16));
        pente.setText(observation.get(17));
        ouverture.setText(observation.get(18));
        natureVegetation.setText(observation.get(19));
        vegetation.setText(observation.get(20));
    }

    /**
     * Handles the back button click
     *
     * @param event the event that triggers the action
     */
    public void goBack(ActionEvent event) {
        Main.goBack(event);
    }
}
