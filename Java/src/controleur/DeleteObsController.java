package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for the DeleteLoutreObs page
 *
 * @author Groupe SAE PNR 1D1
 */
public class DeleteObsController extends InteractivePage {

    private static ArrayList<ArrayList<String>> observation;
    private static String espece = "unset";
    private String id;
    private String lastName;
    private String firstName;
    @FXML
    private Label especeLabel;
    @FXML
    private VBox container;

    public static void setObs(ArrayList<ArrayList<String>> obs) {
        observation = obs;
    }

    public static void setEspece(String e) {
        espece = e;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        this.id = observation.get(1).get(0);
        if (espece.equals("Chouette")) {
            espece = observation.get(1).get(3);
        }

        this.isAdmin = true;
        this.especeLabel.setText(espece);
        for (int i = 0; i < observation.get(0).size(); i++) {
            HBox btn = createButton(observation.get(0).get(i), observation.get(1).get(i));
            this.container.getChildren().add(btn);

            if (observation.get(0).get(i).equals("nom")) {
                this.lastName = observation.get(1).get(i);
            } else if (observation.get(0).get(i).equals("prenom")) {
                this.firstName = observation.get(1).get(i);
            }
        }
    }

    private HBox createButton(String title, String value) {
        title = parseTitle(title);
        Label titleLabel = new Label(title);
        titleLabel.setPrefHeight(16);
        titleLabel.setPrefWidth(254);
        titleLabel.setFont(new Font("Berlin Sans FB", 23));
        titleLabel.setPadding(new Insets(0, -10, 0, 0));

        Label valueLabel = new Label(value);
        valueLabel.setPrefHeight(16);
        valueLabel.setPrefWidth(254);
        valueLabel.setFont(new Font("Berlin Sans FB", 23));
        valueLabel.setPadding(new Insets(0, -10, 0, 0));

        HBox hbox = new HBox();
        hbox.setId("hBox");
        hbox.setAlignment(Pos.CENTER);
        hbox.setPrefHeight(100);
        hbox.setPrefWidth(1078);
        hbox.getChildren().addAll(titleLabel, valueLabel);

        return hbox;
    }

    private String parseTitle(String title) {
        StringBuilder formattedTitle = new StringBuilder();

        if (title.matches("(O|o)bs.")) {
            formattedTitle.append("ID");
        } else {
            formattedTitle.append(title.substring(0, 1).toUpperCase());
            title = title.substring(1);
            for (String s : title.split("")) {
                if (s.equals("_")) {
                    formattedTitle.append(" ");
                } else if (s.toUpperCase().equals(s)) {
                    formattedTitle.append(" ");
                    formattedTitle.append(s);
                } else {
                    formattedTitle.append(s);
                }
            }

        }

        return formattedTitle.toString();
    }

    /**
     * Validate the data and insert it into the database
     *
     * @param event The event that triggered the method
     */
    @FXML
    public void delete(ActionEvent event) {
        Data userData = (Data) this.homeButton.getScene().getUserData();
        Data data = new Data(userData.get(0), userData.get(1), userData.get(2), espece, id, lastName, firstName);
        Main.switchScene("DeletionConfirm", this.homeButton, data);
    }

}
