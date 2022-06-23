package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import modele.donnee.EspeceObservee;
import modele.donnee.UseDatabase;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Controller for the Login page
 *
 * @author Groupe SAE PNR 1D1
 */
public class AdminPanelController implements Initializable {
    /**
     * The list of tables in the database
     */
    String[] tables = {"Lieu", "Observateur", "AObserve", "Obs_Hippocampe", "Obs_Loutre", "Obs_GCI", "Nid_GCI", "Chouette", "Obs_Chouette", "ZoneHumide", "Obs_Batracien", "Vegetation", "Lieu_Vegetation"};

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
     * The observation deletion button
     */
    @FXML
    public Button deleteButton;

    /**
     * Export the data from a table to a CSV file
     *
     * @param file The zip file to export to
     */
    public void exportData(File file) {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(file))) {
            for (String table : tables) {
                String dir = file.getName().replace(".zip", "");
                String filename = String.format("%s/%s.csv", dir, table);
                ZipEntry e = new ZipEntry(filename);
                out.putNextEntry(e);

                StringBuilder sb = new StringBuilder();
                ArrayList<ArrayList<String>> rows = UseDatabase.selectQuery(String.format("SELECT * FROM %s", table));
                for (ArrayList<String> row : rows) {
                    for (String cell : row) {
                        sb.append(cell);
                        sb.append(",");
                    }
                    sb.append("\n");
                }

                byte[] data = sb.toString().getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
            }

            Main.showPopup("Les données on été exportées correctement", this.exitButton, false);
        } catch (IOException | SQLException e) {
            Main.showPopup("Erreur lors de l'export", this.exitButton, true);
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Error while exporting data: " + e.getMessage() + " (Table most likely does not exist)");
        }
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
        Window window = this.exitButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        ZonedDateTime date = ZonedDateTime.now();
        String dateString = String.format("%s-%s-%s-%s:%s:%s", date.getDayOfMonth(), date.getMonthValue(), date.getYear(), date.getHour(), date.getMinute(), date.getSecond());

        //Set extension filter for csv files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ZIP files (*.zip)", "*.zip");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName("export_" + dateString + ".zip");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setTitle("Sélectionnez le dossier de destination");

        //Show save file dialog
        File file = fileChooser.showSaveDialog(window);

        if (file != null) {
            exportData(file);
        }
    }

    /**
     * The import button action
     *
     * @param event the event that triggered the method
     */
    public void importAction(ActionEvent event) {
        // Get the data passed from the previous scene
        Data data = (Data) ((Button) event.getSource()).getScene().getUserData();
        System.out.println(EspeceObservee.valueOf((String) data.get(0)));
    }

    /**
     * The species management button action
     *
     * @param event the event that triggered the method
     */
    public void manageSpecies(ActionEvent event) {
        Main.switchScene("SpeciesManagement", this.deleteButton);
    }

    /**
     * The observation deletion button action
     *
     * @param event the event that triggered the method
     */
    public void deleteObs(ActionEvent event) {
        Data data = new Data("Delete");
        data.setAdmin(true);
        Main.switchScene("SpeciesChoice", this.deleteButton, data);
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
