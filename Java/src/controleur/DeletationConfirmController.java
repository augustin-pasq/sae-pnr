package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeletationConfirmController {
    @FXML
    private Button doNotDeleteButton;

    @FXML
    private Button deleteButton;

    @FXML
    private void abort(ActionEvent event) {
        Main.goBack(event);
    }

    @FXML
    private void delete(ActionEvent event) {
        // TODO
    }
}
