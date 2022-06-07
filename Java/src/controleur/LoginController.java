package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    @FXML
    private void login(final ActionEvent event) {
        Color c = Color.color(1, 1, 1);
        System.out.println(c);
        loginButton.setStyle(
                "-fx-background-color: #" + c.toString().substring(2));
    }
}
