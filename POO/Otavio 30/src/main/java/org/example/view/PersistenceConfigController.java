package org.example.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import org.example.academic.system.controller.AcademicSystemController;

public class PersistenceConfigController {

    @FXML private ComboBox<String> comboPersistenceType;
    private final AcademicSystemController academicController = AcademicSystemController.getInstance();

    @FXML
    public void initialize() {
        comboPersistenceType.getItems().addAll("TXT", "XML", "JSON");
    }

    @FXML
    private void handleSaveConfig() {
        String selected = comboPersistenceType.getValue();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selecione uma opção.");
            alert.showAndWait();
            return;
        }

        try {
            academicController.configurePersistenceType(selected);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Formato alterado para " + selected);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Erro: " + e.getMessage());
            alert.showAndWait();
        }
    }
}