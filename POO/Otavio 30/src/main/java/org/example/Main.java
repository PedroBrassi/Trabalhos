package org.example;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Localiza e carrega o arquivo FXML usando o caminho correto
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/academic/system/view/ClassRegistration.fxml"));
            Parent root = loader.load();

            // Configura a janela principal do JavaFX
            primaryStage.setTitle("Sistema Acadêmico - Cadastro de Turmas");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Erro ao iniciar a interface gráfica: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // O método principal que o Java procura para iniciar tudo
    public static void main(String[] args) {
        launch(args);
    }
}