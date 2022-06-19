package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import modele.donnee.EspeceHippocampe;
import modele.donnee.Peche;
import modele.donnee.Sexe;

import java.net.URL;
import java.util.ResourceBundle;


public class DataHippocampeController extends InteractivePage {

    ObservableList<EspeceHippocampe> especeList = FXCollections.observableArrayList(EspeceHippocampe.ENTERURUS_AEQUOREUS, EspeceHippocampe.HIPPOCAMPUS_GUTTLATUS, EspeceHippocampe.HIPPOCAMPUS_HIPPOCAMPUS, EspeceHippocampe.SYNGNATHUS_ACUS);
    ObservableList<Sexe> sexeList = FXCollections.observableArrayList(Sexe.MALE, Sexe.FEMELLE, Sexe.INCONNU);
    ObservableList<String> gestantList = FXCollections.observableArrayList("Gestant", "Non gestant");
    ObservableList<Peche> typePecheList = FXCollections.observableArrayList(Peche.CASIER_CREVETTES, Peche.CASIER_MORGATES, Peche.NON_RENSEIGNE, Peche.PETIT_FILET, Peche.VERVEUX_ANGUILLES);
    @FXML
    private ComboBox<EspeceHippocampe> especeComboBox;
    @FXML
    private ComboBox<Sexe> sexeComboBox;
    @FXML
    private ComboBox<String> gestantComboBox;
    @FXML
    private ComboBox<Peche> typePecheComboBox;

    public static void main(String[] args) {
        DataHippocampeController controller = new DataHippocampeController();
    }

    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = true;
        especeComboBox.setItems(especeList);
        sexeComboBox.setItems(sexeList);
        gestantComboBox.setItems(gestantList);
        typePecheComboBox.setItems(typePecheList);

    }
}
