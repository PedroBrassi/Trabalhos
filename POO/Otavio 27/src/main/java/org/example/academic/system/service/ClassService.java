package org.example.academic.system.service;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.repository.AcademicClassRepository;
import org.example.academic.system.validation.DomainValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Lógica de negócio para gerenciamento de turmas (TUS-2396).
 * Extraída do antigo AcademicService para isolar a responsabilidade de turmas
 * das avaliações (TUS-2397). Independente de teclado/menu.
 */
public class ClassService {

    private static final Logger log = LoggerFactory.getLogger(ClassService.class);
    private final AcademicClassRepository repository;

    public ClassService(AcademicClassRepository repository) {
        this.repository = repository;
    }

    /**
     * Cadastra uma nova turma após validação (US-2363).
     * Dados inválidos resultam em AcademicSystemException via DomainValidator (TUS-2401).
     */
    public void registerClass(String code, String title) {
        AcademicClass academicClass = new AcademicClass(code, title);
        DomainValidator.validate(academicClass);
        repository.save(academicClass);
        log.info("Class registered: code='{}', title='{}'", code, title);
    }

    public Optional<AcademicClass> findByCode(String code) {
        return repository.findByCode(code);
    }

    public List<AcademicClass> listAllClasses() {
        return repository.findAll();
    }
}
