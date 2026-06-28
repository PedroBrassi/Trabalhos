package org.example.academic.system.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.academic.system.controller.AuthenticationController;
import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.model.User;

/**
 * Controller JavaFX da tela de login (TUS-2407).
 *
 * <p>Não contém lógica de autenticação: delega ao
 * {@link AuthenticationController} (AC6) e, em caso de sucesso, solicita ao
 * {@link ScreenNavigator} a exibição da tela principal por perfil (AC7).
 * Em caso de falha, exibe uma mensagem de erro (AC4).</p>
 */
public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField; // AC5: senha nunca em texto plano
    @FXML private Label errorLabel;

    private final AuthenticationController authenticationController;
    private final ScreenNavigator navigator;

    public LoginController(AuthenticationController authenticationController, ScreenNavigator navigator) {
        this.authenticationController = authenticationController;
        this.navigator = navigator;
    }

    /** Acionado pelo botão de login (AC2, AC3). */
    @FXML
    private void handleLogin() {
        errorLabel.setText("");
        String username = usernameField.getText() == null ? "" : usernameField.getText().trim();
        String password = passwordField.getText() == null ? "" : passwordField.getText();

        try {
            User user = authenticationController.authenticate(username, password); // AC6
            navigator.showMainScreen(user); // AC7
        } catch (AuthenticationException e) {
            errorLabel.setText("Invalid username or password."); // AC4
        }
    }
}
