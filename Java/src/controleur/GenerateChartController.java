package controleur;

import javafx.event.Event;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import modele.donnee.EspeceObservee;
import java.util.HashMap;
import java.net.URL;
import java.util.ResourceBundle;

import javax.lang.model.element.QualifiedNameable;

import static modele.donnee.UseDatabase.selectQuery;

public class GenerateChartController extends InteractivePage {

    ObservableList<EspeceObservee> especeList = FXCollections.observableArrayList(EspeceObservee.values());
    ObservableList<String> abscisseList = FXCollections.observableArrayList("Période temporelle");
    ObservableList<String> ordoneeList = FXCollections.observableArrayList("Nombre d'individus");
    @FXML
    private ComboBox<EspeceObservee> especeComboBox;
    @FXML
    private ComboBox<String> abscisseComboBox;
    @FXML
    private ComboBox<String> ordonneeComboBox;
    @FXML
    private Button generateButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        especeComboBox.setItems(especeList);
        abscisseComboBox.setItems(abscisseList);
        ordonneeComboBox.setItems(ordoneeList);
    }

    @FXML
    /**
     * Generates a query to get the data for the chart.
     * @param event The event that triggered the method.
     * @return the query to make a chart.
     */
    private String generateQuery(final Event event){

        String query = "";
        if(especeComboBox.getValue() != null && abscisseComboBox.getValue() != null && ordonneeComboBox.getValue() != null){
            String espece, abscisse, ordonnee;
            espece = especeComboBox.getValue().toString();
            if (!espece.equals("GCI"))
                espece = stringTreatment(espece);
            
            abscisse = abscisseComboBox.getValue().toString();
            ordonnee = ordonneeComboBox.getValue().toString();

            query = "SELECT * FROM vue_allFrom" + espece + " ORDER BY dateObs DESC;";
        }
        System.out.println(query);
        return query;
    }

    /**
     * Returns the same string with a capital letter and lower letters.
     * @param s the string to treat.
     * @return the same string with a capital letter.
     */
    private String stringTreatment(String s){
        String result = "";
        for (int i = 0; i < s.length(); i++){
            if (i == 0){
                result += s.charAt(i);
            }
            else {
                result += Character.toLowerCase(s.charAt(i));
            }
        }
        return result;
    }
}
