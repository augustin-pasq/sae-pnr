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
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Controller for the DataLoutre page
 *x
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

    private static ArrayList<String> observation;

    public static void setObs(int numObs) {
        observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromLoutre WHERE ObsL = " + numObs + ";").get(1);
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

        lastNameField.setText(observation.get(4));
        firstNameField.setText(observation.get(5));
        LocalDate saisie = LocalDate.parse(observation.get(6));
        dateField = new DatePicker(saisie);
        timeField.setText(observation.get(7));
        lambertXField.setText(observation.get(8));
        lambertYField.setText(observation.get(9));
        communeField.setText(observation.get(1));
        lieuDitField.setText(observation.get(2));
        indiceComboBox.getSelectionModel().select(observation.get(3));
        validateButton.setText(observation.get(4));
        indiceComboBox.setItems(indiceList);
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
            // Generate a unique id for the observation
            final Integer idObs = Math.abs(UUID.randomUUID().hashCode());

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

            // Get a connection to the database for the prepared statements
            Connection conn = UseDatabase.MySQLConnection();

            // Insert the coordinates in the database. If they already exist, the SQLIntegrityConstraintViolationException is caught by useDatabase and ignored
            UseDatabase.updateQuery(String.format("INSERT INTO Lieu (coord_Lambert_X, coord_Lambert_Y) VALUES ('%s', '%s')",
                    lambertX, lambertY));

            // Format the time as an object
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(time, formatter);

            // Insert the observation in the database with a prepared statement (mostly because of the time)
            String q = "INSERT INTO Observation (idObs, lieu_Lambert_X, lieu_Lambert_Y, dateObs, heureObs) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prep = conn.prepareStatement(q);
            prep.setInt(1, idObs);
            prep.setString(2, lambertX);
            prep.setString(3, lambertY);
            prep.setDate(4, Date.valueOf(date));
            prep.setTime(5, Time.valueOf(localTime));
            prep.executeUpdate();

            // Insert the link between the observation and the observer in the database
            UseDatabase.updateQuery(String.format("INSERT INTO AObserve (lobservation, lobservateur) VALUES ('%s', '%s')",
                    idObs, idObservateur));

            // Insert the loutre linked to the observation in the database
            UseDatabase.updateQuery(String.format("INSERT INTO Obs_Loutre (obsL, commune, lieuDit, indice) VALUES ('%s', '%s', '%s', '%s')",
                    idObs, commune, lieuDit, indice));

            // If no exception has been thrown, the observation has been successfully added
            Main.showPopup("Observation enregistrée correctement", event, false);

            // Close the connections
            prep.close();
            conn.close();
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
