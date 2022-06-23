package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.donnee.ContenuNid;
import modele.donnee.EspeceBatracien;
import modele.donnee.UseDatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

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
    private TextField concerneZH;
    @FXML
    private TextField concerneVEGE;
    @FXML
    private TextField vegetationField;
    @FXML
    private Button validateButton;
    private static int idObs;

    public static void setObs(int numObs) {
        idObs = numObs;
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromBatracien WHERE obsB = " + numObs + ";").get(1);
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

        EspeceBatracien saisieBatracien = null;
        String tmpsaisieBatracien = observation.get(1);
        if (tmpsaisieBatracien != null) {
            if (tmpsaisieBatracien.equals("calamite")){
                saisieBatracien = EspeceBatracien.CALAMITE;
            } else if (tmpsaisieBatracien.equals("pelodyte")) {
                saisieBatracien = EspeceBatracien.PELODYTE;
            }
        }
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
        concerneVEGE.setText(observation.get(12));
        concerneZH.setText(observation.get(11));
    }

    @FXML
    private void validate (ActionEvent event) {

        //Get the data from fields
        String lastName = lastNameField.getText().toUpperCase();
        String firstName = firstNameField.getText().toUpperCase();
        LocalDate date = dateField.getValue();
        String time = timeField.getText();
        String lambertX = lambertXField.getText();
        String lambertY = lambertYField.getText();
        String nbAdulte = nbAdultesField.getText();
        String nbAmplexus = nbAmplexusField.getText();
        String nbPontes = nbPontesField.getText();
        String nbTetards = nbTetardsField.getText();
        String temperature = temperatureField.getText();
        String zoneProfondeur = zoneProfondeurField.getText();
        String zoneSurface = zoneSurfaceField.getText();
        String vegetation = vegetationField.getText();
        String concerneZh = concerneZH.getText();
        String concerneVege = concerneVEGE.getText();

        //Get the datas from comboBox
        String espece = especeComboBox.getValue() == null ? null : especeComboBox.getValue().toString();
        String meteoCiel = meteoCielComboBox.getValue() == null ? null : meteoCielComboBox.getValue().toString();
        String meteoTemperature = meteoTemperatureComboBox.getValue() == null ? null : meteoTemperatureComboBox.getValue().toString();
        String meteoVent = meteoVentComboBox.getValue() == null ? null : meteoVentComboBox.getValue().toString();
        String meteoPluie = meteoPluieComboBox.getValue() == null ? null : meteoPluieComboBox.getValue().toString();
        Integer zoneTemporaire = null;
        if (zoneTemporaireComboBox.getValue() != null) {
            zoneTemporaire = zoneTemporaireComboBox.getValue().equals("Oui") ? 1 : 0;
        }

        String typeMaree = zoneMareeComboBox.getValue() == null ? null : zoneMareeComboBox.getValue().toString();
        String zonePente = zonePenteComboBox.getValue() == null ? null : zonePenteComboBox.getValue().toString();
        String zoneOuverture = zoneOuvertureComboBox.getValue() == null ? null : zoneOuvertureComboBox.getValue().toString();
        String vegetationCombo = vegetationComboBox.getValue() == null ? null : vegetationComboBox.getValue().toString();

        try {
            // Check the validity of the data
            //checkFields(lastName, firstName, date, time, lambertX, lambertY, nombre, leNid, plage, nbEnvol, bagueMale, bagueFemelle, nidObserve, nidProtege, raisonArret, nature);

            // Try to get the observer's id if it exists
            ArrayList<ArrayList<String>> observateur = UseDatabase.selectQuery(String.format("SELECT idObservateur FROM Observateur WHERE nom = '%s' AND prenom = '%s' LIMIT 1", lastName, firstName));
            int idObservateur;
            if (observateur.size() == 1) {
                // If the observer doesn't exist, create it, with a unique id
                idObservateur = Math.abs(UUID.randomUUID().hashCode());
                UseDatabase.updateQuery(String.format("INSERT INTO Observateur (idObservateur, nom, prenom) VALUES (%d, '%s', '%s')",
                        idObservateur, lastName, firstName));
            } else {
                // If the observer exists, get its id
                idObservateur = Integer.parseInt(observateur.get(1).get(0));
            }

            System.out.println(meteoCiel);
            System.out.println(meteoVent);
            System.out.println(meteoPluie);
            System.out.println(meteoTemperature);

            Connection conn = UseDatabase.MySQLConnection();
            String q3 = "UPDATE Obs_Batracien SET nombreAdultes = ?, nombreAmplexus = ?, nombrePonte = ?, nombreTetard = ? , temperature = ?, meteo_ciel = ?, meteo_temp = ?, meteo_vent = ?, meteo_pluie = ?, concerne_ZH = ?, concernes_vege = ? WHERE obsB = ?";
            PreparedStatement p = conn.prepareStatement(q3);
            p.setInt(1, Integer.parseInt(nbAdulte));
            p.setInt(2, Integer.parseInt(nbAmplexus));
            p.setInt(3, Integer.parseInt(nbPontes));
            p.setInt(4, Integer.parseInt(nbTetards));
            p.setInt( 5, Integer.parseInt(temperature));
            p.setString(6, meteoCiel);
            p.setString(7, meteoTemperature);
            p.setString(8, meteoVent);
            p.setString(9, meteoPluie);
            p.setInt(10, Integer.parseInt(concerneZh));
            p.setInt(11, Integer.parseInt(concerneVege));
            p.setInt(12, idObs);
            p.executeUpdate();



            UseDatabase.updateQuery(String.format("UPDATE Observation SET dateObs = '%s', heureObs = '%s', lieu_Lambert_X = '%s', lieu_Lambert_Y = '%s' WHERE idObs = '%s'", date, time, lambertX, lambertY, idObs));
            UseDatabase.updateQuery(String.format("UPDATE AObserve set lobservateur = %d WHERE lobservateur = %d", idObservateur, idObs));
            Main.showPopup("Donnée mis a jour correctement", lastNameField, false);
        } catch (IllegalArgumentException e) {
            // If one of the fields is invalid, show a popup with the error message
            Main.showPopup(e.getMessage(), event, true);
        } catch (SQLException e) {
            // If an SQL exception has been thrown, show a popup with an error message
            Main.showPopup("Une erreur est survenue au moment de l'enregistrement des données", event, true);
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            Main.showPopup("Merci de remplir tous les champs", event, true);
            e.printStackTrace();
        } catch (Exception e) {
            // Catch all other exceptions and show a popup with a generic error message
            Main.showPopup("Une erreur inconnue est survenue", event, true);
            e.printStackTrace();
        }
    }
}
