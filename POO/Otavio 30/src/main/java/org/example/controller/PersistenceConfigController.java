package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import org.example.util.SceneManager;

public class PersistenceConfigController {

    @FXML
    private void goBack() {

        SceneManager.switchScene(
                "MainScreen.fxml",
                "Sistema Acadêmico"
        );

    }

    @FXML
    private ComboBox<String> cbPersistence;

    @FXML
    public void initialize() {

        cbPersistence.getItems().addAll(
                "JSON",
                "TXT",
                "XML"
        );

    }

    @FXML
    private void saveConfiguration() {

        try {

            // TODO integrar Repository

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Persistência alterada.");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }

    }

}