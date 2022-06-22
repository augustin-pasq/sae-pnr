package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeletionConfirmController {
    @FXML
    private Button doNotDeleteButton;

    @FXML
    private Button deleteButton;

    @FXML
    public void abort(ActionEvent event) {
        Main.goBack(event);
    }

    @FXML
    public void delete(ActionEvent event) {
        SpeciesManagementController smc = new SpeciesManagementController();
        smc.deleteSpecies(event);
    }
}
