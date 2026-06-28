package org.example.academic.system.gui;

import org.example.academic.system.model.User;

/**
 * Contrato de navegação entre telas JavaFX (TUS-2407).
 * Permite que os controllers de tela troquem de cena sem conhecer a
 * implementação concreta da aplicação. Reutilizável pelas demais telas
 * (parte do Otavio 30).
 */
public interface ScreenNavigator {

    /** Exibe a tela de login. */
    void showLoginScreen();

    /** Exibe a tela principal baseada no perfil do usuário autenticado (AC7). */
    void showMainScreen(User user);
}
