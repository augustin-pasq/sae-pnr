package controleur;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import modele.donnee.EspeceObservee;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SpeciesManagementController extends InteractivePage {
    @FXML
    private VBox speciesContainer;

    private final HashMap<Button, EspeceObservee> especes = new HashMap<>();

    private final HashMap<Button, EspeceObservee> especeDelete = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        for (EspeceObservee espece : EspeceObservee.values()) {
            Button species = createButton(espece);
            Button delete = createDeleteButton();
            especes.put(species, espece);
            especeDelete.put(delete, espece);

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.getChildren().addAll(species, delete);
            speciesContainer.getChildren().add(anchorPane);

            AnchorPane.setLeftAnchor(species, 338.0);
            AnchorPane.setRightAnchor(species, 338.0);
        }
    }

    private Button createButton(EspeceObservee espece) {
        Button button = new Button(espece.toString().toUpperCase());
        button.setFont(new Font("DejaVu Sans Bold", 20));
        button.setAlignment(Pos.CENTER);
        button.setContentDisplay(ContentDisplay.CENTER);
        button.setTextAlignment(TextAlignment.CENTER);
        button.setPrefWidth(1240.0);
        button.setPrefHeight(78.0);
        button.setLayoutX(338.0);
        button.setMnemonicParsing(false);
        return button;
    }

    private Button createDeleteButton() {
        ImageView img = new ImageView("../../data/Red_Bin.png");
        img.setFitHeight(57.0);
        img.setFitWidth(59.0);
        img.setPickOnBounds(true);
        img.setPreserveRatio(true);
        img.setId("delete");

        Button button = new Button();
        button.setGraphic(img);

        return button;
    }
}
