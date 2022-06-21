package controleur;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;


import java.net.URL;
import java.util.ResourceBundle;


public class DataLoutreController extends InteractivePage {

    ObservableList<String> indiceList = FXCollections.observableArrayList("Positif", "Négatif", "Non prospection");

    @FXML
    private ComboBox<String> indiceComboBox;

    @Override
    public void initialize(URL url, ResourceBundle ressourceBundle) {
        super.initialize(url, ressourceBundle);
        this.isAdmin = true;
        indiceComboBox.setItems(indiceList);
    }
}
