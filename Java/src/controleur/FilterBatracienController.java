package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.donnee.EspeceBatracien;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Controller for the FilterBatracien page
 */
public class FilterBatracienController extends InteractivePage {

    /**
     * The animal value
     */
    private final String ANIMAL = "Batracien";

    /**
     * The species list
     */
    ObservableList<EspeceBatracien> especeList = FXCollections.observableArrayList(EspeceBatracien.values());
    
    /**
     * The sky condition
     */
    ObservableList<String> meteoCielList = FXCollections.observableArrayList("dégagé", "semi-dégagé", "nuageux");
    
    /**
     * The weather temperature list
     */
    ObservableList<String> meteoTemperatureList = FXCollections.observableArrayList("froid", "moyen", "chaud");
    
    /**
     * The weather wind list
     */
    ObservableList<String> meteoVentList = FXCollections.observableArrayList("non", "léger", "moyen", "fort");
    
    /**
     * The weather rain list
     */
    ObservableList<String> meteoPluieList = FXCollections.observableArrayList("non", "légère", "moyenne", "forte");
   
    /**
     * The temporary humid area list
     */
    ObservableList<String> zoneTemporaireList = FXCollections.observableArrayList("Oui", "Non");
    
    /**
     * The tide type list
     */
    ObservableList<String> typeMareeList = FXCollections.observableArrayList("Prairie", "Etang", "Marais", "Mare");
    
    /**
     * The slope type list
     */
    ObservableList<String> typePenteList = FXCollections.observableArrayList("Raide", "Abrupte", "Douce");
    
    /**
     * The opening type list
     */
    ObservableList<String> typeOuvertureList = FXCollections.observableArrayList("Abritee", "Semi-Abritee", "Ouverte");
    
    /**
     * The vegetation type list
     */
    ObservableList<String> typeVegetationList = FXCollections.observableArrayList("environnement", "bordure", "ripisyle");

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
     * The button to validate the filter
     */
    @FXML
    private Button validateButton;

    /**
     * Inherited method from Initializable
     * @see javafx.fxml.Initializable
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
     * Filter the select query
     * 
     * @param event the event that triggered the method
     */
    @FXML
    private void filter(ActionEvent event) {
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
        String meteoTemperature = meteoTemperatureComboBox.getValue() == null ? null : meteoTemperatureComboBox.getValue().toLowerCase();
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

            HashMap<Object, String> filter = new HashMap<>();
            this.initFilter(filter, lastName, firstName, date, time, lambertX, lambertY, espece, nbAdultes, nbAmplexus, nbPontes, nbTetards,
                    temperature, meteoCiel, meteoTemperature, meteoVent, meteoPluie, zoneTemporaire, zoneProfondeur, zoneSurface, zoneMaree,
                    zonePente, zoneOuverture, natureVegetation, vegetation);
            String restriction = this.makeRestriction(filter);

            Data userData = (Data) this.homeButton.getScene().getUserData();
            Data data = new Data(userData.get(0), ANIMAL, restriction);
            ObservationChoiceController.setAllObservations(ANIMAL, restriction);
            Main.switchScene("ObservationChoice", this.validateButton, data);

        } catch (IllegalArgumentException e) {
                Main.showPopup(e.getMessage(), event, true);
        }
    }

    /**
     * Check if all fields are valid
     *
     * @param lastName last name of the observer
     * @param firstName first name of the observer
     * @param date date of the observation
     * @param time time of the observation
     * @param lambertX lambert X coordinate of the observation
     * @param lambertY lambert Y coordinate of the observation
     * @param nbAdultes number of adults of the observation
     * @param nbAmplexus number of amplexus of the observation
     * @param nbPontes number of clutch of the observation
     * @param nbTetards number of tadpole of the observation
     * @param temperature temperature of the observation
     * @param zoneProfondeur area depth of the observation
     * @param zoneSurface area surface of the observation
     * @param vegetation vegetation of the observation
     * 
     * @throws IllegalArgumentException if one of the fields is invalid, with a detailed message
     */
    private void checkFields(String lastName, String firstName, LocalDate date, String time,
                            String lambertX, String lambertY, String nbAdultes,
                            String nbAmplexus, String nbPontes, String nbTetards,
                            String temperature, String zoneProfondeur, String zoneSurface,
                            String vegetation) throws IllegalArgumentException {
        if (!lastName.matches("[a-zA-Z\\-éèàçëê\\ ]+") && !lastName.isEmpty())
            throw new IllegalArgumentException("Le nom ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");

        if (!firstName.matches("[a-zA-Z\\-éèàçëê\\ ]+") && !firstName.isEmpty())
            throw new IllegalArgumentException("Le prénom ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");


        if (time != null && !time.isEmpty()) {
            if (!time.matches("\\d{2}:\\d{2}"))
                throw new IllegalArgumentException("L'heure doit être au format hh:mm");
            else {
                String[] timeSplit = time.split(":");
                int h = Integer.parseInt(timeSplit[0]);
                int m = Integer.parseInt(timeSplit[1]);
                if (!(0 <= h && h < 24 && 0 <= m && m < 60))
                    throw new IllegalArgumentException("L'heure doit être valide");

            }
        }

        if (!lambertX.matches("\\d+(\\.\\d+)?") && !lambertX.isEmpty())
            throw new IllegalArgumentException("La coordonnée ne peut pas être vide et Lambert X doit être un nombre");

        if (!lambertY.matches("\\d+(\\.\\d+)?") && !lambertY.isEmpty())
            throw new IllegalArgumentException("La coordonnée ne peut pas être vide et Lambert Y doit être un nombre");

        if (!nbAdultes.matches("\\d+") && !nbAdultes.isEmpty())
            throw new IllegalArgumentException("Le nombre d'adultes ne peut pas être vide et doit être un entier");

        if (!nbAmplexus.matches("\\d+") && !nbAmplexus.isEmpty())
            throw new IllegalArgumentException("Le nombre d'amplexus ne peut pas être vide et doit être un entier");

        if (!nbPontes.matches("\\d+") && !nbPontes.isEmpty())
            throw new IllegalArgumentException("Le nombre de pontes ne peut pas être vide et doit être un entier");

        if (!nbTetards.matches("\\d+") && !nbTetards.isEmpty())
            throw new IllegalArgumentException("Le nombre de tetards ne peut pas être vide et doit être un entier");

        if (!temperature.matches("\\d+(\\.\\d+)?") && !temperature.isEmpty())
            throw new IllegalArgumentException("La température doit être un nombre");

        if (!zoneProfondeur.matches("\\d+(\\.\\d+)?") && !zoneProfondeur.isEmpty())
            throw new IllegalArgumentException("La profondeur de zone doit être un nombre");

        if (!zoneSurface.matches("\\d+(\\.\\d+)?") && !zoneSurface.isEmpty())
            throw new IllegalArgumentException("La zone de surface doit être un nombre");

        if (!vegetation.matches("[a-zA-Z\\-éèàçëê\\ ]+") && !vegetation.isEmpty())
            throw new IllegalArgumentException("La vegetation ne peut pas être vide et ne doit contenir que des lettres, espaces et tirets");
    }

    /**
     * Initialize the filter with the fields values
     * 
     * @param filter the filter to initialize
     * @param lastName last name of the observer
     * @param firstName first name of the observer
     * @param date date of the observation
     * @param time time of the observation
     * @param lambertX lambert X coordinate of the observation
     * @param lambertY lambert Y coordinate of the observation
     * @param espece the specie of the observation
     * @param nbAdulte number of adults of the observation
     * @param nbAmplexus number of amplexus of the observation
     * @param nbPontes number of clutch of the observation
     * @param nbTetards number of tadpole of the observation
     * @param temperature temperature of the observation
     * @param zoneProfondeur area depth of the observation
     * @param zoneSurface area surface of the observation
     * @param zoneMaree tide type of the observation
     * @param zonePente slope type of the observation
     * @param zoneOuverture opening type of the observation
     * @param natureVegetation vegetation type of the observation
     * @param vegetation vegetation of the observation
     */
    private void initFilter(HashMap<Object, String> filter, String lastName, String firstName, LocalDate date, String time,
                        String lambertX, String lambertY, String espece, String nbAdulte, String nbAmplexus, String nbPontes, String nbTetards, String temperature,
                            String meteoCiel, String meteoTemperature, String meteoVent, String meteoPluie, Integer zoneTemporaire,
                            String zoneProfondeur, String zoneSurface, String zoneMaree, String zonePente, String zoneOuverture,
                            String natureVegetation, String vegetation) {

        filter.put(lastName, "nom");
        filter.put(firstName, "prenon");
        filter.put(date, "dateObs");
        filter.put(time, "heureObs");
        filter.put(lambertX, "lieu_Lambert_X");
        filter.put(lambertY, "lieu_Lambert_Y");
        filter.put(espece, "espece");
        filter.put(nbAdulte, "nbAdultes");
        filter.put(nbAmplexus, "nbAmplexus");
        filter.put(nbPontes, "nbPOntes");
        filter.put(nbTetards, "nbTetards");
        filter.put(temperature, "temperature");
        filter.put(meteoCiel, "meteoCiel");
        filter.put(meteoTemperature, "meteoTemperature");
        filter.put(meteoVent, "meteoVent");
        filter.put(meteoPluie, "meteoPluie");
        this.putInteger(filter, zoneTemporaire, "zoneTemporaire");
        filter.put(zoneProfondeur, "zoneProfondeur");
        filter.put(zoneSurface, "zoneSurface");
        filter.put(zoneMaree, "zoneMaree");
        filter.put(zonePente, "zonePente");
        filter.put(zoneOuverture, "zoneOuverture");
        filter.put(natureVegetation, "natureVegetation");
        filter.put(vegetation, "vegetation");
    }

    /**
     * Edit the select query to get data from the database
     * @param filter the filter containing the values of the fields with the associated database column names.
     * @return the end of the query, corresponding to the restriction of a query.
     */
    private String makeRestriction(HashMap<Object, String> filter){
        String query = "";
        int nbRestriction = 0;

        for (Object o : filter.keySet()){
            if (!(o == null)){
                String value = o.toString();
                if (!value.isEmpty()){
                    if (nbRestriction > 0){
                        query = query + " AND " + filter.get(o) + " =\"" + value + "\"";
                    } else {
                        query = query + " WHERE " + filter.get(o) + " =\"" + value + "\"";
                    }
                    nbRestriction ++;
                }
            }
        }
        return query;
    }

    /**
     * Formats an Integer to be placed in the filter.
     * @param filter the filter
     * @param value the integer
     * @param column the integer's column name
     */
    private void putInteger(HashMap<Object, String> filter, Integer value, String column){
        if (value == null) filter.put("", "nombre");
        else filter.put(value, column);
    }
}