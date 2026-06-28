# Otavio 27 — Services, Controller, Testes e JavaFX Login

Parte do trabalho semestral correspondente às histórias **TUS-2397 a TUS-2407 e TUS-2414**.

## O que foi implementado

### Camada de Services (refatoração do Controller)
- **TUS-2397 — AssessmentService**: lógica de registro de avaliações extraída do
  controller/AcademicService. Encontra a turma, cria o tipo correto (1=Exam,
  2=PracticalAssignment, 3=Seminar, 4=Assignment), valida e adiciona. Tipo inválido
  ou turma inexistente não adicionam avaliação. Independente de teclado/menu.
- **TUS-2398 — PersistenceService** e **TUS-2399 — ReportService**: persistência e
  geração de relatórios isoladas em services dedicados.
- **TUS-2400 — Simplify AcademicSystemController**: o controller passou a injetar
  `ClassService` + `AssessmentService` + `PersistenceService` + `ReportService` e
  apenas delega, preservando as checagens de autorização (`requireRole`). O antigo
  `AcademicService` (que misturava turma + avaliação) foi removido para não duplicar
  lógica.

### Testes automatizados (`src/test/java`)
- **TUS-2401** `ClassServiceTest` — registra turma válida; armazena no repositório;
  dados inválidos lançam `AcademicSystemException`.
- **TUS-2402** `AssessmentServiceTest` — registra avaliação em turma existente; tipo
  inválido e código inexistente não adicionam.
- **TUS-2403** `PersistenceServiceTest` — salva com TXT (padrão), troca para XML e
  JSON; os arquivos gerados contêm os dados (`@TempDir`).
- **TUS-2404** `ReportServiceTest` — gera sumário, relatório de pesos e relatório de
  configuração de persistência.
- **TUS-2405** `AcademicSystemControllerTest` — registro de turma/avaliação por
  delegação e preservação das regras de autorização (PROFESSOR e anônimo não
  cadastram turma).

### Interface gráfica JavaFX
- **TUS-2406 — Infraestrutura JavaFX**: dependências `javafx-controls`/`javafx-fxml` e
  `javafx-maven-plugin` no `pom.xml`; ponto de entrada `gui/JavaFXMain` (Application)
  que **reutiliza** os controllers/services. A CLI (`Main`) continua funcionando.
- **TUS-2414 — AuthenticationController**: `controller/AuthenticationController` delega
  ao `AuthenticationService` e devolve o `User`, propagando exceções sem alterar o
  comportamento existente. A GUI não conhece a implementação de autenticação.
- **TUS-2407 — Tela de login JavaFX**: `gui/Login.fxml` + `gui/LoginController` com
  campo de usuário, `PasswordField` (senha nunca em texto plano), botão e label de
  erro. Em caso de sucesso navega para a tela principal por perfil; em caso de falha
  exibe mensagem de erro.

> A tela principal (`MainScreenView`) é um **placeholder mínimo de integração**: a tela
> completa baseada em perfil é a **TUS-2408 (Otavio 30)**, que pode substituí-la
> reutilizando `ScreenNavigator` e `AcademicSystemController`.

## Como rodar

Usuários padrão (quando não há `users.txt`): `admin/admin123` (ADMIN) e
`professor/prof123` (PROFESSOR).

```bash
# Testes
mvn test

# Aplicação de linha de comando
mvn -q compile exec:java   # ou rode org.example.academic.system.Main pela IDE

# Interface gráfica JavaFX
mvn javafx:run
```

> Requer Java 21+ e Maven. No IntelliJ, basta rodar a classe `Main` (CLI),
> `gui.JavaFXMain` (GUI) ou os testes em `src/test`.
