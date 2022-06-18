package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public abstract class InteractivePage implements Initializable {
    @FXML
    public Button backButton;

    @FXML
    public Button homeButton;

    public abstract void goBack(final ActionEvent event);

    public abstract void goHome(final ActionEvent event);
}
