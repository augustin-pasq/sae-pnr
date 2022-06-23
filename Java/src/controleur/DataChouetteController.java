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
import modele.donnee.UseDatabase;
import org.jetbrains.annotations.NotNull;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Controller for the DataChouette page
 *
 * @author Groupe SAE PNR 1D1
 */
public class DataChouetteController extends InteractivePage {

    /**
     * The list of owl species
     */
    ObservableList<EspeceChouette> especeList = FXCollections.observableArrayList(EspeceChouette.values());
    /**
     * The list of possible gender
     */
    ObservableList<Sexe> sexeList = FXCollections.observableArrayList(Sexe.values());
    /**
     * The list of option to whether the protocol is followed or not
     */
    ObservableList<String> protocoleList = FXCollections.observableArrayList("Oui", "Non");
    /**
     * The list of types of observations
     */
    ObservableList<TypeObservation> typeObservationList = FXCollections.observableArrayList(TypeObservation.values());

    /**
     * The first name of the observer
     */
    @FXML
    private TextField lastNameField;

    /**
     * The last name of the observer
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
     * The X Lambert93 coordinates of the observation
     */
    @FXML
    private TextField lambertXField;

    /**
     * The Y Lambert93 coordinates of the observation
     */
    @FXML
    private TextField lambertYField;

    /**
     * The species of the observation
     */
    @FXML
    private ComboBox<EspeceChouette> especeComboBox;

    /**
     * Indicates if the observation protocol is followed
     */
    @FXML
    private ComboBox<String> protocoleComboBox;

    /**
     * The type of the observation
     */
    @FXML
    private ComboBox<TypeObservation> typeObservationComboBox;

    /**
     * The gender of the owl
     */
    @FXML
    private ComboBox<Sexe> sexeComboBox;

    /**
     * The button to validate the insert
     */
    @FXML
    private Button validateButton;

    /**
     * Initialize the scene
     *
     * @param url             the url of the page
     * @param ressourceBundle the resource bundle of the page
     */
    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = true;
        especeComboBox.setItems(especeList);
        sexeComboBox.setItems(sexeList);
        protocoleComboBox.setItems(protocoleList);
        typeObservationComboBox.setItems(typeObservationList);
    }

    /**
     * Validate the data and add it to the database
     *
     * @param event the event that triggered the method
     */
    @FXML
    private void validate(ActionEvent event) {
        String lastName = lastNameField.getText().toUpperCase();
        String firstName = firstNameField.getText().toUpperCase();
        LocalDate date = dateField.getValue();
        String time = timeField.getText();
        String lambertX = lambertXField.getText();
        String lambertY = lambertYField.getText();
        String espece = especeComboBox.getValue() == null ? "" : especeComboBox.getValue().toString();
        Integer protocole = null;
        if (protocoleComboBox.getValue() != null) {
            protocole = protocoleComboBox.getValue().equals("Oui") ? 1 : 0;
        }
        String typeObservation = typeObservationComboBox.getValue() == null ? null
                : typeObservationComboBox.getValue().toString().replace("_", " ET ").replace("VISUELLE", "VISUEL");;
        String sexe = sexeComboBox.getValue() == null ? "INCONNU" : sexeComboBox.getValue().toString();

        try {
            checkFields(lastName, firstName, date, time, lambertX, lambertY);
            final Integer idObs = Math.abs(UUID.randomUUID().hashCode());

            ArrayList<ArrayList<String>> observateur = UseDatabase.selectQuery(
                    String.format("SELECT idObservateur FROM Observateur WHERE nom = '%s' AND prenom = '%s' LIMIT 1",
                            lastName, firstName));
            int idObservateur;
            if (observateur.size() == 1) {
                idObservateur = Math.abs(UUID.randomUUID().hashCode());
                UseDatabase.updateQuery(
                        String.format("INSERT INTO Observateur (idObservateur, nom, prenom) VALUES (%d, '%s', '%s')",
                                idObservateur, lastName, firstName));
            } else {
                idObservateur = Integer.parseInt(observateur.get(1).get(0));
            }

            Connection conn = UseDatabase.MySQLConnection();

            UseDatabase.updateQuery(
                    String.format("INSERT INTO Lieu (coord_Lambert_X, coord_Lambert_Y) VALUES ('%s', '%s')",
                            lambertX, lambertY));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(time, formatter);

            String q = "INSERT INTO Observation (idObs, lieu_Lambert_X, lieu_Lambert_Y, dateObs, heureObs) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prep = conn.prepareStatement(q);
            prep.setInt(1, idObs);
            prep.setString(2, lambertX);
            prep.setString(3, lambertY);
            prep.setDate(4, java.sql.Date.valueOf(date));
            prep.setTime(5, Time.valueOf(localTime));
            prep.executeUpdate();

            UseDatabase
                    .updateQuery(String.format("INSERT INTO AObserve (lobservation, lobservateur) VALUES ('%s', '%s')",
                            idObs, idObservateur));

            String numIndividu = UUID.randomUUID().toString().replace("-", "");
            UseDatabase.updateQuery(
                    String.format("INSERT INTO Chouette (numIndividu, espece, sexe) VALUES ('%s', '%s', '%s')",
                            numIndividu, espece, sexe));

            String q2 = "INSERT INTO Obs_Chouette (numObs, leNumIndividu, typeObs, protocole) VALUES (?, ?, ?, ?)";
            PreparedStatement prep2 = conn.prepareStatement(q2);
            prep2.setInt(1, idObs);
            prep2.setString(2, numIndividu);
            prep2.setString(3, typeObservation);
            prep2.setObject(4, protocole);
            prep2.executeUpdate();

            Main.showPopup("Observation enregistrée correctement", event, false);

            prep.close();
            conn.close();
        } catch (IllegalArgumentException e) {
            Main.showPopup(e.getMessage(), event, true);
        } catch (SQLException e) {
            Main.showPopup("Une erreur est survenue au moment de l'enregistrement des données", event, true);
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            Main.showPopup("Merci de remplir tous les champs", event, true);
            e.printStackTrace();
        } catch (Exception e) {
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
     * @throws IllegalArgumentException if one of the fields is invalid, with a
     *                                  detailed message
     */
    private void checkFields(@NotNull String lastName, @NotNull String firstName, LocalDate date, String time,
            @NotNull String lambertX, @NotNull String lambertY) throws IllegalArgumentException {
        if (!lastName.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException(
                    "Le nom ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");

        if (!firstName.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException(
                    "Le prénom ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");

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
        float lambertXInt = Float.parseFloat(lambertX);
        if (0 > lambertXInt || lambertXInt > 1300000)
            throw new IllegalArgumentException("La coordonnée Lambert X doit être comprise entre 0 et 1300000");

        if (!lambertY.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La coordonnée ne peut pas être vide et Lambert Y doit être un nombre");
        float lambertYInt = Float.parseFloat(lambertY);
        if (lambertYInt < 6000000 || lambertYInt > 7200000)
            throw new IllegalArgumentException("La coordonnée Lambert Y doit être comprise entre 6000000 et 7200000");
    }
}
