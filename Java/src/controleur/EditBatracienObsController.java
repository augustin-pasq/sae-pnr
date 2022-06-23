package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.donnee.EspeceBatracien;
import modele.donnee.UseDatabase;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the EditBatracienObs page
 *
 * @author Groupe SAE PNR 1D1
 */
public class EditBatracienObsController extends InteractivePage {

    private static ArrayList<String> observation;

    /**
     * The list of batracien species
     */
    ObservableList<EspeceBatracien> especeList = FXCollections.observableArrayList(EspeceBatracien.values());
    /**
     * The list of sky weather states
     */
    ObservableList<String> meteoCielList = FXCollections.observableArrayList("dégagé", "semi-dégagé", "nuageux");
    /**
     * The list of temperature states
     */
    ObservableList<String> meteoTemperatureList = FXCollections.observableArrayList("froid", "moyen", "chaud");
    /**
     * The list of wind states
     */
    ObservableList<String> meteoVentList = FXCollections.observableArrayList("non", "léger", "moyen", "fort");
    /**
     * The list of rain states
     */
    ObservableList<String> meteoPluieList = FXCollections.observableArrayList("non", "légère", "moyenne", "forte");
    /**
     * The list of option to whether the zone is temporary or not
     */
    ObservableList<String> zoneTemporaireList = FXCollections.observableArrayList("Oui", "Non");
    /**
     * The list of types af ponds
     */
    ObservableList<String> typeMareeList = FXCollections.observableArrayList("Prairie", "Etang", "Marais", "Mare");
    /**
     * The list of types of slopes
     */
    ObservableList<String> typePenteList = FXCollections.observableArrayList("Raide", "Abrupte", "Douce");
    /**
     * The of covering types
     */
    ObservableList<String> typeOuvertureList = FXCollections.observableArrayList("Abritee", "Semi-Abritee", "Ouverte");
    /**
     * The list of types of vegetation
     */
    ObservableList<String> typeVegetationList = FXCollections.observableArrayList("environnement", "bordure",
            "ripisyle");

    /**
     * The last name of the observer
     */
    @FXML
    private TextField lastNameField;

    /**
     * The first name of the observer
     */
    @FXML
    private TextField firstNameField;

    /**
     * The date of the observation
     */
    @FXML
    private DatePicker dateField;

    /**
     * The time of the observation
     */
    @FXML
    private TextField timeField;

    /**
     * The X coordinate of the observation
     */
    @FXML
    private TextField lambertXField;

    /**
     * The Y coordinate of the observation
     */
    @FXML
    private TextField lambertYField;

    /**
     * The specie of the observation
     */
    @FXML
    private ComboBox<EspeceBatracien> especeComboBox;

    /**
     * The number of adults of the observation
     */
    @FXML
    private TextField nbAdultesField;

    /**
     * The number of amplexus of the observation
     */
    @FXML
    private TextField nbAmplexusField;

    /**
     * The number of clutch of the observation
     */
    @FXML
    private TextField nbPontesField;

    /**
     * The number of tadpole of the observation
     */
    @FXML
    private TextField nbTetardsField;

    /**
     * The water temperature of the observation
     */
    @FXML
    private TextField temperatureField;

    /**
     * The sky condition of the observation
     */
    @FXML
    private ComboBox<String> meteoCielComboBox;

    /**
     * The air temperature of the observation
     */
    @FXML
    private ComboBox<String> meteoTemperatureComboBox;

    /**
     * The wind intensity of the observation
     */
    @FXML
    private ComboBox<String> meteoVentComboBox;

    /**
     * The rain intensity of the observation
     */
    @FXML
    private ComboBox<String> meteoPluieComboBox;

    /**
     * The temporary humid area value of the observation
     */
    @FXML
    private ComboBox<String> zoneTemporaireComboBox;

    /**
     * The depth of the area
     */
    @FXML
    private TextField zoneProfondeurField;

    /**
     * The surface of the area
     */
    @FXML
    private TextField zoneSurfaceField;

    /**
     * The tide's type of the area
     */
    @FXML
    private ComboBox<String> zoneMareeComboBox;

    /**
     * The slope of the area
     */
    @FXML
    private ComboBox<String> zonePenteComboBox;

    /**
     * The opening of the area
     */
    @FXML
    private ComboBox<String> zoneOuvertureComboBox;

    /**
     * The vegetation of the area
     */
    @FXML
    private ComboBox<String> vegetationComboBox;

    /**
     * The vegetation's type of the area
     */
    @FXML
    private TextField vegetationField;

    /**
     * Allows you to retrieve all the data of an observation by making a query in
     * the database
     * 
     * @param numObs Observation number
     */
    public static void setObs(int numObs) {
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromBatracien WHERE numObs = " + numObs + ";")
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


    /**
     * Allows you to initialise the attributes of the page, firstly the ComboBoxes
     * and then by initialising the fields with the data from the database
     *
     * @param url             the url of the page
     * @param ressourceBundle the resource bundle of the page
     */
    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = false;
        especeComboBox.setItems(especeList);
        meteoCielComboBox.setItems(meteoCielList);
        meteoTemperatureComboBox.setItems(meteoTemperatureList);
        meteoVentComboBox.setItems(meteoVentList);
        meteoPluieComboBox.setItems(meteoPluieList);
        zoneTemporaireComboBox.setItems(zoneTemporaireList);
        zoneMareeComboBox.setItems(typeMareeList);
        zonePenteComboBox.setItems(typePenteList);
        zoneOuvertureComboBox.setItems(typeOuvertureList);
        vegetationComboBox.setItems(typeVegetationList);

        lastNameField.setText(observation.get(22));
        firstNameField.setText(observation.get(23));
        LocalDate saisieDate = LocalDate.parse(observation.get(24));
        dateField = new DatePicker(saisieDate);
        timeField.setText(observation.get(25));
        lambertXField.setText(observation.get(26));
        lambertYField.setText(observation.get(27));
        EspeceBatracien saisieBatracien = EspeceBatracien.valueOf(observation.get(1));
        especeComboBox.getSelectionModel().select(saisieBatracien);
        nbAdultesField.setText(observation.get(2));
        nbAmplexusField.setText(observation.get(3));
        nbPontesField.setText(observation.get(4));
        nbTetardsField.setText(observation.get(5));
        temperatureField.setText(observation.get(6));
        meteoCielComboBox.getSelectionModel().select(observation.get(7));
        meteoTemperatureComboBox.getSelectionModel().select(observation.get(8));
        meteoVentComboBox.getSelectionModel().select(observation.get(9));
        meteoPluieComboBox.getSelectionModel().select(observation.get(10));
        zoneTemporaireComboBox.getSelectionModel().select(observation.get(13));
        zoneProfondeurField.setText(observation.get(14));
        zoneSurfaceField.setText(observation.get(15));
        zoneMareeComboBox.getSelectionModel().select(observation.get(16));
        zonePenteComboBox.getSelectionModel().select(observation.get(17));
        zoneOuvertureComboBox.getSelectionModel().select(observation.get(18));
        vegetationComboBox.getSelectionModel().select(observation.get(20));

    }

}
