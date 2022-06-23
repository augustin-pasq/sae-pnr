package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import modele.donnee.UseDatabase;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller for the DeletionConfirm page
 *
 * @author Groupe SAE PNR 1D1
 */
public class DeletionConfirmController implements Initializable {
    @FXML
    /**
     * Button to not delete the observation
     */
    private Button doNotDeleteButton;

    @FXML
    /**
     * Button to delete the observation
     */
    private Button deleteButton;

    @FXML
    /**
     * Allows you to cancel and go back
     * @param event the event that triggered the action
     */
    public void abort(ActionEvent event) {
        Main.goBack(event);
    }

    @FXML
    /**
     * Allows you to delete an observation from the database
     * @param event the event that triggered the action
     */
    public void delete(ActionEvent event) {
        Data data = (Data) this.deleteButton.getScene().getUserData();
        String id = (String) data.get(4);
        String lastName = (String) data.get(5);
        String firstName = (String) data.get(6);

        try (Connection connection = UseDatabase.MySQLConnection()) {
            String idObservateur = UseDatabase.selectQuery(
                    String.format("SELECT idObservateur FROM Observateur WHERE nom = '%s' AND prenom = '%s'",
                            lastName, firstName)).get(1).get(0);

            UseDatabase.updateQuery(
                    String.format("DELETE FROM AObserve WHERE lobservateur = %d AND lobservation = %d;",
                            Integer.parseInt(idObservateur), Integer.parseInt(id)));


            switch ((String) data.get(3)) {
                case "Batracien" -> {
                    UseDatabase.updateQuery(
                            String.format("DELETE FROM Obs_Batracien WHERE obsB = %d;",
                                    Integer.parseInt(id)));
                }
                case "Chouette" -> {
                    UseDatabase.updateQuery(
                            String.format("DELETE FROM Obs_Chouette WHERE numObs = %d;",
                                    Integer.parseInt(id)));
                }
                case "GCI" -> {
                    UseDatabase.updateQuery(
                            String.format("DELETE FROM Obs_GCI WHERE obsG = %d;",
                                    Integer.parseInt(id)));
                }
                case "Hippocampe" -> {
                    UseDatabase.updateQuery(
                            String.format("DELETE FROM Obs_Hippocampe WHERE obsH = %d;",
                                    Integer.parseInt(id)));
                }
                case "Loutre" -> {
                    UseDatabase.updateQuery(
                            String.format("DELETE FROM Obs_Loutre WHERE ObsL = %d;",
                                    Integer.parseInt(id)));
                }
            }

            UseDatabase.updateQuery(String.format("DELETE FROM Observation WHERE idObs = %d;",
                    Integer.parseInt(id)));

            data.setAdmin(true);
            Main.switchScene("Success", this.deleteButton, data);

        } catch (SQLException e) {
            Main.showPopup("Une erreur est survenue lors de la suppression de l'observation.", this.deleteButton, true);
            e.printStackTrace();
        }

    }

    @Override
    /**
     * Initialise the scene
     *
     * @param url            the url of the page
     * @param resourceBundle the resource bundle of the page
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
