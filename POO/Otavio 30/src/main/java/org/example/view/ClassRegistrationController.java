package org.example.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.example.academic.system.controller.AcademicSystemController;

public class ClassRegistrationController {

    @FXML private TextField txtClassCode;
    @FXML private TextField txtClassTitle;

    // Conecta direto com o controlador de negócios (TUS-2409)
    private final AcademicSystemController academicController = AcademicSystemController.getInstance();

    @FXML
    private void handleSaveClass() {
        String code = txtClassCode.getText().trim();
        String title = txtClassTitle.getText().trim();

        if (code.isEmpty() || title.isEmpty()) {
            showAlert(AlertType.WARNING, "Validação", "Preencha todos os campos.");
            return;
        }

        try {
            // Chamada direta do método do backend exigido nos critérios de aceitação
            academicController.registerClass(code, title);
            showAlert(AlertType.INFORMATION, "Sucesso", "Turma salva com sucesso!");
            txtClassCode.clear();
            txtClassTitle.clear();
        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Erro", e.getMessage());
        }
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
