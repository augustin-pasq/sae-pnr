package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import modele.donnee.ContenuNid;
import modele.donnee.UseDatabase;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;


public class EditGCIObsController extends InteractivePage {

    private static ArrayList<String> observation;

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
    private TextField obsGField;
    @FXML
    private TextField leNidField;
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

    private static int idObs;

    public static void setObs(int numObs) {
        idObs = numObs;
        try {
            observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromGCI WHERE ObsG = " + numObs + ";").get(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
        if (tmpNatureNid.equals("nid")){
            saisieNature = ContenuNid.NID_SEUL;
        } else if(tmpNatureNid.equals("oeuf")) {
            saisieNature = ContenuNid.OEUF;
        } else if (tmpNatureNid.equals("poussin")) {
            saisieNature = ContenuNid.POUSSIN;
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
        timeField.setText(observation.get(14));

        lambertXField.setText(observation.get(15));
        lambertYField.setText(observation.get(16));

    }
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
        Integer leNid = Integer.parseInt(leNidField.getText());
        String nature = natureComboBox.getValue() == null ? "" : natureComboBox.getValue().toString();
        String raisonArret = raisonComboBox.getValue() == null ? "" : raisonComboBox.getValue().toString();

        Integer nidObservé = null;
        if (nidObserveComboBox.getValue() != null) {
            nidObservé = nidObserveComboBox.getValue().equals("Oui") ? 1 : 0;
        }

        Integer nidProtege = null;
        if (nidProtegeComboBox.getValue() != null) {
            nidProtege = nidProtegeComboBox.getValue().equals("Oui") ? 1 : 0;
        }
        try {
            // Check the validity of the data
            //checkFields(lastName, firstName, date, time, lambertX, lambertY, commune, lieuDit);

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
                    "bagueFemelle = '%s' WHERE idNid = %d'", plage, raisonArret, nbEnvol, nidProtege, bagueMale, bagueFemelle, leNid));

            UseDatabase.updateQuery(String.format("UPDATE Obs_GCI SET nature = '%s', nombre = %d, leNid = '%s'", nature, nombre, leNid));

            UseDatabase.updateQuery(String.format("UPDATE Observation SET dateObs = '%s', heureObs = '%s', lieu_Lambert_X = '%s', lieu_Lambert_Y = '%s' WHERE idObs = '%s'", date, time, lambertX, lambertY, idObs));

            UseDatabase.updateQuery(String.format("UPDATE AObserve set lobservateur = %d WHERE lobservateur = %d", idObservateur, idObs));
            Main.showPopup("Donnée mis a jour correctement", lastNameField, false);
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
    private void checkFields (String lastName, String firstName, LocalDate date, String time,
                              String lambertX, String lambertY, String nombre,
                              String idNid, String plage, String nbEnvol,
                              String bagueMale, String bagueFemelle, String nidObservé) throws IllegalArgumentException  {
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
    public void goBack(ActionEvent event) {
        Main.goBack(event);
    }
}
