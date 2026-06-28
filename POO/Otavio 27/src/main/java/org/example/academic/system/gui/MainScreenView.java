package org.example.academic.system.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.controller.AuthenticationController;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

/**
 * Tela principal mínima por perfil (placeholder de integração — TUS-2407 AC7).
 *
 * <p>A tela completa baseada em perfil é responsabilidade da TUS-2408 (Otavio 30).
 * Esta versão apenas confirma o login, mostra opções de acordo com o perfil e
 * oferece logout, retornando à tela de login.</p>
 */
final class MainScreenView {

    private MainScreenView() {
    }

    static Parent build(User user,
                        AcademicSystemController controller,
                        AuthenticationController authenticationController,
                        ScreenNavigator navigator) {
        VBox root = new VBox(12.0);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(24.0));

        Label welcome = new Label("Welcome, " + user.getUsername() + " [" + user.getRole() + "]");
        welcome.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        root.getChildren().add(welcome);

        if (user.getRole() == Role.ADMIN) {
            root.getChildren().add(new Label("Administrator options available"));
        } else {
            root.getChildren().add(new Label("Professor options available"));
        }

        Button logout = new Button("Logout");
        logout.setOnAction(e -> {
            authenticationController.logout();
            navigator.showLoginScreen();
        });
        root.getChildren().add(logout);

        return root;
    }
}
