package controleur;

import modele.donnee.UseDatabase;
import java.net.URL;
import java.util.ResourceBundle;

import org.jetbrains.annotations.NotNull;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class ObservationChoiceController extends InteractivePage {

    public ArrayList<ArrayList<String>> allObservations;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        
        for (ArrayList<String> observation : allObservations) {
            Button species = createButton(observation);}
            /*
            especes.put(species, espece);
            especeDelete.put(delete, espece);

            anchorPane.setMaxWidth(1240.0);
            anchorPane.getChildren().addAll(species, delete);
            speciesContainer.getChildren().add(anchorPane);

        }
        
        speciesContainer.getChildren().add(createAddSpeciesButton());*/
    }

    public void setAllObservations(String espece) {
        this.allObservations = UseDatabase.selectQuery("SELECT * FROM vue_allFrom" + espece);
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
