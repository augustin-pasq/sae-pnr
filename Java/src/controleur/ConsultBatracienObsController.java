package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modele.donnee.UseDatabase;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ConsultBatracienObsController extends InteractivePage {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label date;
    @FXML
    private Label heure;
    @FXML
    private Label coordX;
    @FXML
    private Label coordY;
    @FXML
    private Label especeBatracien;
    @FXML
    private Label nbAdultes;
    @FXML
    private Label nbAmplexus;
    @FXML
    private Label nbPontes;
    @FXML
    private Label nbTetards;
    @FXML
    private Label temperature;
    @FXML
    private Label ciel;
    @FXML
    private Label meteoTemperature;
    @FXML
    private Label vent;
    @FXML
    private Label pluie;
    @FXML
    private Label temporaire;
    @FXML
    private Label profondeur;
    @FXML
    private Label surface;
    @FXML
    private Label maree;
    @FXML
    private Label pente;
    @FXML
    private Label ouverture;
    @FXML
    private Label natureVegetation;
    @FXML
    private Label vegetation;

    private static ArrayList<String> observation;

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
    
    public static void setObs(int numObs) {
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromBatracien WHERE ObsB = " + numObs + ";").get(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void goBack(ActionEvent event) {
        Main.goBack(event);
    }
}
