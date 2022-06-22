package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modele.donnee.UseDatabase;

public class ConsultChouetteObsController extends InteractivePage {

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

        date.setText(observation.get(6));
        heure.setText(observation.get(7));
        coordX.setText(observation.get(8));
        coordY.setText(observation.get(9));
        especeBatracien.setText(observation.get(6));
        nbAdultes.setText(observation.get(6));
        nbAmplexus.setText(observation.get(6));
        nbPontes.setText(observation.get(6));
        nbTetards.setText(observation.get(6));
        temperature.setText(observation.get(6));
        ciel.setText(observation.get(6));
        meteoTemperature.setText(observation.get(6));
        vent.setText(observation.get(6));
        pluie.setText(observation.get(6));
        temporaire.setText(observation.get(6));
        profondeur.setText(observation.get(6));
        surface.setText(observation.get(6));
        maree.setText(observation.get(6));
        pente.setText(observation.get(6));
        ouverture.setText(observation.get(6));
        natureVegetation.setText(observation.get(6));
        vegetation.setText(observation.get(6));
    }

    public static void setObs(int numObs) {
        observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromChouette WHERE numObs = " + numObs + ";").get(1);
    }
}
