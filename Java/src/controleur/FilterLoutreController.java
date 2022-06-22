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


public class FilterLoutreController extends InteractivePage {

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
    public void validate(ActionEvent event) {
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
            HashMap<Object, String> filter = new HashMap<>();
            this.initFilter(filter, lastName, firstName, date, time, lambertX, lambertY, commune, lieuDit, indice);
            String restriction = this.makeRestriction(filter);
            System.out.println(restriction);
            checkFields(lastName, firstName, date, time, lambertX, lambertY, commune, lieuDit);
            final Integer idObs = Math.abs(UUID.randomUUID().hashCode());

            ArrayList<ArrayList<String>> observateur = UseDatabase.selectQuery(String.format("SELECT idObservateur FROM Observateur WHERE nom = '%s' AND prenom = '%s' LIMIT 1", lastName, firstName));
            int idObservateur;
            if (observateur.size() == 1) {
                idObservateur = Math.abs(UUID.randomUUID().hashCode());
                UseDatabase.updateQuery(String.format("INSERT INTO Observateur (idObservateur, nom, prenom) VALUES (%d, '%s', '%s')",
                        idObservateur, lastName, firstName));
            } else {
                idObservateur = Integer.parseInt(observateur.get(1).get(0));
            }

            Connection conn = UseDatabase.MySQLConnection();

            UseDatabase.updateQuery(String.format("INSERT INTO Lieu (coord_Lambert_X, coord_Lambert_Y) VALUES ('%s', '%s')",
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

            UseDatabase.updateQuery(String.format("INSERT INTO AObserve (lobservation, lobservateur) VALUES ('%s', '%s')",
                    idObs, idObservateur));

            UseDatabase.updateQuery(String.format("INSERT INTO Obs_Loutre (obsL, commune, lieuDit, indice) VALUES ('%s', '%s', '%s', '%s')",
                    idObs, commune, lieuDit, indice));

            Main.showPopup("Observation enregistrée correctement", event, false);

            prep.close();
            conn.close();
        } catch (IllegalArgumentException e) {
            Main.showPopup(e.getMessage(), event, true);
        } catch (SQLException e) {
            Main.showPopup("Une erreur est survenue au moment de l'enregistrement des données", event, true);
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            Main.showPopup(e.getMessage(), event, true);
            System.err.println(e.getMessage());
        } catch (Exception e) {
            Main.showPopup("Une erreur inconnue est survenue", event, true);
            e.printStackTrace();
        }
    }

    private void checkFields(String lastName, String firstName, LocalDate date, String time, String lambertX, String lambertY, String commune, String lieuDit) throws IllegalArgumentException {
        if (!lastName.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException("Le nom ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");

        if (!firstName.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException("Le prénom ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");

        if (date == null)
            throw new IllegalArgumentException("La date est obligatoire");

        String[] timeSplit = time.split(":");
        int h = Integer.parseInt(timeSplit[0]);
        int m = Integer.parseInt(timeSplit[1]);
        if (!time.matches("\\d{2}:\\d{2}") && 0 < h && h < 24 && 0 < m && m < 60)
            throw new IllegalArgumentException("L'heure ne peut pas être vide et doit être au format hh:mm");

        if (!lambertX.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La coordonnée ne peut pas être vide et Lambert X doit être un nombre");

        if (!lambertY.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La coordonnée ne peut pas être vide et Lambert Y doit être un nombre");

        if (!commune.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException("La commune ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");

        if (!lieuDit.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException("Le lieu ne peut pas être vide et dit ne doit contenir que des lettres, espaces et tirets");
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
        String query = "SELECT * FROM vue_AllFromLoutre";
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
        query = query + ";";
        return query;
    }
}
