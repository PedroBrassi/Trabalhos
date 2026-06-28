package org.example.controller;

import javafx.fxml.FXML;
import org.example.util.SceneManager;

public class MainScreenController {

    @FXML
    private void openClassRegistration() {
        SceneManager.switchScene("ClassRegistration.fxml", "Cadastro de Turmas");
    }

    @FXML
    private void openAssessmentRegistration() {
        SceneManager.switchScene("AssessmentRegistration.fxml", "Cadastro de Avaliações");
    }

    @FXML
    private void openPersistence() {
        SceneManager.switchScene("PersistenceConfig.fxml", "Configuração de Persistência");
    }

    @FXML
    private void openVisualization() {
        SceneManager.switchScene("DataVisualization.fxml", "Visualização");
    }

    @FXML
    private void openReports() {
        SceneManager.switchScene("Report.fxml", "Relatórios");
    }

}