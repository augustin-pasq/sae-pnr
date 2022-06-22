package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
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

    private static ArrayList<ArrayList<String>> allObservations;

    private static String espece;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

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

    public void getData(ActionEvent event) {
        Button target = (Button) event.getSource();
        Data data = (Data) target.getScene().getUserData();

        System.out.println(Arrays.toString(data.getAll()));
    }

    private Button createButton(@NotNull ArrayList<String> observation) {
        Button button = new Button();
        if (espece.equals("Chouette")) {
            button.setText((observation.get(observation.size() - 4) + "   -   " + observation.get(observation.size() - 3) + "   -   " + observation.get(observation.size() - 2) + "   -   " + observation.get(observation.size() - 1)).toUpperCase());
        } else {
            button.setText((observation.get(observation.size() - 4) + "   -   " + observation.get(observation.size() - 3) + "   -   " + observation.get(observation.size() - 2) + "   -   " + observation.get(observation.size() - 1) + "   -   " + observation.get(observation.size() - 5) + " " + observation.get(observation.size() - 6)).toUpperCase());
        }
        button.setFont(new Font("DejaVu Sans Bold", 20));
        button.setAlignment(Pos.CENTER);
        button.setContentDisplay(ContentDisplay.CENTER);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setPrefWidth(1225);
        button.setPrefHeight(76);
        button.setMnemonicParsing(false);
        button.setId("observation");
        button.setOnAction(e -> {
            switch (espece){
                case "Batracien":
                    ConsultBatracienObsControler.setObs(Integer.parseInt(observation.get(0)));
                    Main.switchScene("ConsultBatracienObs", button);
                    break;
                case "Chouette":
                    ConsultChouetteObsControler.setObs(Integer.parseInt(observation.get(3)));
                    Main.switchScene("ConsultChouetteObs", button);
                    break;
                case "GCI":
                    ConsultGCIObsControler.setObs(Integer.parseInt(observation.get(0)));
                    Main.switchScene("ConsultGCIObs", button);
                    break;
                case "Hippocampe":
                    ConsultHippocampeObsControler.setObs(Integer.parseInt(observation.get(0)));
                    Main.switchScene("ConsultHippocampeObs", button);
                    break;
                case "Loutre":
                    ConsultLoutreObsControler.setObs(Integer.parseInt(observation.get(0)));
                    Main.switchScene("ConsultLoutreObs", button);
                    break;
            }
            
        });
        return button;
    }
}
