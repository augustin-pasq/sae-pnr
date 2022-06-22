package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the ConsultData page
 *
 * @author Groupe SAE PNR 1D1
 */
public class ConsultDataController extends InteractivePage {

    @FXML
    private Button consultObsButton;

    @FXML
    private Button generateChartButton;

    @FXML
    private void consultObs(ActionEvent event) {
        Data data = new Data("ConsultData");
        Main.switchScene("SpeciesChoice", consultObsButton, data);
    }

    @FXML
    private void generateChart(ActionEvent event) {
        Main.switchScene("GenerateChart", generateChartButton);
    }
}
