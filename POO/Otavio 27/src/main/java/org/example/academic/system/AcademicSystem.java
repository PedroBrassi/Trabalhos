package org.example.academic.system;

import org.example.academic.system.repository.InMemoryAcademicClassRepository;
import org.example.academic.system.repository.TxtUserRepository;
import org.example.academic.system.security.AuthenticationService;
import org.example.academic.system.security.AuthorizationService;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ClassService;
import org.example.academic.system.service.PersistenceService;
import org.example.academic.system.service.ReportService;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.view.ConsoleView;

/**
 * Classe raiz da aplicação: conecta todas as dependências e inicializa a CLI.
 */
public class AcademicSystem {

    public static void run() {
        // Infraestrutura
        InMemoryAcademicClassRepository memoryRepo = new InMemoryAcademicClassRepository();
        TxtUserRepository userRepository = new TxtUserRepository("users.txt");

        // Segurança
        AuthenticationService authService = new AuthenticationService(userRepository);
        @SuppressWarnings("unused")
        AuthorizationService authorizationService = new AuthorizationService(); // disponível para extensões futuras

        // Serviços (TUS-2396, TUS-2397, TUS-2398, TUS-2399)
        PersistenceService persistenceService = new PersistenceService(memoryRepo);
        ClassService classService = new ClassService(memoryRepo);
        AssessmentService assessmentService = new AssessmentService(memoryRepo);
        ReportService reportService = new ReportService();

        // Controller (TUS-2370, TUS-2400)
        AcademicSystemController controller = new AcademicSystemController(
                classService, assessmentService, authService, persistenceService, reportService);

        // View — responsável apenas por startup, auth, menus e E/S (TUS-2370)
        ConsoleView view = new ConsoleView(authService, controller);
        view.start();
    }
}
