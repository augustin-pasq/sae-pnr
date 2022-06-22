package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.donnee.ContenuNid;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class FilterGCIController extends InteractivePage {

    private final String ANIMAL = "GCI";
    ObservableList<ContenuNid> natureList = FXCollections.observableArrayList(ContenuNid.values());
    ObservableList<String> nidObserveList = FXCollections.observableArrayList("Oui", "Non");
    ObservableList<String> raisonList = FXCollections.observableArrayList("Envol", "Inconnu", "Maree", "Pietinement", "Prédation");
    ObservableList<String> nidProtegeList = FXCollections.observableArrayList("Oui", "Non");

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField lambertXField;
    @FXML
    private TextField lambertYField;
    @FXML
    private ComboBox<ContenuNid> natureComboBox;
    @FXML
    private TextField nombreField;
    @FXML
    private ComboBox<String> nidObserveComboBox;
    @FXML
    private TextField idField;
    @FXML
    private TextField plageField;
    @FXML
    private ComboBox<String> raisonComboBox;
    @FXML
    private TextField nbEnvolField;
    @FXML
    private ComboBox<String> nidProtegeComboBox;
    @FXML
    private TextField bagueMaleField;
    @FXML
    private TextField bagueFemelleField;
    @FXML
    private Button validateButton;

    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = true;
        natureComboBox.setItems(natureList);
        nidObserveComboBox.setItems(nidObserveList);
        raisonComboBox.setItems(raisonList);
        nidProtegeComboBox.setItems(nidProtegeList);
    }

    @FXML
    public void filter(ActionEvent event){

        // Init //
        String lastName = lastNameField.getText().toUpperCase();
        String firstName = firstNameField.getText().toUpperCase();
        LocalDate date = dateField.getValue();
        String time = timeField.getText();
        String lambertX = lambertXField.getText();
        String lambertY = lambertYField.getText();
        String nature = natureComboBox.getValue().toString();
        int nombre = Integer.parseInt(nombreField.getText());
        String presentMaisNonObs = nidObserveComboBox.getValue().toString();
        int leNid = Integer.parseInt(idField.getText());
        String nomPlage = plageField.getText();
        String raisonArretObservation = raisonComboBox.getValue().toString();
        int nbEnvols = Integer.parseInt(nbEnvolField.getText());
        int protection = Integer.parseInt(nidProtegeComboBox.getValue());
        String bagueMale = bagueMaleField.getText();
        String bagueFemelle = bagueFemelleField.getText();
        
        //

        
    }
}
