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
            case "Saisir" -> Main.switchScene("EnterData", target);
            case "Modifier" -> Main.switchScene("ModifyData", target);
            case "Consulter" -> Main.switchScene("ConsultData", target);
            default -> System.err.println("Error: unknown button \"" + target.getText() + "\"");
        }
    }

    public void logout(final ActionEvent event) {
        Main.switchScene("Login", this.logout);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
