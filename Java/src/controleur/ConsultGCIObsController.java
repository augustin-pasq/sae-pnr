package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import modele.donnee.UseDatabase;

public class ConsultGCIObsController extends InteractivePage {

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
    private Label nature;
    @FXML
    private Label nombre;
    @FXML
    private Label dejaObserve;
    @FXML
    private Label idNid;
    @FXML
    private Label plage;
    @FXML
    private Label raisonStop;
    @FXML
    private Label nbEnvols;
    @FXML
    private Label protege;
    @FXML
    private Label male;
    @FXML
    private Label femelle;

    private static ArrayList<String> observation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        nom.setText(observation.get(11));
        prenom.setText(observation.get(12));
        date.setText(observation.get(13));
        heure.setText(observation.get(14));
        coordX.setText(observation.get(15));
        coordY.setText(observation.get(16));
        nature.setText(observation.get(1));
        nombre.setText(observation.get(2));
        dejaObserve.setText(observation.get(3));
        idNid.setText(observation.get(4));
        plage.setText(observation.get(5));
        raisonStop.setText(observation.get(6));
        nbEnvols.setText(observation.get(7));
        protege.setText(observation.get(8));
        male.setText(observation.get(9));
        femelle.setText(observation.get(10));
    }

    public static void setObs(int numObs) {
        observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromGCI WHERE ObsG = " + numObs + ";").get(1);
    }
}
