package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.example.util.SceneManager;

public class ReportController {

    @FXML
    private void goBack() {

        SceneManager.switchScene(
                "MainScreen.fxml",
                "Sistema Acadêmico"
        );

    }

    @FXML
    private TextArea txtReport;

    @FXML
    private void generateReport() {

        // TODO integrar ReportService

        txtReport.setText("Relatório gerado com sucesso.");

    }

}