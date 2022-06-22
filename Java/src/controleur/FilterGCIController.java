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
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = true;
        natureComboBox.setItems(natureList);
        nidObserveComboBox.setItems(nidObserveList);
        raisonComboBox.setItems(raisonList);
        nidProtegeComboBox.setItems(nidProtegeList);
    }

    @FXML
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
        
        System.out.println(restriction);

        Data data = new Data(ANIMAL, restriction);
        ObservationChoiceController.setAllObservations(ANIMAL, restriction);
        Main.switchScene("ObservationChoice", this.validateButton, data);
    }

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
