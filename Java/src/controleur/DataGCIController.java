package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.donnee.ContenuNid;
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
 * Controller for the DataGCI page
 *
 * @author Groupe SAE PNR 1D1
 */
public class DataGCIController extends InteractivePage {
    /**
     * The list of nest content possibilities
     */
    ObservableList<ContenuNid> natureList = FXCollections.observableArrayList(ContenuNid.values());
    /**
     * The list of possibilities to whether the nest is observed or not
     */
    ObservableList<String> nidObserveList = FXCollections.observableArrayList("Oui", "Non");
    /**
     * List of possibilities of reasons to stop the observation
     */
    ObservableList<String> raisonList = FXCollections.observableArrayList("Envol", "Inconnu", "Maree", "Pietinement",
            "Prédation");
    /**
     * List of possibilities of reasons to whether
     */
    ObservableList<String> nidProtegeList = FXCollections.observableArrayList("Oui", "Non");

    /**
     * The last naame of the observer
     */
    @FXML
    private TextField lastNameField;

    /**
     * The first name of the observer
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
     * The nature of the observation
     */
    @FXML
    private ComboBox<ContenuNid> natureComboBox;

    /**
     * The amount of items (nature attribute) observed
     */
    @FXML
    private TextField nombreField;

    /**
     * Indicates if the nid is already observed
     */
    @FXML
    private ComboBox<String> nidObserveComboBox;

    /**
     * The ID of the nest
     */
    @FXML
    private TextField idNidField;

    /**
     * The name of the beach
     */
    @FXML
    private TextField plageField;

    /**
     * The reason why the observation was stopped
     */
    @FXML
    private ComboBox<String> raisonComboBox;

    /**
     * The amount of flight in the nest
     */
    @FXML
    private TextField nbEnvolField;

    /**
     * Indicates if the nest is protected
     */
    @FXML
    private ComboBox<String> nidProtegeComboBox;

    /**
     * The code of the male ring
     */
    @FXML
    private TextField bagueMaleField;

    /**
     * The code of the female ring
     */
    @FXML
    private TextField bagueFemelleField;

    /**
     * Initializes the scene
     *
     * @param url             the url of the page
     * @param ressourceBundle the resource bundle of the page
     */
    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        natureComboBox.setItems(natureList);
        nidObserveComboBox.setItems(nidObserveList);
        raisonComboBox.setItems(raisonList);
        nidProtegeComboBox.setItems(nidProtegeList);
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
        String nature = null;
        if (natureComboBox.getValue() != null) {
            nature = natureComboBox.getValue().toString().split("_")[0];
            nature = nature.substring(0, 1).toUpperCase() + nature.substring(1);
        }

        String nombre = nombreField.getText();
        Integer presentMaisNonObs = null;
        if (nidObserveComboBox.getValue() != null)
            presentMaisNonObs = nidObserveComboBox.getValue().equals("Oui") ? 1 : 0;
        String idNid = idNidField.getText();
        String plage = plageField.getText();
        String raison = raisonComboBox.getValue();
        String nbEnvol = nbEnvolField.getText();
        Integer nidProtege = null;
        if (nidProtegeComboBox.getValue() != null)
            nidProtege = nidProtegeComboBox.getValue().equals("Oui") ? 1 : 0;
        String bagueMale = bagueMaleField.getText();
        String bagueFemelle = bagueFemelleField.getText();

        try {
            // Check if the data is valid
            checkFields(lastName, firstName, date, time, lambertX, lambertY, nombre, idNid, plage, nbEnvol, bagueMale,
                    bagueFemelle);
            // Generate a unique id for the new observation
            final int idObs = Math.abs(UUID.randomUUID().hashCode());

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
            prep.setDate(4, Date.valueOf(date));
            prep.setTime(5, Time.valueOf(localTime));
            prep.executeUpdate();

            UseDatabase.updateQuery(String.format("INSERT INTO AObserve (lobservation, lobservateur) VALUES (%d, '%s')",
                    idObs, idObservateur));

            System.out.println("raison: " + raison);
            ArrayList<ArrayList<String>> nid = UseDatabase.selectQuery(
                    String.format(
                            "SELECT idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle FROM Nid_GCI WHERE idNid = '%s' LIMIT 1",
                            idNid));
            if (nid.size() == 1) {
                String q2 = "INSERT INTO Nid_GCI (idNid, nomPlage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle) VALUES (?, ?, ?, ?, ?, ?, ?)";
                System.out.println("q2: " + q2);
                PreparedStatement p = conn.prepareStatement(q2);
                p.setInt(1, Integer.parseInt(idNid));
                p.setString(2, plage);
                p.setString(3, raison);
                p.setInt(4, Integer.parseInt(nbEnvol));
                p.setObject(5, nidProtege);
                p.setString(6, bagueMale);
                p.setString(7, bagueFemelle);
                p.executeUpdate();
            } else {
                ArrayList<String> nidData = nid.get(1);
                if (!(nidData.get(1).equals(plage) && nidData.get(2).equals(raison) && nidData.get(3).equals(nbEnvol) &&
                        nidData.get(4).equals(nidProtege) && nidData.get(5).equals(bagueMale) &&
                        nidData.get(6).equals(bagueFemelle))) {
                    throw new IllegalArgumentException(
                            "Les données correspondant à cet identifiant de nid ne correspondent pas aux données entrées");
                }
            }

            String q3 = "INSERT INTO Obs_GCI (obsG, leNid, nature, nombre, presentMaisNonObs) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement p = conn.prepareStatement(q3);
            p.setInt(1, idObs);
            p.setInt(2, Integer.parseInt(idNid));
            p.setString(3, nature);
            p.setInt(4, Integer.parseInt(nombre));
            p.setObject(5, presentMaisNonObs);
            p.executeUpdate();

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
     * @param lastName     last name of the observer
     * @param firstName    first name of the observer
     * @param date         date of the observation
     * @param time         time of the observation
     * @param lambertX     lambert X coordinate of the observation
     * @param lambertY     lambert Y coordinate of the observation
     * @param nombre       number of birds observed
     * @param idNid        id of the nid
     * @param plage        name of the beach
     * @param nbEnvol      number of birds that flew away
     * @param bagueMale    name of the male ring
     * @param bagueFemelle name of the female ring
     * @throws IllegalArgumentException if one of the fields is invalid, with a
     *                                  detailed message
     */
    private void checkFields(@NotNull String lastName, @NotNull String firstName, LocalDate date, String time,
            @NotNull String lambertX, @NotNull String lambertY, @NotNull String nombre,
            @NotNull String idNid, @NotNull String plage, @NotNull String nbEnvol,
            @NotNull String bagueMale, @NotNull String bagueFemelle) throws IllegalArgumentException {
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
            throw new IllegalArgumentException("La coordonnée Lambert X ne peut pas être vide et doit être un nombre");

        if (!lambertY.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La coordonnée Lambert Y ne peut pas être vide et doit être un nombre");

        if (!nombre.matches("\\d+"))
            throw new IllegalArgumentException("Le nombre d'individus ne peut pas être vide et doit être un entier");

        if (!idNid.matches("\\d+"))
            throw new IllegalArgumentException("L'identifiant de nid ne peut pas être vide et doit être un entier");

        if (!plage.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException(
                    "La plage ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");

        if (!nbEnvol.matches("\\d+"))
            throw new IllegalArgumentException("Le nombre d'envol ne peut pas être vide et doit être un entier");

        if (!bagueMale.matches("[a-zA-Z\\d\\-/#]+"))
            throw new IllegalArgumentException(
                    "La bague mâle ne peut pas être vide et ne doit contenir que des lettres, chiffres, -, / et #");

        if (!bagueFemelle.matches("[a-zA-Z\\d\\-/#]+"))
            throw new IllegalArgumentException(
                    "La bague femelle ne peut pas être vide et ne doit contenir que des lettres, chiffres, -, / et #");
    }
}
