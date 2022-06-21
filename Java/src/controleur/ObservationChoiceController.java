package controleur;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import modele.donnee.UseDatabase;
import java.net.URL;
import java.util.ResourceBundle;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class ObservationChoiceController extends InteractivePage {

    @FXML
    private VBox scrollPaneContainer;
    
    public ArrayList<ArrayList<String>> allObservations;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        this.allObservations = UseDatabase.selectQuery("SELECT * FROM vue_allFromBatracien");
        VBox observationsContainer = new VBox();
        for (int i = 1; i < allObservations.size(); i++) {
            Button obs = createButton(allObservations.get(i));
            observationsContainer.getChildren().add(obs);
        }
        ScrollPane scrollPane = new ScrollPane(observationsContainer);
        scrollPaneContainer.getChildren().add(scrollPane);
    }

    public void setAllObservations(String espece) {
        this.allObservations = UseDatabase.selectQuery("SELECT * FROM vue_allFromBatracien");
    }

    private Button createButton(@NotNull ArrayList<String> observation) {
        Button button = new Button(observation.toString().toUpperCase());
        button.setFont(new Font("DejaVu Sans Bold", 20));
        button.setAlignment(Pos.CENTER);
        button.setContentDisplay(ContentDisplay.CENTER);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setPrefWidth(1240.0);
        button.setPrefHeight(78.0);
        button.setLayoutX(338.0);
        button.setMnemonicParsing(false);
        button.setId("species");
        return button;
    }
}
