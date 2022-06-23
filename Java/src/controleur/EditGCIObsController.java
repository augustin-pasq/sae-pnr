package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import modele.donnee.ContenuNid;
import modele.donnee.UseDatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;


public class EditGCIObsController extends InteractivePage {
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
    private TextField leNidField;

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
     * obsGField
     */
    @FXML
    private TextField obsGField;


    private static ArrayList<String> observation;

    /**
     * Allows you to retrieve all the data of an observation by making a query in
     * the database
     * 
     * @param numObs Observation number
     */
    public static void setObs(int numObs) {
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromGCI WHERE ObsG = " + numObs + ";").get(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Allows you to initialise the attributes of the page, firstly the ComboBoxes
     * and then by initialising the fields with the data from the database
     *
     * @param url             the url of the page
     * @param ressourceBundle the resource bundle of the page
     */
    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = true;
        natureComboBox.setItems(natureList);
        nidObserveComboBox.setItems(nidObserveList);
        raisonComboBox.setItems(raisonList);
        nidProtegeComboBox.setItems(nidProtegeList);


        obsGField.setText(observation.get(0));

        ContenuNid saisieNature = null;
        String tmpNatureNid = observation.get(1);
        if (tmpNatureNid != null) {
            if (tmpNatureNid.equals("nid")){
                saisieNature = ContenuNid.NID_SEUL;
            } else if(tmpNatureNid.equals("oeuf")) {
                saisieNature = ContenuNid.OEUF;
            } else if (tmpNatureNid.equals("poussin")) {
                saisieNature = ContenuNid.POUSSIN;
            }
        }
        natureComboBox.getSelectionModel().select(saisieNature);

        nombreField.setText(observation.get(2));

        nidObserveComboBox.getSelectionModel().select(observation.get(3));

        leNidField.setText(observation.get(4));
        plageField.setText(observation.get(5));

        raisonComboBox.getSelectionModel().select(observation.get(6));

        nbEnvolField.setText(observation.get(7));

        nidProtegeComboBox.getSelectionModel().select(observation.get(8));

        bagueMaleField.setText(observation.get(9));
        bagueFemelleField.setText(observation.get(10));
        lastNameField.setText(observation.get(11));
        firstNameField.setText(observation.get(12));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate saisie = LocalDate.parse(observation.get(13), formatter);
        dateField.setValue(saisie);
        String[] time = observation.get(14).split(":");
        timeField.setText(time[0] + ":" + time[1]);

        lambertXField.setText(observation.get(15));
        lambertYField.setText(observation.get(16));

    }
    
    
    
    /**
     * Validate the data and add it to the database
     *
     * @param event the event that triggered the method
     */
    @FXML
    private void validate (ActionEvent event) {
        String lastName = lastNameField.getText().toUpperCase();
        String firstName = firstNameField.getText().toUpperCase();
        LocalDate date = dateField.getValue();
        String time = timeField.getText();
        String lambertX = lambertXField.getText();
        String lambertY = lambertYField.getText();
        String nombre = nombreField.getText();
        String id = obsGField.getText();
        String plage = plageField.getText();
        String nbEnvol = nbEnvolField.getText();
        String bagueMale = bagueMaleField.getText();
        String bagueFemelle = bagueFemelleField.getText();
        String leNid = leNidField.getText();
        String nature = null;
        if (natureComboBox.getValue() != null) {
            nature = natureComboBox.getValue().toString().split("_")[0];
            nature = nature.substring(0, 1).toUpperCase() + nature.substring(1);
        }
        String raisonArret = raisonComboBox.getValue() == null ? "" : raisonComboBox.getValue().toString();
        Integer nidObserve = null;
        if (nidObserveComboBox.getValue() != null) {
            nidObserve = nidObserveComboBox.getValue().equals("Oui") ? 1 : 0;
        }

        Integer nidProtege = null;
        if (nidProtegeComboBox.getValue() != null) {
            nidProtege = nidProtegeComboBox.getValue().equals("Oui") ? 1 : 0;
        }
        try {
            // Check the validity of the data
            checkFields(lastName, firstName, date, time, lambertX, lambertY, nombre,
                        leNid, plage, nbEnvol, bagueMale, bagueFemelle, nidObserve, nidProtege, raisonArret, nature);
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
            UseDatabase.updateQuery(String.format("UPDATE Nid_GCI SET nomPlage = '%s', raisonArretObservation = '%s' , nbEnvol = %d , protection = %d , bagueMale = '%s' ," +
                    "bagueFemelle = '%s' WHERE idNid = %d", plage, raisonArret, Integer.valueOf(nbEnvol), nidProtege, bagueMale, bagueFemelle, Integer.valueOf(leNid)));
            System.out.println(nature);

            Connection conn = UseDatabase.MySQLConnection();
            String q3 = "UPDATE Obs_GCI SET nature = ?, nombre = ?, leNid = ? WHERE obsG = ?";
            PreparedStatement p = conn.prepareStatement(q3);
            p.setString(1, nature);
            p.setInt(2, Integer.parseInt(nombre));
            p.setInt(3, Integer.parseInt(leNid));
            p.setInt(4, Integer.parseInt(id));
            p.executeUpdate();

            UseDatabase.updateQuery(String.format("UPDATE Observation SET dateObs = '%s', heureObs = '%s', lieu_Lambert_X = '%s', lieu_Lambert_Y = '%s' WHERE idObs = '%s'", date, time, lambertX, lambertY, idObs));
            System.out.println("30");
            UseDatabase.updateQuery(String.format("UPDATE AObserve set lobservateur = %d WHERE lobservateur = %d", idObservateur, idObs));
            System.out.println("31");
            Main.showPopup("Donnée mis a jour correctement", lastNameField, false);
            System.out.println("32");
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
     * Validate the data
     *
     * @param event the event that triggered the method
     */
    @FXML
    private void checkFields (String lastName, String firstName, LocalDate date, String time,
                              String lambertX, String lambertY, String nombre,
                              String idNid, String plage, String nbEnvol,
                              String bagueMale, String bagueFemelle, Integer nidObserve, Integer nidProtege, String raisonArret, String nature) throws IllegalArgumentException  {
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

        if (!nombre.matches("\\d+") && !nombre.isEmpty())
            throw new IllegalArgumentException("Le nombre d'individus ne peut pas être vide et doit être un entier");

        if (!idNid.matches("\\d+") && !idNid.isEmpty())
            throw new IllegalArgumentException("L'identifiant de nid ne peut pas être vide et doit être un entier");

        if (!plage.matches("[a-zA-Z\\-éèàçëê\\ ]+") && !plage.isEmpty())
            throw new IllegalArgumentException("La plage ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");

        if (!nbEnvol.matches("\\d+") && !nbEnvol.isEmpty())
            throw new IllegalArgumentException("Le nombre d'envol ne peut pas être vide et doit être un entier");

        if (!bagueMale.matches("[a-zA-Z\\d\\-/#]+") && !bagueMale.isEmpty())
            throw new IllegalArgumentException("La bague mâle ne peut pas être vide et ne doit contenir que des lettres, chiffres, -, / et #");

        if (!bagueFemelle.matches("[a-zA-Z\\d\\-/#]+") && !bagueFemelle.isEmpty())
            throw new IllegalArgumentException("La bague femelle ne peut pas être vide et ne doit contenir que des lettres, chiffres, -, / et #");

    }

     /**
     * Go back to the previous page
     *
     * @param event the event that triggered the action
     */
    public void goBack(ActionEvent event) {
        Main.goBack(event);
    }
}
