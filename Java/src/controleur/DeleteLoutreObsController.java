package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modele.donnee.UseDatabase;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * Controller for the DeleteLoutreObs page
 *
 * @author Groupe SAE PNR 1D1
 */
public class DeleteLoutreObsController extends InteractivePage {

    private static ArrayList<String> observation;

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label date;
    @FXML
    private Label heure;
    @FXML
    private Label coordX;
    @FXML
    private Label coordY;
    @FXML
    private Label commune;
    @FXML
    private Label lieudit;
    @FXML
    private Label indice;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        nom.setText(observation.get(4));
        prenom.setText(observation.get(5));
        date.setText(observation.get(6));
        heure.setText(observation.get(7));
        coordX.setText(observation.get(8));
        coordY.setText(observation.get(9));
        commune.setText(observation.get(1));
        lieudit.setText(observation.get(2));
        indice.setText(observation.get(3));
    }

    public static void setObs(int numObs) {
        observation = UseDatabase.selectQuery("SELECT * FROM vue_allFromLoutre WHERE ObsL = " + numObs + ";").get(1);
    }

    public void goBack(ActionEvent event) {
        Main.goBack(event);
    }

    /**
     * Validate the data and insert it into the database
     *
     * @param event The event that triggered the method
     */
    @FXML
    public void validate(ActionEvent event) {
        // Get the data from the fields
        String lastName = nom.getText().toUpperCase();
        String firstName = prenom.getText().toUpperCase();
        LocalDate dateLocalDate = LocalDate.parse(date.toString());
        String time = heure.getText();
        String lambertX = coordX.getText();
        String lambertY = coordY.getText();
        String communeString = commune.getText();
        String lieuDit = lieudit.getText();
        String indiceString = indice.getText();

        try {
 
            // Format the time as an object
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(time, formatter);

            // Get the observation's id 
            final Integer idObs;
            ArrayList<ArrayList<String>> observation = UseDatabase.selectQuery(String.format("SELECT idObs FROM Observation WHERE lieu_Lambert_X = '%s' AND lieu_Lambert_Y = '%s' AND dateObs = '%s' AND heureObs = '%s' LIMIT 1", lambertX, lambertY, Date.valueOf(dateLocalDate), Time.valueOf(localTime)));
            idObs = Integer.parseInt(observation.get(1).get(0));
            

            // Get a connection to the database for the prepared statements
            Connection conn = UseDatabase.MySQLConnection();

            // Delete the observation in the database with a prepared statement (mostly because of the time)
            String q = "DELETE FROM Observation WHERE idObs = (?)";
            PreparedStatement prep = conn.prepareStatement(q);
            prep.setInt(1, idObs);
            prep.executeUpdate();

            // Delete the link between the observation and the observer in the database
            String q2 = "DELETE FROM AObserve WHERE lobservation = (?)";
            PreparedStatement prep2 = conn.prepareStatement(q2);
            prep2.setInt(1, idObs);
            prep2.executeUpdate();

            // Delete the loutre linked to the observation in the database
            String q3 = "DELETE FROM Obs_Loutre WHERE ObsL = (?)";
            PreparedStatement prep3 = conn.prepareStatement(q3);
            prep3.setInt(1, idObs);
            prep3.executeUpdate();

            // If no exception has been thrown, the observation has been successfully added
            Main.showPopup("Observation supprimée correctement", event, false);

            // Close the connections
            prep.close();
            conn.close();
        } catch (IllegalArgumentException e) {
            // If one of the fields is invalid, show a popup with the error message
            Main.showPopup(e.getMessage(), event, true);
        } catch (SQLException e) {
            // If an SQL exception has been thrown, show a popup with an error message
            Main.showPopup("Une erreur est survenue au moment de la suppression des données", event, true);
            System.err.println(e.getMessage());
        } catch (NullPointerException e) {
            Main.showPopup("Merci de remplir tous les champs", event, true);
            e.printStackTrace();
        } catch (Exception e) {
            // Catch all other exceptions and show a popup with a generic error message
            Main.showPopup("Une erreur inconnue est survenue", event, true);
            e.printStackTrace();
        }
    }

}
