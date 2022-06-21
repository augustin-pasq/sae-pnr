package controleur;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import modele.donnee.EspeceObservee;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Controller for the Species Management page
 *
 * @author Groupe SAE PNR 1D1
 */
public class SpeciesManagementController extends InteractivePage {
    private final HashMap<Button, EspeceObservee> especes = new HashMap<>();
    private final HashMap<Button, EspeceObservee> especeDelete = new HashMap<>();
    @FXML
    private VBox speciesContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        for (EspeceObservee espece : EspeceObservee.values()) {
            Button species = createButton(espece);
            Button delete = createDeleteButton();
            especes.put(species, espece);
            especeDelete.put(delete, espece);

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setMaxWidth(1240.0);
            anchorPane.getChildren().addAll(species, delete);
            speciesContainer.getChildren().add(anchorPane);

            AnchorPane.setLeftAnchor(species, 338.0);
            AnchorPane.setRightAnchor(species, 338.0);
            AnchorPane.setRightAnchor(delete, 338.0);
        }

        speciesContainer.getChildren().add(createAddSpeciesButton());
    }

    private Button createButton(@NotNull EspeceObservee espece) {
        Button button = new Button(espece.toString().toUpperCase());
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

    private Button createDeleteButton() {
        ImageView img = new ImageView();
        try {
            img.setImage(new Image(new FileInputStream("data/Red_Bin.png")));
        } catch (FileNotFoundException e) {
            System.err.println("Delete button image not found");
        }
        img.setFitHeight(57.0);
        img.setFitWidth(59.0);
        img.setPickOnBounds(true);
        img.setPreserveRatio(true);
        img.setId("delete");

        Button button = new Button();
        button.setId("bin");
        button.setGraphic(img);
        button.setOnAction(e -> {
            Main.switchScene("DeletationConfirm", button);
        });

        return button;
    }

    private AnchorPane createAddSpeciesButton() {
        TextField addField = new TextField();
        addField.setId("add");
        addField.setAlignment(Pos.CENTER);
        addField.setLayoutX(338.0);
        addField.prefHeight(76.0);
        addField.setMinHeight(80);
        addField.prefWidth(565.0);
        addField.setPromptText("SAISIR UNE ESPECE");
        addField.setFont(new Font("DejaVu Sans Bold", 20.0));

        Button addButton = new Button();
        addButton.setId("addButton");
        addButton.setLayoutX(951.0);
        addButton.setMnemonicParsing(false);
        addButton.setPrefHeight(78.0);
        addButton.setPrefWidth(88.0);
        ImageView img = new ImageView();
        try {
            img.setImage(new Image(new FileInputStream("data/Button.png")));
        } catch (FileNotFoundException e) {
            System.err.println("Add button image not found");
        }
        img.setFitHeight(44.0);
        img.setFitWidth(49.0);
        img.setPickOnBounds(true);
        img.setPreserveRatio(true);
        addButton.setGraphic(img);
        addButton.setOnAction(this::addSpecies);

        AnchorPane add = new AnchorPane();
        add.setMaxWidth(1240.0);
        add.setPrefWidth(1240.0);
        add.getChildren().addAll(addField, addButton);

        AnchorPane.setLeftAnchor(addField, 338.0);
        AnchorPane.setRightAnchor(addField, 338.0);
        AnchorPane.setRightAnchor(addButton, 338.0);

        return add;
    }

    private void deleteSpecies(final Event event) {
        Button target = (Button) event.getTarget();
        System.out.println("Deleting " + this.especeDelete.get(target));
    }

    private void addSpecies(final Event event) {
        TextField target = (TextField) event.getTarget();
        System.out.println("Adding " + target.getText());
    }
}
