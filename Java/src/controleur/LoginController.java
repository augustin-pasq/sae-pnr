package controleur;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static modele.donnee.UseDatabase.authenticateUser;

/**
 * Controller for the Login page
 */
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

    /**
     * Label for the error message
     */
    @FXML
    private Label errorLabel;

    /**
     * Inherited method from Initializable
     * @see javafx.fxml.Initializable
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Log into an account
     * @param event the event that triggered the method
     */
    @FXML
    private void login(final Event event) {
        String username = usernameField.getText();
        String password = hashPassword(passField.getText());

        if (username.equals("") || password.equals("")) {
            this.errorLabel.setText("Merci de remplir tous les champs");
        } else {
            try {
                if (authenticateUser(username, password)) {
                    changeScene(username, event);
                } else {
                    this.errorLabel.setText("Identifiant ou mot de passe incorrect");
                }
            } catch (NoInternetException e) {
                this.errorLabel.setText("Vous devez être connecté à internet pour utiliser l'application");
            }
        }
    }

    /**
     * React to a key press. Login if key is Enter
     * @param event the event that triggered the method
     */
    @FXML
    private void keypress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            login(event);
        }
    }

    /**
     * Change to the appropriate scene
     * @param username the username of the user
     * @param event the event that triggered the method
     */
    private void changeScene(final String username, final Event event) {
        Control target = (Control) event.getSource();
        if (username.equals("admin")) {
            /*
            * Pass data to the next scene
            Data o = new Data("BATRACIEN");
            System.out.println("AdminPanelController initialized " + target);
            Main.switchScene("AdminPanel", target, o);
             */
            Main.switchScene("AdminPanel", target);
        } else {
            Main.switchScene("HomePage", target);
        }
    }

    /**
     * Create an SHA-256 hash of a password
     * @param password the password to hash
     * @return the hash of the password
     */
    public String hashPassword(String password) {
        String hash = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
