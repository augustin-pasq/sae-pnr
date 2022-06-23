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


    /**
     * The button to accede to the page of observation consultation
     */
    @FXML
    private Button consultObsButton;

    /**
     * The button to accede to the page of chart generation
     */
    @FXML
    private Button generateChartButton;

    /**
     * Got to the SpeciesChoice page
     *
     * @param event the event that triggered the action
     */
    @FXML
    private void consultObs(ActionEvent event) {
        Data data = new Data("ConsultData");
        Main.switchScene("SpeciesChoice", consultObsButton, data);
    }

    /**
     * Got to the ChartGeneration page
     *
     * @param event the event that triggered the action
     */
    @FXML
    private void generateChart(ActionEvent event) {
        Main.switchScene("GenerateChart", generateChartButton);
    }
}
