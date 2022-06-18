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

public class AdminPanelController implements Initializable {
    @FXML
    public Button exportButton;

    @FXML
    public Button exitButton;

    @FXML
    private void exit(final ActionEvent event) {
        Main.switchScene("Login", this.exitButton);
    }

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

    public void export(ActionEvent event) {
        // Redirect to a page where the user can select a file to export to and which table to export
        Main.switchScene("Export", this.exportButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public static void main(String[] args) {
        exportData("user", "users.csv");
    }
}
