package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the SpeciesChoice page
 *
 * @author Groupe SAE PNR 1D1
 */
public class SpeciesChoiceController extends InteractivePage {

    /**
     * The batrachian choice button
     */
    @FXML
    private Button consultObsBatracienButton;

    /**
     * The owl choice button
     */
    @FXML
    private Button consultObsChouetteButton;

    /**
     * The GCI choice button
     */
    @FXML
    private Button consultObsGCIButton;

    /**
     * The seahorse choice button
     */
    @FXML
    private Button consultObsHippocampeButton;

    /**
     * The otter choice button
     */
    @FXML
    private Button consultObsLoutreButton;

    /**
     * The action to perform when the batrachian choice button is clicked
     * 
     * @param event the event
     */
    @FXML
    private void consultBatracienObs(ActionEvent event) {
        goTo("Batracien");
    }

    /**
     * The action to perform when the owl choice button is clicked
     * 
     * @param event the event   
     */
    @FXML
    private void consultChouetteObs(ActionEvent event) {
        goTo("Chouette");
    }

    /**
     * The action to perform when the GCI choice button is clicked
     * 
     * @param event the event
     */
    @FXML
    private void consultGCIObs(ActionEvent event) {
        ObservationChoiceController.setAllObservations("GCI");
        goTo("GCI");
    }

    /**
     * The action to perform when the hippocampe choice button is clicked
     * 
     * @param event the event
     */
    @FXML
    private void consultHippocampeObs(ActionEvent event) {
        goTo("Hippocampe");
    }

    /**
     * The action to perform when the otter choice button is clicked
     * 
     * @param event the event
     */
    @FXML
    private void consultLoutreObs(ActionEvent event) {
        goTo("Loutre");
    }

    /**
     * The action to perform when a button is clicked
     * 
     * @param species the selected specie
     */
    private void goTo(String species) {
        Data data = (Data) this.backButton.getScene().getUserData();
        String scene = (String) data.get(0);
        switch (scene) {
            case "Data" -> Main.switchScene("Data" + species, this.homeButton, data); // For observation insertion
            case "Edit", "ConsultData", "Delete" -> Main.switchScene("Filter" + species, this.homeButton, data);
            default -> System.out.println("Error: " + scene + " is not a valid scene");
        }
    }
}
