package org.example.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.example.util.SceneManager;

public class DataVisualizationController {

    @FXML
    private void goBack() {

        SceneManager.switchScene(
                "MainScreen.fxml",
                "Sistema Acadêmico"
        );

    }

    @FXML
    private TableView<?> tableClasses;

    @FXML
    private TableView<?> tableAssessments;

    @FXML
    public void initialize() {

        tableClasses.setItems(
                FXCollections.observableArrayList()
        );

        tableAssessments.setItems(
                FXCollections.observableArrayList()
        );

    }

    @FXML
    private void refreshData() {

        // TODO carregar dados do sistema

    }

}