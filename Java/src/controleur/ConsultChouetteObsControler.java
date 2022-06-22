package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modele.donnee.UseDatabase;

public class ConsultChouetteObsControler extends InteractivePage {

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
    private Label especeChouette;
    @FXML
    private Label protocole;
    @FXML
    private Label typeObs;
    @FXML
    private Label sexe;

    private static ArrayList<String> observation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        date.setText(observation.get(6));
        heure.setText(observation.get(7));
        coordX.setText(observation.get(8));
        coordY.setText(observation.get(9));
        especeChouette.setText(observation.get(4));
        protocole.setText(observation.get(0));
        typeObs.setText(observation.get(1));
        sexe.setText(observation.get(5));
    }

    public static void setObs(int numObs) {
        observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromChouette WHERE numObs = " + numObs + ";").get(1);
    }
}
