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

    @FXML
    private Button consultObsBatracienButton;

    @FXML
    private Button consultObsChouetteButton;

    @FXML
    private Button consultObsGCIButton;

    @FXML
    private Button consultObsHippocampeButton;

    @FXML
    private Button consultObsLoutreButton;

    @FXML
    private void consultBatracienObs(ActionEvent event) {
        goTo("Batracien");
    }

    @FXML
    private void consultChouetteObs(ActionEvent event) {
        goTo("Chouette");
    }

    @FXML
    private void consultGCIObs(ActionEvent event) {
        ObservationChoiceController.setAllObservations("GCI");
        goTo("GCI");
    }

    @FXML
    private void consultHippocampeObs(ActionEvent event) {
        goTo("Hippocampe");
    }

    @FXML
    private void consultLoutreObs(ActionEvent event) {
        goTo("Loutre");
    }

    private void goTo(String species) {
        Data data = (Data) this.backButton.getScene().getUserData();
        String scene = (String) data.get(0);
        switch (scene) {
            case "Data" -> Main.switchScene("Data" + species, this.homeButton, data); // For observation insertion
            case "Edit" -> Main.switchScene(scene + species, this.homeButton, data); // For observation modification
            case "ObservationChoice" -> {
                ObservationChoiceController.setAllObservations(species);
                Main.switchScene(scene, this.homeButton, data); // For observation consultation
            }
        }
    }
}
