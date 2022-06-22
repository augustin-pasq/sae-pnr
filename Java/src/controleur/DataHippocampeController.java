package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.donnee.EspeceHippocampe;
import modele.donnee.Peche;
import modele.donnee.Sexe;
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


public class DataHippocampeController extends InteractivePage {

    ObservableList<EspeceHippocampe> especeList = FXCollections.observableArrayList(EspeceHippocampe.values());
    ObservableList<Sexe> sexeList = FXCollections.observableArrayList(Sexe.values());
    ObservableList<String> gestantList = FXCollections.observableArrayList("Gestant", "Non gestant");
    ObservableList<Peche> typePecheList = FXCollections.observableArrayList(Peche.values());
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
    private ComboBox<EspeceHippocampe> especeComboBox;
    @FXML
    private ComboBox<Sexe> sexeComboBox;
    @FXML
    private TextField temperatureField;
    @FXML
    private ComboBox<Peche> typePecheComboBox;
    @FXML
    private TextField sizeField;
    @FXML
    private ComboBox<String> gestantComboBox;

    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        especeComboBox.setItems(especeList);
        sexeComboBox.setItems(sexeList);
        gestantComboBox.setItems(gestantList);
        typePecheComboBox.setItems(typePecheList);

    }

    @FXML
    public void validate(ActionEvent event) {
        // get all the fields
        String lastName = lastNameField.getText().toUpperCase();
        String firstName = firstNameField.getText().toUpperCase();
        LocalDate date = dateField.getValue();
        String time = timeField.getText();
        String lambertX = lambertXField.getText();
        String lambertY = lambertYField.getText();
        String espece = null;
        // format the species as it is required in the database
        if (especeComboBox.getValue() != null) {
            espece = especeComboBox.getValue().toString();
            StringBuilder especeSb = new StringBuilder();
            for (String s : espece.split("")) {
                if (s.equals("_"))
                    especeSb.append(' ');
                else if (s.equals("H"))
                    especeSb.append('H');
                else
                    especeSb.append(s.toLowerCase());
            }
            espece = especeSb.toString();
        }

        String sexe = null;
        // format the sexe as it is required in the database
        if (sexeComboBox.getValue() != null) {
            sexe = sexeComboBox.getValue().toString().charAt(0) +
                    sexeComboBox.getValue().toString().substring(1).toLowerCase();
        }

        String temperature = temperatureField.getText();
        // format the type de peche as it is required in the database
        String typePeche = null;
        if (typePecheComboBox.getValue() != null) {
            String tmp = typePecheComboBox.getValue().toString();
            StringBuilder typePecheSb = new StringBuilder();
            for (int i = 0; i < tmp.length(); i++) {
                if (tmp.charAt(i) == '_')
                    typePecheSb.append(tmp.charAt(++i));
                else if (tmp.charAt(i) == 'P')
                    typePecheSb.append(tmp.charAt(i));
                else
                    typePecheSb.append(tmp.substring(i, i + 1).toLowerCase());
            }
            typePeche = tmp.equals("NON_RENSEIGNE") ? null : typePecheSb.toString();
        }

        String size = sizeField.getText();
        int gestant = 0; // 0 if not gestant, 1 if gestant, as it is required in the database
        if (gestantComboBox.getValue() != null) {
            gestant = gestantComboBox.getValue().equals("Gestant") ? 1 : 0;
        }

        try {
            // check the validity of the fields
            checkFields(lastName, firstName, date, time, lambertX, lambertY, temperature, size);
            // Generate a unique id for the new observation
            final int idObs = Math.abs(UUID.randomUUID().hashCode());

            // try to get the observer's id if it exists
            ArrayList<ArrayList<String>> observateur = UseDatabase.selectQuery(String.format("SELECT idObservateur FROM Observateur WHERE nom = '%s' AND prenom = '%s' LIMIT 1", lastName, firstName));
            int idObservateur;
            if (observateur.size() == 1) {
                // if it doesn't exist, create it with a unique id
                idObservateur = Math.abs(UUID.randomUUID().hashCode());
                UseDatabase.updateQuery(String.format("INSERT INTO Observateur (idObservateur, nom, prenom) VALUES (%d, '%s', '%s')",
                        idObservateur, lastName, firstName));
            } else {
                // if it exists, get its id
                idObservateur = Integer.parseInt(observateur.get(1).get(0));
            }

            // Get a new connection to the database for the prepared statements
            Connection conn = UseDatabase.MySQLConnection();

            // Insert the coordinates in the database. If they already exist, the SQLIntegrityConstraintViolationException is caught by useDatabase and ignored
            UseDatabase.updateQuery(String.format("INSERT INTO Lieu (coord_Lambert_X, coord_Lambert_Y) VALUES ('%s', '%s')",
                    lambertX, lambertY));

            // Format the time to an object
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(time, formatter);

            // Insert the observation in the database with a prepared statement (mostly because of the time)
            String q = "INSERT INTO Observation (idObs, lieu_Lambert_X, lieu_Lambert_Y, dateObs, heureObs) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement prep = conn.prepareStatement(q);
            prep.setInt(1, idObs);
            prep.setString(2, lambertX);
            prep.setString(3, lambertY);
            prep.setDate(4, java.sql.Date.valueOf(date));
            prep.setTime(5, Time.valueOf(localTime));
            prep.executeUpdate();

            // Insert the link between the observation and the observer in the database
            UseDatabase.updateQuery(String.format("INSERT INTO AObserve (lobservation, lobservateur) VALUES ('%s', '%s')",
                    idObs, idObservateur));

            // Insert the hippocampe linked to the observation in the database with a prepared statement
            String q2 = "INSERT INTO Obs_Hippocampe (obsH, espece, sexe, temperatureEau, typePeche, taille, gestant) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prep2 = conn.prepareStatement(q2);
            prep2.setInt(1, idObs);
            prep2.setString(2, espece);
            prep2.setString(3, sexe);
            prep2.setString(4, temperature);
            prep2.setString(5, typePeche);
            prep2.setString(6, size);
            prep2.setInt(7, gestant);
            prep2.executeUpdate();

            // If no exception has been thrown, the observation has been successfully added
            Main.showPopup("Observation enregistrée correctement", event, false);

            // Close the connections
            prep.close();
            conn.close();
        } catch (IllegalArgumentException e) {
            // If one of the fields is not valid, show a popup with the error message
            Main.showPopup(e.getMessage(), event, true);
        } catch (SQLException e) {
            // If an SQL exception has been thrown, show a popup with an error message
            Main.showPopup("Une erreur est survenue au moment de l'enregistrement des données", event, true);
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            Main.showPopup("Merci de remplir tous les champs", event, true);
            e.printStackTrace();
        } catch (Exception e) {
            // Catch all other exceptions, show a popup with a generic error message
            Main.showPopup("Une erreur inconnue est survenue", event, true);
            e.printStackTrace();
        }
    }

    /**
     * Check if all fields are valid
     * @param lastName last name of the observer
     * @param firstName first name of the observer
     * @param date date of the observation
     * @param time time of the observation
     * @param lambertX lambert X coordinate of the observation
     * @param lambertY lambert Y coordinate of the observation
     * @throws IllegalArgumentException if one of the fields is invalid, with a detailed message
     */
    private void checkFields(@NotNull String lastName, @NotNull String firstName, LocalDate date, String time, @NotNull String lambertX, @NotNull String lambertY, @NotNull String temperature, @NotNull String size) throws IllegalArgumentException {
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
            throw new IllegalArgumentException("La coordonnée Lambert X doit être un nombre");

        if (!lambertY.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La coordonnée Lambert Y doit être un nombre");

        if (!temperature.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La température doit être un nombre");

        if (!size.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La taille doit être un nombre");
    }
}
