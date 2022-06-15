package controleur;

import  modele.donnee.EspeceHippocampe;
import  modele.donnee.Sexe;
import  modele.donnee.Peche;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;


public class DataHippocampeController implements Initializable {

    ObservableList<EspeceHippocampe> especeList = FXCollections.observableArrayList(EspeceHippocampe.ENTERURUS_AEQUOREUS, EspeceHippocampe.HIPPOCAMPUS_GUTTLATUS, EspeceHippocampe.HIPPOCAMPUS_HIPPOCAMPUS, EspeceHippocampe.SYNGNATHUS_ACUS);
    @FXML
    private ComboBox<EspeceHippocampe> especeComboBox;

    ObservableList<Sexe> sexeList = FXCollections.observableArrayList(Sexe.MALE, Sexe.FEMELLE, Sexe.INCONNU);
    @FXML
    private ComboBox<Sexe> sexeComboBox;

    ObservableList<String> gestantList = FXCollections.observableArrayList("Gestant", "Non gestant");
    @FXML
    private ComboBox<String> gestantComboBox;

    ObservableList<Peche> typePecheList = FXCollections.observableArrayList(Peche.CASIER_CREVETTES, Peche.CASIER_MORGATES, Peche.NON_RENSEIGNE, Peche.PETIT_FILET, Peche.PETIT_FILET, Peche.VERVEUX_ANGUILLES);
    @FXML
    private ComboBox<Peche> typePecheComboBox;



    public static void main(String[] args) {
        DataHippocampeController controller = new DataHippocampeController();
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        especeComboBox.setItems(especeList);
        sexeComboBox.setItems(sexeList);
        gestantComboBox.setItems(gestantList);
        typePecheComboBox.setItems(typePecheList);
        
    }
}
