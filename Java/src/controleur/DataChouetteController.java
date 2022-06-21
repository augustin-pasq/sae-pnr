package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import modele.donnee.TypeObservation;
import modele.donnee.EspeceChouette;
import modele.donnee.Sexe;


import java.net.URL;
import java.util.ResourceBundle;


public class DataChouetteController extends InteractivePage {

    ObservableList<EspeceChouette> especeList = FXCollections.observableArrayList(EspeceChouette.values());
    ObservableList<Sexe> sexeList = FXCollections.observableArrayList(Sexe.values());
    ObservableList<String> protocoleList = FXCollections.observableArrayList("Oui", "Non");
    ObservableList<TypeObservation> typeObservationList = FXCollections.observableArrayList(TypeObservation.values());

    @FXML
    private ComboBox<EspeceChouette> especeComboBox;
    @FXML
    private ComboBox<Sexe> sexeComboBox;
    @FXML
    private ComboBox<String> protocoleComboBox;
    @FXML
    private ComboBox<TypeObservation> typeObservationComboBox;

    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = true;
        especeComboBox.setItems(especeList);
        sexeComboBox.setItems(sexeList);
        protocoleComboBox.setItems(protocoleList);
        typeObservationComboBox.setItems(typeObservationList);
        

    }
}
