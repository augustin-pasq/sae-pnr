package controleur;

import javafx.event.Event;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import modele.donnee.EspeceObservee;

import java.net.URL;
import java.util.ResourceBundle;


public class GenerateChartController extends InteractivePage {

    ObservableList<EspeceObservee> especeList = FXCollections.observableArrayList(EspeceObservee.values());
    ObservableList<String> abscisseList = FXCollections.observableArrayList("Période temporelle");
    ObservableList<String> ordoneeList = FXCollections.observableArrayList("Nombre d'individus");
    @FXML
    private ComboBox<EspeceObservee> especeComboBox;
    @FXML
    private ComboBox<String> abscisseComboBox;
    @FXML
    private ComboBox<String> ordonneeComboBox;
    @FXML
    private Button generateButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        especeComboBox.setItems(especeList);
        abscisseComboBox.setItems(abscisseList);
        ordonneeComboBox.setItems(ordoneeList);
    }

    private void generate(final Event event){
        System.out.println("generation");
    }
}
