package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import modele.donnee.EspeceBatracien;


import java.net.URL;
import java.util.ResourceBundle;



public class DataBatracienController extends InteractivePage {

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
    private ComboBox<EspeceBatracien> especeComboBox;
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
    private ComboBox<String> zoneMareeComboBox;
    @FXML
    private ComboBox<String> zonePenteComboBox;
    @FXML
    private ComboBox<String> zoneOuvertureComboBox;
    @FXML
    private ComboBox<String> vegetationComboBox;


    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = true;
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

    }
}
