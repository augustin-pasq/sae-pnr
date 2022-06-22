package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.donnee.UseDatabase;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.UUID;

import controleur.ObservationChoiceController;

public class FilterLoutreController extends InteractivePage {

    private final String ANIMAL = "Loutre";  
    ObservableList<String> indiceList = FXCollections.observableArrayList("Positif", "Négatif", "Non prospection");

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
    private TextField communeField;
    @FXML
    private TextField lieuDitField;
    @FXML
    private ComboBox<String> indiceComboBox;
    @FXML
    private Button validateButton;

    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        indiceComboBox.setItems(indiceList);
    }

    @FXML
    public void filterQuery(ActionEvent event) {
        String lastName = lastNameField.getText().toUpperCase();
        String firstName = firstNameField.getText().toUpperCase();
        LocalDate date = dateField.getValue();
        String time = timeField.getText();
        String lambertX = lambertXField.getText();
        String lambertY = lambertYField.getText();
        String commune = communeField.getText();
        String lieuDit = lieuDitField.getText();
        String indice = indiceComboBox.getValue();

        HashMap<Object, String> filter = new HashMap<>();
        this.initFilter(filter, lastName, firstName, date, time, lambertX, lambertY, commune, lieuDit, indice);
        String restriction = this.makeRestriction(filter);

        Data userData = (Data) this.homeButton.getScene().getUserData();
        Data data = new Data(userData.get(0), ANIMAL, restriction);
        ObservationChoiceController.setAllObservations(ANIMAL, restriction);
        Main.switchScene("ObservationChoice", this.validateButton, data);
    }

    private void initFilter(HashMap<Object,String> filter, String lastName, String firstName, LocalDate date, String time, String lambertX, String lambertY, String commune, String lieuDit, String indice){
        filter.put(lastName, "nom");
        filter.put(firstName, "prenom");
        filter.put(date, "dateObs");
        filter.put(time, "heureObs");
        filter.put(lambertX, "lieu_Lambert_X");
        filter.put(lambertY, "lieu_Lambert_Y");
        filter.put(commune, "commune");
        filter.put(lieuDit, "lieuDit");
        filter.put(indice, "indice");
    }

    private String makeRestriction(HashMap<Object, String> filter){
        String query = "";
        int nbRestriction = 0;

        for (Object o : filter.keySet()){
            if (!(o == null)){
                String value = o.toString();
                if (!value.equals("")){
                    if (nbRestriction > 0){
                        query = query + " AND " + filter.get(o) + " =\"" + value + "\"";
                    } else {
                        query = query + " WHERE " + filter.get(o) + " =\"" + value + "\"";
                    }
                    nbRestriction ++;
                }
            }
        }
        return query;
    }
}
