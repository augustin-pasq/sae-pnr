package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.donnee.ContenuNid;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Controller for the FilterGCI page
 */
public class FilterGCIController extends InteractivePage {

    /**
     * The animal value
     */
    private final String ANIMAL = "GCI";

    /**
     * The observation nature list
     */
    ObservableList<ContenuNid> natureList = FXCollections.observableArrayList(ContenuNid.values());
    
    /**
     * The observed nest list
     */
    ObservableList<String> nidObserveList = FXCollections.observableArrayList("Oui", "Non");
    
    /**
     * The reason list
     */
    ObservableList<String> raisonList = FXCollections.observableArrayList("Envol", "Inconnu", "Maree", "Pietinement", "Prédation");
    
    /**
     * The protected nest list
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
    private TextField idField;

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
     * The button to validate the filter
     */
    @FXML
    private Button validateButton;

    @Override
    /**
     * Initialise the controller class.
     */
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = true;
        natureComboBox.setItems(natureList);
        nidObserveComboBox.setItems(nidObserveList);
        raisonComboBox.setItems(raisonList);
        nidProtegeComboBox.setItems(nidProtegeList);
    }

    @FXML
    /**
     * Method called when the user clicks on the validate button.
     */
    public void filter(ActionEvent event){

        // Init //
        String lastName = lastNameField.getText().toUpperCase();
        String firstName = firstNameField.getText().toUpperCase();
        LocalDate date = dateField.getValue();
        String time = timeField.getText();
        String lambertX = lambertXField.getText();
        String lambertY = lambertYField.getText();

        String nature = null;
        if (natureComboBox.getValue() != null){
            nature = natureComboBox.getValue().toString();
        }

        String nombre = nombreField.getText();

        Integer presentMaisNonObs = null;
        if (nidObserveComboBox.getValue() != null) {
            presentMaisNonObs = nidObserveComboBox.getValue().equals("Oui") ? 1 : 0;
        }

        String idNid = idField.getText();
        String plage = plageField.getText();

        String raisonArretObservation = null;
        if (raisonComboBox.getValue() != null){
            raisonArretObservation = raisonComboBox.getValue().toString();
        }
        
        String nbEnvol = nbEnvolField.getText();

        Integer protection = null;
        if (nidProtegeComboBox.getValue() != null) {
            protection = nidProtegeComboBox.getValue().equals("Oui") ? 1 : 0;
        }

        String bagueMale = bagueMaleField.getText();
        String bagueFemelle = bagueFemelleField.getText();
        
        //

        try {
            // Check if the data is valid
            checkFields(lastName, firstName, date, time, lambertX, lambertY, nombre, idNid, plage, nbEnvol, bagueMale, bagueFemelle);

            HashMap<Object, String> filter = new HashMap<>();
            this.initFilter(filter, lastName, firstName, date, time, lambertX, lambertY, nature, nombre, presentMaisNonObs, 
                            idNid, plage, raisonArretObservation, nbEnvol, protection, bagueMale, bagueFemelle);
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
     * @throws IllegalArgumentException if one of the fields is invalid, with a detailed message
     */
    private void checkFields(String lastName, String firstName, LocalDate date, String time,
                                String lambertX, String lambertY, String nombre,
                                String idNid, String plage, String nbEnvol,
                                String bagueMale, String bagueFemelle) throws IllegalArgumentException {

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
            throw new IllegalArgumentException("La coordonnée Lambert X ne peut pas être vide et doit être un nombre");

        if (!lambertY.matches("\\d+(\\.\\d+)?") && !lambertY.isEmpty())
            throw new IllegalArgumentException("La coordonnée Lambert Y ne peut pas être vide et doit être un nombre");

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
     * Adds in a filter the values of the field with the associated database column names.
     * @param filter the filter of the observation
     * @param lastName the last name of the observation
     * @param firstName the first name of the observation
     * @param date the date of the observation
     * @param time the time of the observation
     * @param lambertX the coordinate X of the observation
     * @param lambertY the oordinate Y of the observation
     * @param nature the nature of the observation
     * @param nombre the number of items of the observation
     * @param presentMaisNonObs boolean value which is true if the nest have already been observed
     * @param idNid the nest id
     * @param plage the beach
     * @param raisonArretObservation the reason why the prospecting has been stopped
     * @param nbEnvol the number of flights
     * @param protection boolean value which is true if the nest is under protection
     * @param bagueMale the code of the male
     * @param bagueFemelle the code of the female
     */
    private void initFilter(HashMap<Object, String> filter, String lastName, String firstName,
                            LocalDate date, String time, String lambertX, String lambertY,
                            String nature, String nombre, Integer presentMaisNonObs, String idNid,
                            String plage, String raisonArretObservation, String nbEnvol,
                            Integer protection, String bagueMale, String bagueFemelle){

        
        filter.put(lastName, "nom");
        filter.put(firstName, "prenom"); 
        filter.put(date, "dateObs");
        filter.put(time, "heureObs");
        filter.put(lambertX, "lieu_Lambert_X");
        filter.put(lambertY, "lieu_Lambert_Y");
        filter.put(nature, "nature");
        filter.put(nombre, "nombre");
        this.putInteger(filter, presentMaisNonObs, "presentMaisNonObs");
        filter.put(idNid, "idNid");
        filter.put(plage, "plage");
        filter.put(raisonArretObservation, "raisonArretObservation");
        filter.put(nbEnvol, "nbEnvol");
        this.putInteger(filter, protection, "protection");
        filter.put(bagueMale, "bagueMale");
        filter.put(bagueFemelle, "bagueFemelle");
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
