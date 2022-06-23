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
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Controller for the DataLoutre page
 * x
 *
 * @author Groupe SAE PNR 1D1
 */
public class EditLoutreObsController extends InteractivePage {

    /**
     * The list of all indice prospection
     */
    ObservableList<String> indiceList = FXCollections.observableArrayList("Positif", "Négatif", "Non prospection");

    /**
     * The surname of the observer
     */
    @FXML
    private TextField lastNameField;
    /**
     * The name of the observer
     */
    @FXML
    private TextField firstNameField;
    /**
     * The date of the observation
     */
    @FXML
    private DatePicker dateField;
    /**
     * The time of the observation
     */
    @FXML
    private TextField timeField;
    /**
     * The X coordinate of the observation
     */
    @FXML
    private TextField lambertXField;
    /**
     * The Y coordinate of the observation
     */
    @FXML
    private TextField lambertYField;
    /**
     * The town of the observation
     */
    @FXML
    private TextField communeField;
    /**
     * The locality of the observation
     */
    @FXML
    private TextField lieuDitField;
    /**
     * The indice of the observation
     */
    @FXML
    private ComboBox<String> indiceComboBox;
    /**
     * The button to validate the observation
     */
    @FXML
    private Button validateButton;

    private static int idObs;

    private static ArrayList<String> observation;

    public static void setObs(int numObs) {
        idObs = numObs;
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromLoutre WHERE ObsL = " + numObs + ";").get(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void goBack(ActionEvent event) {
        Main.goBack(event);
    }

    /**
     * Initializes the controller class.
     *
     * @param url             the url of the page
     * @param ressourceBundle the resource bundle of the page
     */
    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);

        System.out.println(observation.toString());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate saisie = LocalDate.parse(observation.get(6), formatter);
        dateField.setValue(saisie);
        String[] time = observation.get(7).split(":");
        timeField.setText(time[0] + ":" + time[1]);
        lambertXField.setText(observation.get(8));
        lambertYField.setText(observation.get(9));
        communeField.setText(observation.get(1));
        lieuDitField.setText(observation.get(2));
        indiceComboBox.getSelectionModel().select(observation.get(3));
        indiceComboBox.setItems(indiceList);
        lastNameField.setText(observation.get(4));
        firstNameField.setText(observation.get(5));
    }

    /**
     * Validate the data and insert it into the database
     *
     * @param event The event that triggered the method
     */
    @FXML
    public void validate(ActionEvent event) {
        // Get the data from the fields
        String lastName = lastNameField.getText().toUpperCase();
        String firstName = firstNameField.getText().toUpperCase();
        LocalDate date = dateField.getValue();
        String time = timeField.getText();
        String lambertX = lambertXField.getText();
        String lambertY = lambertYField.getText();
        String commune = communeField.getText();
        String lieuDit = lieuDitField.getText();
        String indice = indiceComboBox.getValue();

        try {
            // Check the validity of the data
            checkFields(lastName, firstName, date, time, lambertX, lambertY, commune, lieuDit);

            // Try to get the observer's id if it exists
            ArrayList<ArrayList<String>> observateur = UseDatabase.selectQuery(String.format("SELECT idObservateur FROM Observateur WHERE nom = '%s' AND prenom = '%s' LIMIT 1", lastName, firstName));
            int idObservateur;
            if (observateur.size() == 1) {
                // If the observer doesn't exist, create it, with a unique id
                idObservateur = Math.abs(UUID.randomUUID().hashCode());
                UseDatabase.updateQuery(String.format("INSERT INTO Observateur (idObservateur, nom, prenom) VALUES (%d, '%s', '%s')",
                        idObservateur, lastName, firstName));
            } else {
                // If the observer exists, get its id
                idObservateur = Integer.parseInt(observateur.get(1).get(0));
            }

            UseDatabase.updateQuery(String.format("UPDATE Obs_Loutre SET commune = '%s', lieuDit = '%s', indice = '%s' WHERE ObsL = '%s'", commune, lieuDit, indice, idObs));
            UseDatabase.updateQuery(String.format("UPDATE Observation SET dateObs = '%s', heureObs = '%s', lieu_Lambert_X = '%s', lieu_Lambert_Y = '%s' WHERE idObs = '%s'", date, time, lambertX, lambertY, idObs));
            UseDatabase.updateQuery(String.format("UPDATE AObserve set lobservateur = %d WHERE lobservateur = %d", idObservateur, idObs));
            Main.showPopup("Données mises à jour correctement", lastNameField, false);
        } catch (IllegalArgumentException e) {
            // If one of the fields is invalid, show a popup with the error message
            Main.showPopup(e.getMessage(), event, true);
        } catch (SQLException e) {
            // If an SQL exception has been thrown, show a popup with an error message
            Main.showPopup("Une erreur est survenue au moment de l'enregistrement des données", event, true);
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            Main.showPopup("Merci de remplir tous les champs", event, true);
            e.printStackTrace();
        } catch (Exception e) {
            // Catch all other exceptions and show a popup with a generic error message
            Main.showPopup("Une erreur inconnue est survenue", event, true);
            e.printStackTrace();
        }
    }

    /**
     * Check if all fields are valid
     *
     * @param lastName  last name of the observer
     * @param firstName first name of the observer
     * @param date      date of the observation
     * @param time      time of the observation
     * @param lambertX  lambert X coordinate of the observation
     * @param lambertY  lambert Y coordinate of the observation
     * @throws IllegalArgumentException if one of the fields is invalid, with a detailed message
     */
    private void checkFields(@NotNull String lastName, @NotNull String firstName, LocalDate date, String time, @NotNull String lambertX, @NotNull String lambertY, @NotNull String commune, @NotNull String lieuDit) throws IllegalArgumentException {
        if (!lastName.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException("Le nom ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");

        if (!firstName.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException("Le prénom ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");

        if (date == null)
            throw new IllegalArgumentException("La date est obligatoire");

        if (time == null)
            throw new IllegalArgumentException("L'heure est obligatoire");
        if (!time.matches("\\d{2}:\\d{2}"))
            throw new IllegalArgumentException("L'heure doit être au format hh:mm");
        String[] timeSplit = time.split(":");
        int h = Integer.parseInt(timeSplit[0]);
        int m = Integer.parseInt(timeSplit[1]);
        if (!(0 <= h && h < 24 && 0 <= m && m < 60)) {
            throw new IllegalArgumentException("L'heure doit être valide");
        }

        if (!lambertX.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La coordonnée ne peut pas être vide et Lambert X doit être un nombre");

        if (!lambertY.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La coordonnée ne peut pas être vide et Lambert Y doit être un nombre");

        if (!commune.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException("La commune ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");

        if (!lieuDit.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException("Le lieu ne peut pas être vide et dit ne doit contenir que des lettres, espaces et tirets");
    }
}
