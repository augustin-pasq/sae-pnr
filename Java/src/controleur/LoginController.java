package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    /**
     * The text field for the username
     */
    @FXML
    public TextField usernameField;
    /**
     * The text field for the password
     */
    @FXML
    public PasswordField passField;
    /**
     * The login button
     */
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void login(final ActionEvent event) {
        System.out.println("Login" + usernameField.getText() + " " + hashPassword(passField.getText()));

    }

    private String hashPassword(String password) {
        Argon2PasswordEncoder a2 = new Argon2PasswordEncoder();
        return a2.encode(password);
    }
}
