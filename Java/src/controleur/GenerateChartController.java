package controleur;

import  modele.donnee.EspeceObservee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;


public class GenerateChartController implements Initializable {

    ObservableList<EspeceObservee> especeList = FXCollections.observableArrayList(EspeceObservee.values());
    @FXML
    private ComboBox<EspeceObservee> especeComboBox;

    ObservableList<String> abscisseList = FXCollections.observableArrayList("Période temporelle");
    @FXML
    private ComboBox<String> abscisseComboBox;

    ObservableList<String> ordoneeList = FXCollections.observableArrayList("Nombre d'individus");
    @FXML
    private ComboBox<String> ordonneeComboBox;




    public static void main(String[] args) {
        GenerateChartController controller = new GenerateChartController();
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        especeComboBox.setItems(especeList);
        abscisseComboBox.setItems(abscisseList);
        ordonneeComboBox.setItems(ordoneeList); 
    }
}
