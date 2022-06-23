package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.donnee.EspeceBatracien;
import modele.donnee.UseDatabase;
import org.jetbrains.annotations.NotNull;
import org.junit.function.ThrowingRunnable;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Controller for the DataBatracien page
 *
 * @author Groupe SAE PNR 1D1
 */
public class DataBatracienController extends InteractivePage {

    /**
     * The list of batracien species
     */
    ObservableList<EspeceBatracien> especeList = FXCollections.observableArrayList(EspeceBatracien.values());
    /**
     * The list of sky weather states
     */
    ObservableList<String> meteoCielList = FXCollections.observableArrayList("dégagé", "semi-dégagé", "nuageux");
    /**
     * The list of temperature states
     */
    ObservableList<String> meteoTemperatureList = FXCollections.observableArrayList("froid", "moyen", "chaud");
    /**
     * The list of wind states
     */
    ObservableList<String> meteoVentList = FXCollections.observableArrayList("non", "léger", "moyen", "fort");
    /**
     * The list of rain states
     */
    ObservableList<String> meteoPluieList = FXCollections.observableArrayList("non", "légère", "moyenne", "forte");
    /**
     * The list of option to whether the zone is temporary or not
     */
    ObservableList<String> zoneTemporaireList = FXCollections.observableArrayList("Oui", "Non");
    /**
     * The list of types af ponds
     */
    ObservableList<String> typeMareeList = FXCollections.observableArrayList("Prairie", "Etang", "Marais", "Mare");
    /**
     * The list of types of slopes
     */
    ObservableList<String> typePenteList = FXCollections.observableArrayList("Raide", "Abrupte", "Douce");
    /**
     * The of covering types
     */
    ObservableList<String> typeOuvertureList = FXCollections.observableArrayList("Abritee", "Semi-Abritee", "Ouverte");
    /**
     * The list of types of vegetation
     */
    ObservableList<String> typeVegetationList = FXCollections.observableArrayList("environnement", "bordure",
            "ripisyle");

    /**
     * The last name of the observer
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
     * The specie of the observation
     */
    @FXML
    private ComboBox<EspeceBatracien> especeComboBox;

    /**
     * The number of adults of the observation
     */
    @FXML
    private TextField nbAdultesField;

    /**
     * The number of amplexus of the observation
     */
    @FXML
    private TextField nbAmplexusField;

    /**
     * The number of clutch of the observation
     */
    @FXML
    private TextField nbPontesField;

    /**
     * The number of tadpole of the observation
     */
    @FXML
    private TextField nbTetardsField;

    /**
     * The water temperature of the observation
     */
    @FXML
    private TextField temperatureField;

    /**
     * The sky condition of the observation
     */
    @FXML
    private ComboBox<String> meteoCielComboBox;

    /**
     * The air temperature of the observation
     */
    @FXML
    private ComboBox<String> meteoTemperatureComboBox;

    /**
     * The wind intensity of the observation
     */
    @FXML
    private ComboBox<String> meteoVentComboBox;

    /**
     * The rain intensity of the observation
     */
    @FXML
    private ComboBox<String> meteoPluieComboBox;

    /**
     * The temporary humid area value of the observation
     */
    @FXML
    private ComboBox<String> zoneTemporaireComboBox;

    /**
     * The depth of the area
     */
    @FXML
    private TextField zoneProfondeurField;

    /**
     * The surface of the area
     */
    @FXML
    private TextField zoneSurfaceField;

    /**
     * The tide's type of the area
     */
    @FXML
    private ComboBox<String> zoneMareeComboBox;

    /**
     * The slope of the area
     */
    @FXML
    private ComboBox<String> zonePenteComboBox;

    /**
     * The opening of the area
     */
    @FXML
    private ComboBox<String> zoneOuvertureComboBox;

    /**
     * The vegetation of the area
     */
    @FXML
    private ComboBox<String> vegetationComboBox;

    /**
     * The vegetation's type of the area
     */
    @FXML
    private TextField vegetationField;

    /**
     * Initializes the controller class.
     *
     * @param url             the url of the page
     * @param ressourceBundle the resource bundle of the page
     */
    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = false;
        especeComboBox.setItems(especeList);
        meteoCielComboBox.setItems(meteoCielList);
        meteoTemperatureComboBox.setItems(meteoTemperatureList);
        meteoVentComboBox.setItems(meteoVentList);
        meteoPluieComboBox.setItems(meteoPluieList);
        zoneTemporaireComboBox.setItems(zoneTemporaireList);
        zoneMareeComboBox.setItems(typeMareeList);
        zonePenteComboBox.setItems(typePenteList);
        zoneOuvertureComboBox.setItems(typeOuvertureList);
        vegetationComboBox.setItems(typeVegetationList);

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
        String espece = especeComboBox.getValue() == null ? null : especeComboBox.getValue().toString().toLowerCase();
        String nbAdultes = nbAdultesField.getText();
        String nbAmplexus = nbAmplexusField.getText();
        String nbPontes = nbPontesField.getText();
        String nbTetards = nbTetardsField.getText();
        String temperature = temperatureField.getText();
        String meteoCiel = meteoCielComboBox.getValue() == null ? null : meteoCielComboBox.getValue().toLowerCase();
        String meteoTemperature = meteoTemperatureComboBox.getValue() == null ? null
                : meteoTemperatureComboBox.getValue().toLowerCase();
        String meteoVent = meteoVentComboBox.getValue() == null ? null : meteoVentComboBox.getValue().toLowerCase();
        String meteoPluie = meteoPluieComboBox.getValue() == null ? null : meteoPluieComboBox.getValue().toLowerCase();
        Integer zoneTemporaire = null;
        if (zoneTemporaireComboBox.getValue() != null) {
            zoneTemporaire = zoneTemporaireComboBox.getValue().equals("Oui") ? 1 : 0;
        }
        String zoneProfondeur = zoneProfondeurField.getText();
        String zoneSurface = zoneSurfaceField.getText();
        String zoneMaree = zoneMareeComboBox.getValue() == null ? null : zoneMareeComboBox.getValue();
        String zonePente = zonePenteComboBox.getValue() == null ? null : zonePenteComboBox.getValue();
        String zoneOuverture = zoneOuvertureComboBox.getValue() == null ? null : zoneOuvertureComboBox.getValue();
        String natureVegetation = vegetationComboBox.getValue() == null ? null : vegetationComboBox.getValue();
        String vegetation = this.vegetationField.getText();

        try {
            // Check if the data is valid
            checkFields(lastName, firstName, date, time, lambertX, lambertY, nbAdultes, nbAmplexus, nbPontes, nbTetards,
                    temperature, zoneProfondeur, zoneSurface, vegetation);
            // Generate a unique id for the new observation
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
            prep.setDate(4, Date.valueOf(date));
            prep.setTime(5, Time.valueOf(localTime));
            prep.executeUpdate();

            UseDatabase.updateQuery(String.format("INSERT INTO AObserve (lobservation, lobservateur) VALUES (%d, '%s')",
                    idObs, idObservateur));

            int idVegeLieu = Math.abs(UUID.randomUUID().hashCode());
            UseDatabase.updateQuery(String.format("INSERT INTO Lieu_Vegetation (idVegeLieu) VALUES (%d)",
                    idVegeLieu));

            int idVege = Math.abs(UUID.randomUUID().hashCode());
            UseDatabase.updateQuery(String.format(
                    "INSERT INTO Vegetation (idVege, decrit_LieuVege, vegetation, natureVege) VALUES (%d, %d, '%s', '%s')",
                    idVege, idVegeLieu, vegetation, natureVegetation));

            ArrayList<ArrayList<String>> zoneHumide = UseDatabase.selectQuery(
                    String.format(
                            "SELECT zh_id FROM ZoneHumide WHERE zh_temporaire = %d AND zh_profondeur = %d AND zh_surface = %d AND zh_typeMare = '%s' AND zh_pente = '%s' AND zh_ouverture = '%s'",
                            zoneTemporaire, Integer.parseInt(zoneProfondeur), Integer.parseInt(zoneSurface), zoneMaree,
                            zonePente, zoneOuverture));
            int zh_id;
            if (zoneHumide.size() == 1) {
                zh_id = Math.abs(UUID.randomUUID().hashCode());
                UseDatabase.updateQuery(String.format(
                        "INSERT INTO ZoneHumide (zh_id, zh_temporaire, zh_profondeur, zh_surface, zh_typeMare, zh_pente, zh_ouverture) VALUES (%d, %d, %d, %d, '%s', '%s', '%s')",
                        zh_id, zoneTemporaire, Integer.parseInt(zoneProfondeur), Integer.parseInt(zoneSurface),
                        zoneMaree, zonePente, zoneOuverture));
            } else {
                zh_id = Integer.parseInt(zoneHumide.get(1).get(0));
            }

            UseDatabase.updateQuery(String.format(
                    "INSERT INTO Obs_Batracien (espece, obsB, concernes_vege, concerne_ZH, temperature, meteo_ciel, meteo_temp, meteo_vent, meteo_pluie, nombreAdultes, nombreAmplexus, nombrePonte, nombreTetard)"
                            +
                            "VALUES ('%s', %d, %d, %d, '%s', '%s', '%s', '%s', '%s', %d, %d, %d, %d)",
                    espece, idObs, idVege, zh_id, temperature, meteoCiel, meteoTemperature, meteoVent, meteoPluie,
                    Integer.parseInt(nbAdultes), Integer.parseInt(nbAmplexus), Integer.parseInt(nbPontes),
                    Integer.parseInt(nbTetards)));

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
     * @param nbAdultes number of adults of the observation
     * @param nbAmplexus number of amplexus of the observation
     * @param nbPontes number of clutch of the observation
     * @param nbTetards number of tadpole of the observation
     * @param temperature temperature of the observation
     * @param zoneProfondeur area depth of the observation
     * @param zoneSurface area surface of the observation
     * @param vegetation vegetation of the observation
     * @return null, this is a unit test return
     * @throws IllegalArgumentException if one of the fields is invalid, with a
     *                                  detailed message
     */
    public ThrowingRunnable checkFields(@NotNull String lastName, @NotNull String firstName, LocalDate date,
                                        String time, @NotNull String lambertX, @NotNull String lambertY, 
                                        @NotNull String nbAdultes, @NotNull String nbAmplexus, @NotNull String nbPontes, 
                                        @NotNull String nbTetards, @NotNull String temperature, @NotNull String zoneProfondeur,
                                        @NotNull String zoneSurface, @NotNull String vegetation) throws IllegalArgumentException {
        
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

        if (!nbAdultes.matches("\\d+"))
            throw new IllegalArgumentException("Le nombre d'adultes ne peut pas être vide et doit être un entier");

        if (!nbAmplexus.matches("\\d+"))
            throw new IllegalArgumentException("Le nombre d'amplexus ne peut pas être vide et doit être un entier");

        if (!nbPontes.matches("\\d+"))
            throw new IllegalArgumentException("Le nombre de pontes ne peut pas être vide et doit être un entier");

        if (!nbTetards.matches("\\d+"))
            throw new IllegalArgumentException("Le nombre de tetards ne peut pas être vide et doit être un entier");

        if (!temperature.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La température doit être un nombre");

        if (!zoneProfondeur.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La profondeur de zone doit être un nombre");

        if (!zoneSurface.matches("\\d+(\\.\\d+)?"))
            throw new IllegalArgumentException("La zone de surface doit être un nombre");

        if (!vegetation.matches("[a-zA-Z\\-éèàçëê\\ ]+"))
            throw new IllegalArgumentException(
                    "La vegetation ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");
        return null;
    }
}
