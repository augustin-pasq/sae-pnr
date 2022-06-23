package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class EditBatracienObsController extends InteractivePage {

    private static ArrayList<String> observation;

    ObservableList<EspeceBatracien> especeList = FXCollections.observableArrayList(EspeceBatracien.values());
    ObservableList<String> meteoCielList = FXCollections.observableArrayList("dégagé", "semi-dégagé", "nuageux");
    ObservableList<String> meteoTemperatureList = FXCollections.observableArrayList("froid", "moyen", "chaud");
    ObservableList<String> meteoVentList = FXCollections.observableArrayList("non", "léger", "moyen", "fort");
    ObservableList<String> meteoPluieList = FXCollections.observableArrayList("non", "légère", "moyenne", "forte");
    ObservableList<String> zoneTemporaireList = FXCollections.observableArrayList("Oui", "Non");
    ObservableList<String> typeMareeList = FXCollections.observableArrayList("Prairie", "Etang", "Marais", "Mare");
    ObservableList<String> typePenteList = FXCollections.observableArrayList("Raide", "Abrupte", "Douce");
    ObservableList<String> typeOuvertureList = FXCollections.observableArrayList("Abritee", "Semi-Abritee", "Ouverte");
    ObservableList<String> typeVegetationList = FXCollections.observableArrayList("environnement", "bordure", "ripisyle");

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField lambertXField;
    @FXML
    private TextField lambertYField;
    @FXML
    private ComboBox<EspeceBatracien> especeComboBox;
    @FXML
    private TextField nbAdultesField;
    @FXML
    private TextField nbAmplexusField;
    @FXML
    private TextField nbPontesField;
    @FXML
    private TextField nbTetardsField;
    @FXML
    private TextField temperatureField;
    @FXML
    private ComboBox<String> meteoCielComboBox;
    @FXML
    private ComboBox<String> meteoTemperatureComboBox;
    @FXML
    private ComboBox<String> meteoVentComboBox;
    @FXML
    private ComboBox<String> meteoPluieComboBox;
    @FXML
    private ComboBox<String> zoneTemporaireComboBox;
    @FXML
    private TextField zoneProfondeurField;
    @FXML
    private TextField zoneSurfaceField;
    @FXML
    private ComboBox<String> zoneMareeComboBox;
    @FXML
    private ComboBox<String> zonePenteComboBox;
    @FXML
    private ComboBox<String> zoneOuvertureComboBox;
    @FXML
    private ComboBox<String> vegetationComboBox;
    @FXML
    private TextField vegetationField;

    public static void setObs(int numObs) {
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromBatracien WHERE numObs = " + numObs + ";")
                    .get(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
