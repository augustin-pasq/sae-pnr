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
 * Controller of the filter GCI.
 */
public class FilterGCIController extends InteractivePage {

    private final String ANIMAL = "GCI";
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
    private TextField idField;
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

        Integer nombre = null;
        if (!nombreField.getText().isEmpty()){
            nombre = Integer.parseInt(nombreField.getText());
        }

        Integer presentMaisNonObs = null;
        if (nidObserveComboBox.getValue() != null) {
            presentMaisNonObs = nidObserveComboBox.getValue().equals("Oui") ? 1 : 0;
        }

        Integer leNid = null;
        if (!idField.getText().isEmpty()){
            leNid = Integer.parseInt(idField.getText());
        }

        String nomPlage = plageField.getText();

        String raisonArretObservation = null;
        if (raisonComboBox.getValue() != null){
            raisonArretObservation = raisonComboBox.getValue().toString();
        }
        
        Integer nbEnvols = null;
        if (!nbEnvolField.getText().isEmpty()){
            nbEnvols = Integer.parseInt(nbEnvolField.getText());
        }

        Integer protection = null;
        if (nidProtegeComboBox.getValue() != null) {
            protection = nidProtegeComboBox.getValue().equals("Oui") ? 1 : 0;
        }

        String bagueMale = bagueMaleField.getText();
        String bagueFemelle = bagueFemelleField.getText();
        
        //

        HashMap<Object, String> filter = new HashMap<>();
        this.initFilter(filter, lastName, firstName, date, time, lambertX, lambertY, nature, nombre, presentMaisNonObs, 
                        leNid, nomPlage, raisonArretObservation, nbEnvols, protection, bagueMale, bagueFemelle);
        String restriction = this.makeRestriction(filter);

        Data userData = (Data) this.homeButton.getScene().getUserData();
        Data data = new Data(userData.get(0), ANIMAL, restriction);
        ObservationChoiceController.setAllObservations(ANIMAL, restriction);
        Main.switchScene("ObservationChoice", this.validateButton, data);
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
     * @param leNid the nest id
     * @param nomPlage the beach
     * @param raisonArretObservation the reason why the prospecting has been stopped
     * @param nbEnvols the number of flights
     * @param protection boolean value which is true if the nest is under protection
     * @param bagueMale the code of the male
     * @param bagueFemelle the code of the female
     */
    private void initFilter(HashMap<Object, String> filter, String lastName, String firstName,
                            LocalDate date, String time, String lambertX, String lambertY,
                            String nature, Integer nombre, Integer presentMaisNonObs, Integer leNid,
                            String nomPlage, String raisonArretObservation, Integer nbEnvols,
                            Integer protection, String bagueMale, String bagueFemelle){

        
        filter.put(lastName, "nom");
        filter.put(firstName, "prenom"); 
        filter.put(date, "dateObs");
        filter.put(time, "heureObs");
        filter.put(lambertX, "lieu_Lambert_X");
        filter.put(lambertY, "lieu_Lambert_Y");
        filter.put(nature, "nature");

        if (nombre == null) filter.put("", "nombre");
        else filter.put(nombre, "nombre");

        if (presentMaisNonObs == null) filter.put("", "presentMaisNonObs");
        else filter.put(presentMaisNonObs, "presentMaisNonObs");

        if (leNid == null) filter.put("", "leNid");
        else filter.put(leNid, "leNid");

        filter.put(nomPlage, "nomPlage");
        filter.put(raisonArretObservation, "raisonArretObservation");

        if (nbEnvols == null) filter.put("", "nbEnvols");
        else filter.put(nbEnvols, "nbEnvols");

        if (protection == null) filter.put("", "protection");
        else filter.put(protection, "protection");         

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
}
