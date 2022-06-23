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

public class DeletionConfirmController implements Initializable {
    @FXML
    private Button doNotDeleteButton;

    @FXML
    private Button deleteButton;

    @FXML
    public void abort(ActionEvent event) {
        Main.goBack(event);
    }

    @FXML
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

            Main.switchScene("Success", event);

        } catch (SQLException e) {
            Main.showPopup("Une erreur est survenue lors de la suppression de l'observation.", this.deleteButton, true);
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
