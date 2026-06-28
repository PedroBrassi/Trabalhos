package org.example.academic.system.service;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.logging.AppLogger;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.repository.ClassRepository;

import java.util.List;
import java.util.logging.Logger;

/**
 * TUS-2396 - ClassService.
 * Concentra a logica de cadastro de turma; o controller so delega pra ca.
 */
public class ClassService {

    private static final Logger log = AppLogger.get(ClassService.class);

    private final ClassRepository repository;

    public ClassService(ClassRepository repository) {
        this.repository = repository;
    }

    public AcademicClass registerClass(String code, String title) {

        if (code == null || code.isBlank()) {
            throw new AcademicSystemException("Invalid class code");
        }

        if (title == null || title.isBlank()) {
            throw new AcademicSystemException("Invalid class title");
        }

        AcademicClass academicClass = new AcademicClass(code, title);
        repository.save(academicClass);

        log.info("Class registered: " + code);
        return academicClass;
    }

    public List<AcademicClass> listClasses() {
        return repository.findAll();
    }
}
