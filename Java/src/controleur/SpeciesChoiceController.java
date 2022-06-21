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
    private void consultBatracienObs (ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();
        Main.switchScene(data.get(0) + "Batracien", consultObsBatracienButton);
    }

    @FXML
    private void consultChouetteObs (ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();
        Main.switchScene(data.get(0) + "Chouette", consultObsChouetteButton);
    }

    @FXML
    private void consultGCIObs (ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();
        Main.switchScene(data.get(0) + "GCI", consultObsGCIButton);
    }

    @FXML
    private void consultHippocampeObs (ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();
        Main.switchScene(data.get(0) + "Hippocampe", consultObsHippocampeButton);
    }

    @FXML
    private void consultLoutreObs (ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();
        Main.switchScene(data.get(0) + "Loutre", consultObsLoutreButton);
    }
}
