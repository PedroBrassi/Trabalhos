package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import org.example.util.SceneManager;

public class ClassRegistrationController {

    @FXML
    private void goBack() {

        SceneManager.switchScene(
                "MainScreen.fxml",
                "Sistema Acadêmico"
        );

    }

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtProfessor;

    @FXML
    private TextField txtSemester;

    @FXML
    private void registerClass() {

        String code = txtCode.getText();
        String name = txtName.getText();
        String professor = txtProfessor.getText();
        String semester = txtSemester.getText();

        try {

            // TODO integrar AcademicSystemController

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Turma cadastrada com sucesso!");
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();

        }

    }

}