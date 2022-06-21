package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.donnee.EspeceChouette;
import modele.donnee.Sexe;
import modele.donnee.TypeObservation;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class DataChouetteController extends InteractivePage {

    ObservableList<EspeceChouette> especeList = FXCollections.observableArrayList(EspeceChouette.values());
    ObservableList<Sexe> sexeList = FXCollections.observableArrayList(Sexe.values());
    ObservableList<String> protocoleList = FXCollections.observableArrayList("Oui", "Non");
    ObservableList<TypeObservation> typeObservationList = FXCollections.observableArrayList(TypeObservation.values());

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
    private ComboBox<EspeceChouette> especeComboBox;
    @FXML
    private ComboBox<String> protocoleComboBox;
    @FXML
    private ComboBox<TypeObservation> typeObservationComboBox;
    @FXML
    private ComboBox<Sexe> sexeComboBox;
    @FXML
    private Button validateButton;

    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = true;
        especeComboBox.setItems(especeList);
        sexeComboBox.setItems(sexeList);
        protocoleComboBox.setItems(protocoleList);
        typeObservationComboBox.setItems(typeObservationList);
    }

    @FXML
    private void validate(ActionEvent event) {
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        LocalDate date = dateField.getValue();
        String time = timeField.getText();
        String lambertX = lambertXField.getText();
        String lambertY = lambertYField.getText();
        EspeceChouette espece = especeComboBox.getValue();
        String protocole = protocoleComboBox.getValue();
        TypeObservation typeObservation = typeObservationComboBox.getValue();
        Sexe sexe = sexeComboBox.getValue();

        try {
            checkFields(lastName, firstName, time, lambertX, lambertY);
            Date sqlDate = Date.valueOf(date);
            Time sqlTime = Time.valueOf(time);
            String query = "INSERT INTO Obs_Chouette (nom, prenom, date, time, lambert_x, lambert_y, espece, protocole, type_observation, sexe) VALUES ('" + lastName + "', '" + firstName + "', '" + sqlDate + "', '" + sqlTime + "', '" + lambertX + "', '" + lambertY + "', '" + espece + "', '" + protocole + "', '" + typeObservation + "', '" + sexe + "')";
            System.out.println(query);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private void checkFields(String lastName, String firstName, String time, String lambertX, String lambertY) throws IllegalArgumentException {
        if (!lastName.matches("[a-zA-Z\\-éèàç\\ ]+"))
            throw new IllegalArgumentException("Le nom ne doit contenir que des lettres, espaces et tirets");

        if (!firstName.matches("[a-zA-Z\\-éèàç\\ ]+"))
            throw new IllegalArgumentException("Le prénom ne doit contenir que des lettres, espaces et tirets");

        if (!time.matches("\\d{2}:\\d{2}"))
            throw new IllegalArgumentException("Le temps doit être au format hh:mm");

        if (!lambertX.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La coordonnée Lambert X doit être un nombre");

        if (!lambertY.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La coordonnée Lambert Y doit être un nombre");
    }
}
