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
        Main.switchScene("ConsultBatObs", consultObsBatButton);
    }

    @FXML
    private void consultChouetteObs (ActionEvent event) {
        Main.switchScene("ConsultChouetteObs", consultObsChouetteButton);
    }

    @FXML
    private void consultGravelotObs (ActionEvent event) {
        Main.switchScene("ConsultGravelotObs", consultObsGravelotButton);
    }

    @FXML
    private void consultHippoObs (ActionEvent event) {
        Main.switchScene("ConsultHippoObs", consultObsHippoButton);
    }

    @FXML
    private void consultLoutreObs (ActionEvent event) {
        Main.switchScene("ConsultLoutreObs", consultObsLoutreButton);
    }
}
