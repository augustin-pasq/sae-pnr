package controleur;

import javafx.event.ActionEvent;
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
import modele.donnee.UseDatabase;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ObservationChoiceController extends InteractivePage {

    @FXML
    private VBox scrollPaneContainer;

    @FXML
    private HBox legendeContainer;

    private static ArrayList<ArrayList<String>> allObservations;

    private static String espece;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        Label legende = new Label();
        if (espece.equals("Chouette")) {
            legende.setText("   Date   -    Heure");
        } else {
            legende.setText("   Date             -   Heure      -   Observateur");
        }
        legende.setPrefWidth(1225);
        legende.setPrefHeight(50);
        legende.setFont(new Font("DejaVu Sans Bold", 20));
        legendeContainer.getChildren().add(legende);


        VBox observationsContainer = new VBox(10);
        for (int i = 1; i < allObservations.size(); i++) {
            Button obs = createButton(allObservations.get(i));
            observationsContainer.getChildren().add(obs);
        }

        ScrollPane scrollPane = new ScrollPane(observationsContainer);
        scrollPaneContainer.getChildren().add(scrollPane);
    }

    public static void setAllObservations(String esp) {
        espece = esp;
        allObservations = UseDatabase.selectQuery("SELECT * FROM vue_allFrom" + esp);
    }

    public static void setAllObservations(String esp, String restriction){
        espece = esp;
        allObservations = UseDatabase.selectQuery("SELECT * FROM vue_allFrom" + esp + " " + restriction);
    }

    public void getData(ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();

        System.out.println(Arrays.toString(data.getAll()));
    }

    private Button createButton(@NotNull ArrayList<String> observation) {
        Button button = new Button();
        if (espece.equals("Chouette")) {
            button.setText((observation.get(observation.size() - 4) + "   -   " + observation.get(observation.size() - 3)).toUpperCase());
        } else {
            button.setText((observation.get(observation.size() - 4) + "   -   " + observation.get(observation.size() - 3) + "   -   " + observation.get(observation.size() - 5) + " " + observation.get(observation.size() - 6)).toUpperCase());
        }
        button.setFont(new Font("DejaVu Sans Bold", 20));
        button.setAlignment(Pos.CENTER_LEFT);
        button.setContentDisplay(ContentDisplay.LEFT);
        button.setTextAlignment(TextAlignment.LEFT);
        button.setPrefWidth(1225);
        button.setPrefHeight(76);
        button.setMnemonicParsing(false);
        button.setId("observation");
        button.setOnAction(e -> {
            Data data = (Data) this.homeButton.getScene().getUserData();
            switch ((String) data.get(0)) {
                case "Consult" -> {
                    int i = espece.equals("Chouette") ? 3 : 0;
                    ConsultBatracienObsController.setObs(Integer.parseInt(observation.get(i)));
                    Main.switchScene("Consult" + espece + "Obs", button, data);
                }
                case "Edit" -> {

                }
            }
            
        });
        return button;
    }
}
