package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import modele.donnee.UseDatabase;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void login(final ActionEvent event) {
        System.out.println("Login");
        UseDatabase.selectQuery("SELECT lObservateur, COUNT(lObservation) nbObs FROM AObserve GROUP BY lObservateur HAVING nbObs > (SELECT AVG(nbObs) moy FROM (SELECT lObservateur, COUNT(lObservation) nbObs FROM AObserve GROUP BY lObservateur) B);");
        this.loginButton.setText("Logged in");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();
        return argon2PasswordEncoder.encode(password);
    }
}
