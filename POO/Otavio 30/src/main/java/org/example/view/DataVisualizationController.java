package org.example.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.academic.system.controller.AcademicSystemController;

public class DataVisualizationController {

    // Usando Object por enquanto para não gerar erro enquanto os outros não finalizam (mudar isso dps)
    @FXML private TableView<Object> tableClasses;
    @FXML private TableColumn<Object, String> colClassCode;
    @FXML private TableColumn<Object, String> colClassTitle;

    @FXML private TableView<Object> tableAssessments;
    @FXML private TableColumn<Object, String> colAsmntType;
    @FXML private TableColumn<Object, Double> colAsmntValue;
    @FXML private TableColumn<Object, Double> colAsmntWeight;

    private final AcademicSystemController academicController = AcademicSystemController.getInstance();

    @FXML
    public void initialize() {
        // Define quais propriedades os objetos do Pedro e da Julia devem expor (getters)
        colClassCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colClassTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

        colAsmntType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colAsmntValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colAsmntWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));

        loadDataFromBackend();
    }

    @FXML
    private void loadDataFromBackend() {
        try {
            tableClasses.getItems().clear();
            tableAssessments.getItems().clear();

            // Puxa as listas diretamente do controlador central (TUS-2413)
            tableClasses.getItems().addAll(academicController.getAllClasses());
            tableAssessments.getItems().addAll(academicController.getAllAssessments());
        } catch (Exception e) {
            System.err.println("Erro ao buscar dados: " + e.getMessage());
        }
    }
}