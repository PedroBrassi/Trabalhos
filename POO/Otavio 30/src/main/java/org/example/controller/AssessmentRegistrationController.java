package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import org.example.util.SceneManager;

public class AssessmentRegistrationController {

    @FXML
    private void goBack() {

        SceneManager.switchScene(
                "MainScreen.fxml",
                "Sistema Acadêmico"
        );

    }

    @FXML
    private ComboBox<String> cbType;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtWeight;

    @FXML
    private TextField txtMaximumScore;

    @FXML
    public void initialize() {

        cbType.getItems().addAll(
                "Exam",
                "Assignment",
                "Seminar",
                "Practical Assignment"
        );

    }

    @FXML
    private void registerAssessment() {

        try {

            // TODO integrar AcademicSystemController

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Avaliação cadastrada!");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }

    }

}