package org.example.academic.system.controller;

import java.util.ArrayList;
import java.util.List;

public class AcademicSystemController {

    private static AcademicSystemController instance;

    // Construtor privado para o padrão Singleton
    private AcademicSystemController() {}

    public static synchronized AcademicSystemController getInstance() {
        if (instance == null) {
            instance = new AcademicSystemController();
        }
        return instance;
    }

    // Método exigido pela TUS-2409
    public void registerClass(String code, String title) {
        System.out.println("[Backend Temporário] Turma registrada: " + code + " - " + title);
    }

    // Método exigido pela TUS-2412
    public void configurePersistenceType(String format) {
        System.out.println("[Backend Temporário] Persistência alterada para: " + format);
    }

    // Métodos exigidos pela TUS-2413
    public List<Object> getAllClasses() {
        return new ArrayList<>(); // Retorna lista vazia por enquanto
    }

    public List<Object> getAllAssessments() {
        return new ArrayList<>(); // Retorna lista vazia por enquanto
    }
}