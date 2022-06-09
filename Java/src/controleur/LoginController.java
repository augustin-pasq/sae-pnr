package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import static modele.donnee.UseDatabase.authenticateUser;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    /**
     * Log into an account
     * @param event the event that triggered the method
     */
    @FXML
    private void login(final ActionEvent event) {
        String username = usernameField.getText();
        String password = hashPassword(passField.getText());

        if (username.equals("") || password.equals("")) {
            this.errorLabel.setText("Please fill in all the fields");
        } else {
            if (authenticateUser(username, password)) {
                System.out.println("Login successful");
                this.errorLabel.setText("");
            } else {
                System.out.println("Login failed");
                this.errorLabel.setText("Wrong username or password");
            }
        }
    }

    private String hashPassword(String password) {
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

    public static void main(String[] args) {
        LoginController loginController = new LoginController();
        String p = "password";
        System.out.format("SHA-512(%s) = %s", p, loginController.hashPassword(p));
    }
}
