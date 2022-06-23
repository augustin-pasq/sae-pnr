package controleur;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Insets;
import modele.donnee.UseDatabase;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the ObservationChoice page
 */
public class ObservationChoiceController extends InteractivePage {

    /*
     * The select query results
     */
    private static ArrayList<ArrayList<String>> allObservations;

    /*
     * The specie
     */
    private static String espece;

    /**
     * The container which contains the scroll pane
     */
    @FXML
    private VBox scrollPaneContainer;

    /**
     * The contains which contains the columns description
     */
    @FXML
    private HBox legendeContainer;

    /**
     * Select the observations from the specie
     * 
     * @param esp the specie
     */
    public static void setAllObservations(String esp) {
        espece = esp;
        try {
            allObservations = UseDatabase.selectQuery("SELECT * FROM vue_allFrom" + esp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Select the observations from the specie with the restriction
     * 
     * @param esp the specie
     * @param restriction the restriction
     */
    public static void setAllObservations(String esp, String restriction) {
        espece = esp;
        try {
            allObservations = UseDatabase.selectQuery("SELECT * FROM vue_allFrom" + esp + " " + restriction);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inherited method from Initializable
     * @see javafx.fxml.Initializable
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        Label legende = new Label();
        if (espece.equals("Chouette")) {
            legende.setText(" Id    -   Date    -   Heure    -   Observateur");
        } else {
            legende.setText("   Date             -   Heure      -   Observateur");
        }
        legende.setPrefWidth(1225);
        legende.setPrefHeight(50);
        legende.setFont(new Font("DejaVu Sans Bold", 20));
        legendeContainer.getChildren().add(legende);


        VBox observationsContainer = new VBox(10);
        observationsContainer.setPadding(new Insets(0, 200, 0, 0));
        for (int i = 1; i < allObservations.size(); i++) {
            ArrayList<ArrayList<String>> observation = new ArrayList<>();
            observation.add(allObservations.get(0));
            observation.add(allObservations.get(i));
            Button obs = createButton(observation);
            observationsContainer.getChildren().add(obs);
        }

        ScrollPane scrollPane = new ScrollPane(observationsContainer);
        scrollPaneContainer.getChildren().add(scrollPane);
    }

    
    private Button createButton(@NotNull ArrayList<ArrayList<String>> observation) {
        Button button = new Button();
        ArrayList<String> obs = observation.get(1);
        if (espece.equals("Chouette")) {
            button.setText(obs.get(3) + "   -   " + obs.get(obs.size() - 4) + "   -   " + (obs.get(obs.size() - 4) + "   -   " + obs.get(obs.size() - 3)).toUpperCase());
        } else {
            button.setText((obs.get(obs.size() - 4) + "   -   " + obs.get(obs.size() - 3) + "   -   " + obs.get(obs.size() - 5) + " " + obs.get(obs.size() - 6)).toUpperCase());
        }
        button.setFont(new Font("DejaVu Sans Bold", 20));
        button.setAlignment(Pos.CENTER_LEFT);
        button.setContentDisplay(ContentDisplay.LEFT);
        button.setTextAlignment(TextAlignment.LEFT);
        button.setPrefWidth(1100);
        button.setPrefHeight(76);
        button.setMnemonicParsing(false);
        button.setId("observation");
        button.setOnAction(e -> {
            Data data = (Data) this.homeButton.getScene().getUserData();
            switch ((String) data.get(0)) {
                case "ConsultData" -> {
                    switch (espece) {
                        case "Batracien" -> {
                            ConsultBatracienObsController.setObs(Integer.parseInt(observation.get(1).get(0)));
                            Main.switchScene("ConsultBatracienObs", button, data);
                        }
                        case "Chouette" -> {
                            ConsultChouetteObsController.setObs(Integer.parseInt(observation.get(1).get(3)));
                            Main.switchScene("ConsultChouetteObs", button, data);
                        }
                        case "Hippocampe" -> {
                            ConsultHippocampeObsController.setObs(Integer.parseInt(observation.get(1).get(0)));
                            Main.switchScene("ConsultHippocampeObs", button, data);
                        }
                        case "Loutre" -> {
                            ConsultLoutreObsController.setObs(Integer.parseInt(observation.get(1).get(0)));
                            Main.switchScene("ConsultLoutreObs", button, data);
                        }
                        case "GCI" -> {
                            ConsultGCIObsController.setObs(Integer.parseInt(observation.get(1).get(0)));
                            Main.switchScene("ConsultGCIObs", button, data);
                        }
                    }
                }
                case "Edit" -> {
                    switch (espece) {
                        case "Batracien" -> {
                            EditBatracienObsController.setObs(Integer.parseInt(observation.get(1).get(0)));
                            Main.switchScene("EditBatracienObs", button, data);
                        }
                        case "Chouette" -> {
                            EditChouetteObsController.setObs(Integer.parseInt(observation.get(1).get(3)));
                            Main.switchScene("EditChouetteObs", button, data);
                        }
                        case "Hippocampe" -> {
                            EditHippocampeObsController.setObs(Integer.parseInt(observation.get(1).get(0)));
                            Main.switchScene("EditHippocampeObs", button, data);
                        }
                        case "Loutre" -> {
                            EditLoutreObsController.setObs(Integer.parseInt(observation.get(1).get(0)));
                            Main.switchScene("EditLoutreObs", button, data);
                        }
                        case "GCI" -> {
                            EditGCIObsController.setObs(Integer.parseInt(observation.get(1).get(0)));
                            Main.switchScene("EditGCIObs", button, data);
                        }
                    }
                }
                case "Delete" -> {
                    data.setAdmin(true);
                    DeleteObsController.setObs(observation);
                    DeleteObsController.setEspece(espece);
                    Main.switchScene("DeleteObs", button, data);
                }
            }
        });
        return button;
    }
}