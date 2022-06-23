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

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Controller for the FilterChouette page
 */
public class FilterChouetteController extends InteractivePage {

    /**
     * The animal value
     */
    private final String ANIMAL = "Chouette";

    /**
     * The specie list
     */
    ObservableList<EspeceChouette> especeList = FXCollections.observableArrayList(EspeceChouette.values());
    
    /**
     * The gender list
     */
    ObservableList<Sexe> sexeList = FXCollections.observableArrayList(Sexe.values());
    
    /**
     * The protocole list
     */
    ObservableList<String> protocoleList = FXCollections.observableArrayList("Oui", "Non");
    
    /**
     * The observation type list
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
        this.isAdmin = true;
        especeComboBox.setItems(especeList);
        sexeComboBox.setItems(sexeList);
        protocoleComboBox.setItems(protocoleList);
        typeObservationComboBox.setItems(typeObservationList);
    }

    /**
     * Validate the data filter the select query
     * @param event the event that triggered the method
     */
    @FXML
    private void filter(ActionEvent event) {

        // Init //
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
        String typeObservation = typeObservationComboBox.getValue() == null ? "" : typeObservationComboBox.getValue().toString();
        String sexe = sexeComboBox.getValue() == null ? "" : sexeComboBox.getValue().toString();

        //

        try {
            checkFields(lastName, firstName, date, time, lambertX, lambertY);

            HashMap<Object, String> filter = new HashMap<>();
            this.initFilter(filter, lastName, firstName, date, time, lambertX, lambertY, espece, protocole, typeObservation, sexe);
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
     * @param lastName last name of the observer
     * @param firstName first name of the observer
     * @param date date of the observation
     * @param time time of the observation
     * @param lambertX lambert X coordinate of the observation
     * @param lambertY lambert Y coordinate of the observation
     * @throws IllegalArgumentException if one of the fields is invalid, with a detailed message
     */
    private void checkFields( String lastName,  String firstName, LocalDate date, String time,  String lambertX,  String lambertY) throws IllegalArgumentException {
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

        if (!lambertX.isEmpty()) {
            if (!lambertX.matches("\\d+(\\.\\d+)?"))
                throw new IllegalArgumentException("La coordonnée ne peut pas être vide et Lambert X doit être un nombre");

            float lambertXInt = Float.parseFloat(lambertX);
            if (0 > lambertXInt || lambertXInt > 1300000)
                throw new IllegalArgumentException("La coordonnée Lambert X doit être comprise entre 0 et 1300000");
        }

        if (!lambertY.isEmpty()) {
            if (!lambertY.matches("\\d+(\\.\\d+)?"))
                throw new IllegalArgumentException("La coordonnée ne peut pas être vide et Lambert Y doit être un nombre");

            float lambertYInt = Float.parseFloat(lambertY);
            if (lambertYInt < 6000000 || lambertYInt > 7200000)
                throw new IllegalArgumentException("La coordonnée Lambert Y doit être comprise entre 6000000 et 7200000");

        }
    }

    /**
     * Adds in a filter the values of the field with the associated database column names.
     * @param filter the filter of the observation
     * @param lastName the last name of the observation
     * @param firstName the first name of the observation
     * @param date the date of the observation
     * @param time the time of the observation
     * @param lambertX the coordinate X of the observation
     * @param lambertY the oordinate Y of the observation
     * @param espece the specie of the observation
     * @param protocole the protocol of the observation
     * @param typeObs the type of the observation
     * @param sexe the sexe of the observation
     */
    private void initFilter(HashMap<Object, String> filter, String lastName, String firstName, LocalDate date, String time, 
                            String lambertX, String lambertY, String espece, Integer protocole, String typeObs, String sexe){

        filter.put(lastName, "nom");
        filter.put(firstName, "prenom"); 
        filter.put(date, "dateObs");
        filter.put(time, "heureObs");
        filter.put(lambertX, "lieu_Lambert_X");
        filter.put(lambertY, "lieu_Lambert_Y");
        filter.put(espece, "espece");
        this.putInteger(filter, protocole, "protocole");
        filter.put(typeObs, "typeObs");
        filter.put(sexe, "sexe");
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
     *  Formats an Integer to be placed in the filter.
     * @param filter the filter
     * @param value the integer
     * @param column the intefer's column name
     */
    private void putInteger(HashMap<Object, String> filter, Integer value, String column){
        if (value == null) filter.put("", "nombre");
        else filter.put(value, column);
    }
}
