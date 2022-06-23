package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.donnee.EspeceHippocampe;
import modele.donnee.Peche;
import modele.donnee.Sexe;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Controller for the FilterHippocampe page
 */
public class FilterHippocampeController extends InteractivePage {

    /**
     * The animal value
     */
    private final String ANIMAL = "Hippocampe";  
    
    /**
     * The specie list
     */
    ObservableList<EspeceHippocampe> especeList = FXCollections.observableArrayList(EspeceHippocampe.values());
    
    /**
     * The gender list
     */
    ObservableList<Sexe> sexeList = FXCollections.observableArrayList(Sexe.values());
    
    /**
     * The pregnancy list
     */
    ObservableList<String> gestantList = FXCollections.observableArrayList("Gestant", "Non gestant");
   
   /**
    * The fishing type list
    */
    ObservableList<Peche> typePecheList = FXCollections.observableArrayList(Peche.values());

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
     * The specie of the seahorse
     */
    @FXML
    private ComboBox<EspeceHippocampe> especeComboBox;

    /**
     * The gender of the seahorse
     */
    @FXML
    private ComboBox<Sexe> sexeComboBox;

    /**
     * The temperature of the water
     */
    @FXML
    private TextField temperatureField;

    /**
     * The type of fishing of the seahorse
     */
    @FXML
    private ComboBox<Peche> typePecheComboBox;

    /**
     * The size of the seahorse
     */
    @FXML
    private TextField sizeField;

    /**
     * Indicates if the seahorse is pregnant
     */
    @FXML
    private ComboBox<String> gestantComboBox;

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
        especeComboBox.setItems(especeList);
        sexeComboBox.setItems(sexeList);
        gestantComboBox.setItems(gestantList);
        typePecheComboBox.setItems(typePecheList);
    }

    /**
     * Filter the select query
     * 
     * @param event the event that triggered the method
     */
    @FXML
    public void filter(ActionEvent event) {

        // Init //
        String lastName = lastNameField.getText().toUpperCase();
        String firstName = firstNameField.getText().toUpperCase();
        LocalDate date = dateField.getValue();
        String time = timeField.getText();
        String lambertX = lambertXField.getText();
        String lambertY = lambertYField.getText();
        String espece = null;
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
        if (sexeComboBox.getValue() != null) {
            sexe = sexeComboBox.getValue().toString().charAt(0) +
                    sexeComboBox.getValue().toString().substring(1).toLowerCase();
        }

        String temperature = temperatureField.getText();
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

        Integer gestant = null;
        if (gestantComboBox.getValue() != null) {
            gestant = gestantComboBox.getValue().equals("Gestant") ? 1 : 0;
        }

        //

        try {
            // check the validity of the fields
            checkFields(lastName, firstName, date, time, lambertX, lambertY, temperature, String.valueOf(size));

            HashMap<Object, String> filter = new HashMap<>();
            this.initFilter(filter, lastName, firstName, date, time, lambertX, lambertY, espece, sexe, temperature, typePeche, size, gestant);
            String restriction = this.makeRestriction(filter);

            Data userData = (Data) this.homeButton.getScene().getUserData();
            Data data = new Data(userData.get(0), ANIMAL, restriction);
            data.setAdmin(userData.isAdmin());
            ObservationChoiceController.setAllObservations(ANIMAL, restriction);
            Main.switchScene("ObservationChoice", this.validateButton, data);

        } catch (IllegalArgumentException e) {
            Main.showPopup(e.getMessage(), event, true);
        }
    }

    /**
     * Adds in a filter the values of the field with the associated database column names.
     * @param filter the filter
     * @param lastName the last name of the observer
     * @param firstName the first name of the observer
     * @param date the date of the observation
     * @param time the time of the observation
     * @param lambertX the X Lambert93 coordinates of the observation
     * @param lambertY the Y Lambert93 coordinates of the observation
     * @param espece the specie of the seahorse
     * @param sexe the gender of the seaborn
     * @param temperature the temperature of the water
     * @param typePeche the fishing type
     * @param size the size of the seaborn
     * @param gestant the pregnancy of the seaborn
     */
    private void initFilter(HashMap<Object, String> filter, String lastName, String firstName,
                            LocalDate date, String time, String lambertX, String lambertY, 
                            String espece, String sexe, String temperature, String typePeche, 
                            String size, Integer gestant){

        filter.put(lastName, "nom");
        filter.put(firstName, "prenom"); 
        filter.put(date, "dateObs");
        filter.put(time, "heureObs");
        filter.put(lambertX, "lieu_Lambert_X");
        filter.put(lambertY, "lieu_Lambert_Y");
        filter.put(espece, "espece");
        filter.put(sexe, "sexe");
        filter.put(temperature, "temperatureEau");
        filter.put(typePeche, "typePeche");
        filter.put(size , "taille");
        this.putInteger(filter, gestant, "gestant");
    }

    /**
     * Check if all fields are valid
     * @param lastName last name of the observer
     * @param firstName first name of the observer
     * @param date date of the observation
     * @param time time of the observation
     * @param lambertX lambert X coordinate of the observation
     * @param lambertY lambert Y coordinate of the observation
     * @param temperature temperature of the water
     * @param size size of the seaborn
     * @throws IllegalArgumentException if one of the fields is invalid, with a detailed message
     */
    private void checkFields(String lastName, String firstName, LocalDate date, String time, String lambertX, String lambertY, String temperature, String size) throws IllegalArgumentException {
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

        if (!temperature.matches("\\d+(\\.\\d+)?") && !temperature.isEmpty()) 
            throw new IllegalArgumentException("La température doit être un nombre");

        System.out.println("size :" +size);
        if (!size.matches("\\d+(\\.\\d+)?") && !size.isEmpty())
            throw new IllegalArgumentException("La taille doit être un nombre");
    }

    /**
     * Edit the select query to get data from the database
     * 
     * @param filter the filter containing the values of the fields with the associated database column names.
     * @return the end of the query, corresponding to the restriction of a query.
     */
    private String makeRestriction(HashMap<Object, String> filter){
        String query = "";
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
