package org.example.academic.system.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.controller.AuthenticationController;
import org.example.academic.system.model.User;
import org.example.academic.system.repository.InMemoryAcademicClassRepository;
import org.example.academic.system.repository.TxtUserRepository;
import org.example.academic.system.security.AuthenticationService;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ClassService;
import org.example.academic.system.service.PersistenceService;
import org.example.academic.system.service.ReportService;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Ponto de entrada da interface gráfica JavaFX (TUS-2406).
 *
 * <p>Reutiliza os controllers e services existentes — não contém regra de
 * negócio acadêmica (AC5, AC6). A aplicação de linha de comando
 * ({@code org.example.academic.system.Main}) continua funcionando de forma
 * independente (AC4). Implementa {@link ScreenNavigator} para alternar entre
 * a tela de login e a tela principal.</p>
 */
public class JavaFXMain extends Application implements ScreenNavigator {

    private Stage primaryStage;

    // Camada de serviços/controllers reutilizada (mesmo wiring da CLI)
    private AuthenticationController authenticationController;
    private AcademicSystemController academicSystemController;

    @Override
    public void init() {
        InMemoryAcademicClassRepository memoryRepo = new InMemoryAcademicClassRepository();
        TxtUserRepository userRepository = new TxtUserRepository("users.txt");

        AuthenticationService authService = new AuthenticationService(userRepository);

        ClassService classService = new ClassService(memoryRepo);
        AssessmentService assessmentService = new AssessmentService(memoryRepo);
        PersistenceService persistenceService = new PersistenceService(memoryRepo);
        ReportService reportService = new ReportService();

        this.authenticationController = new AuthenticationController(authService); // TUS-2414
        this.academicSystemController = new AcademicSystemController(
                classService, assessmentService, authService, persistenceService, reportService);
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        stage.setTitle("Academic System");
        showLoginScreen();
        stage.show();
    }

    @Override
    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/academic/system/gui/Login.fxml"));
            // Injeta o controller com suas dependências (sem fx:controller no FXML)
            loader.setController(new LoginController(authenticationController, this));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load Login.fxml", e);
        }
    }

    /**
     * Exibe a tela principal por perfil (AC7). Implementação mínima de
     * integração — a tela completa baseada em perfil é a TUS-2408 (Otavio 30),
     * que pode substituir este método reutilizando o mesmo {@link ScreenNavigator}
     * e o {@link AcademicSystemController}.
     */
    @Override
    public void showMainScreen(User user) {
        Parent root = MainScreenView.build(user, academicSystemController, authenticationController, this);
        primaryStage.setScene(new Scene(root, 420, 320));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
