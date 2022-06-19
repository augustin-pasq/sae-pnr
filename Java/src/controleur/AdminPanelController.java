package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import modele.donnee.UseDatabase;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the Login page
 *
 * @author Groupe SAE PNR 1D1
 */
public class AdminPanelController implements Initializable {
    /**
     * The exit button
     */
    @FXML
    public Button exitButton;

    /**
     * The export button
     */
    @FXML
    public Button exportButton;

    /**
     * The import button
     */
    @FXML
    public Button importButton;

    /**
     * The species management button
     */
    @FXML
    public Button manageButton;

    /**
     * The observation deletion button
     */
    @FXML
    public Button deleteButton;

    /**
     * Export the data from a table to a CSV file
     *
     * @param table    the table to export
     * @param filename the name of the file
     */
    public static void exportData(String table, String filename) {
        ArrayList<ArrayList<String>> data = UseDatabase.selectQuery(String.format("SELECT * FROM %s", table));
        System.out.println(data);

        try (PrintWriter writer = new PrintWriter(filename)) {
            for (ArrayList<String> row : data) {
                writer.print(row.remove(0));
                for (String cell : row) {
                    writer.print(",");
                    writer.print(cell);
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Error while exporting data: " + e.getMessage() + " (Table most likely does not exist)");
        }
    }

    public static void main(String[] args) {
        exportData("user", "users.csv");
    }

    /**
     * The exit button action
     *
     * @param event the event that triggered the method
     */
    @FXML
    private void exit(final ActionEvent event) {
        Main.switchScene("Login", this.exitButton);
    }

    /**
     * The export button action
     *
     * @param event the event that triggered the method
     */
    public void exportAction(ActionEvent event) {
        // Redirect to a page where the user can select a file to export to and which table to export
        Main.switchScene("Export", this.exportButton);
    }

    /**
     * The import button action
     *
     * @param event the event that triggered the method
     */
    public void importAction(ActionEvent event) {
        // Redirect to a page where the user can select a file to import from and which table to import from
        Main.switchScene("Import", this.importButton);
    }

    /**
     * The species management button action
     *
     * @param event the event that triggered the method
     */
    public void manageSpecies(ActionEvent event) {
        Main.switchScene("SpeciesManagement", this.manageButton);
    }

    /**
     * The observation deletion button action
     *
     * @param event the event that triggered the method
     */
    public void deleteObs(ActionEvent event) {
        Main.switchScene("SpeciesChoice", this.deleteButton);
    }

    /**
     * Initialise the page with the export and exit buttons actions
     *
     * @param url            the url of the page
     * @param resourceBundle the resource bundle of the page
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
