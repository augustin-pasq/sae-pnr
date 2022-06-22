package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modele.donnee.UseDatabase;

public class ConsultHippocampeObsController extends InteractivePage {

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
    private Label especeHippocampe;
    @FXML
    private Label sexe;
    @FXML
    private Label eau;
    @FXML
    private Label peche;
    @FXML
    private Label taille;
    @FXML
    private Label gestant;

    private static ArrayList<String> observation;

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

    public static void setObs(int numObs) {
        observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromHippocampe WHERE ObsH = " + numObs + ";").get(1);
    }

    public void goBack(ActionEvent event) {
        Main.goBack(event);
    }
}
