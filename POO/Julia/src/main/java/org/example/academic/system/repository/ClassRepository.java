package org.example.academic.system.repository;

import org.example.academic.system.model.AcademicClass;

import java.util.List;

public interface ClassRepository {

    void save(AcademicClass academicClass);

    List<AcademicClass> findAll();

    void exportToFile(String fileName);
}
