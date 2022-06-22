package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    public Button consulter;

    @FXML
    public Button modifier;

    @FXML
    public Button saisir;

    @FXML
    public Button logout;

    public void move(final ActionEvent event) {
        Button target = (Button) event.getSource();
        switch (target.getId()) {
            case "Saisir" -> {
                Data data = new Data("Data");
                Main.switchScene("SpeciesChoiceForAdding", target, data);
            }
            case "Modifier" -> {
                Data data = new Data("Edit");
                Main.switchScene("ModifyData", target);
            }
            case "Consulter" -> Main.switchScene("ConsultData", target);
            case "logout" -> Main.switchScene("Login", target);
            default -> System.err.println("Error: unknown button \"" + target.getText() + "\"");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
