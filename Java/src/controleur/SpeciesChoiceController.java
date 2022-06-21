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

    private String nextScene;

    @FXML
    private Button consultObsBatButton;

    @FXML
    private Button consultObsChouetteButton;

    @FXML
    private Button consultObsGravelotButton;

    @FXML
    private Button consultObsHippoButton;

    @FXML
    private Button consultObsLoutreButton;


    @FXML
    private void consultBatObs (ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();
        Main.switchScene(data.get(0) + "Batracien", consultObsBatButton);
    }

    @FXML
    private void consultChouetteObs (ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();
        Main.switchScene(data.get(0) + "Chouette", consultObsChouetteButton);
    }

    @FXML
    private void consultGravelotObs (ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();
        Main.switchScene(data.get(0) + "GCI", consultObsGravelotButton);
    }

    @FXML
    private void consultHippoObs (ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();
        Main.switchScene(data.get(0) + "Hippocampe", consultObsHippoButton);
    }

    @FXML
    private void consultLoutreObs (ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();
        Main.switchScene(data.get(0) + "Loutre", consultObsLoutreButton);
    }
}
