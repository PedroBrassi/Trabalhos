package org.example.academic.system;

import org.example.academic.system.model.Exam;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.repository.ClassRepository;
import org.example.academic.system.repository.JsonClassRepository;
import org.example.academic.system.repository.TxtClassRepository;
import org.example.academic.system.repository.XmlClassRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("US-2389 - Repositórios de persistência (TXT/XML/JSON)")
class PersistenceRepositoryTest {

    @TempDir
    Path tempDir;

    private AcademicClass sampleClass() {
        AcademicClass academicClass = new AcademicClass("CC101", "Introdução a POO");
        academicClass.addAssessment(new Exam(8.0, 0.4));
        return academicClass;
    }

    @Test
    @DisplayName("repositório TXT gera arquivo com os dados")
    void txtRepositoryShouldGenerateFile() throws IOException {
        ClassRepository repository = new TxtClassRepository();
        repository.save(sampleClass());

        Path file = tempDir.resolve("classes.txt");
        repository.exportToFile(file.toString());

        assertTrue(Files.exists(file));
        String content = Files.readString(file);
        assertTrue(content.contains("CC101"));
        assertTrue(content.contains("Exam"));
    }

    @Test
    @DisplayName("repositório XML gera arquivo com os dados")
    void xmlRepositoryShouldGenerateFile() throws IOException {
        ClassRepository repository = new XmlClassRepository();
        repository.save(sampleClass());

        Path file = tempDir.resolve("classes.xml");
        repository.exportToFile(file.toString());

        assertTrue(Files.exists(file));
        String content = Files.readString(file);
        assertTrue(content.contains("CC101"));
        assertTrue(content.contains("Exam"));
    }

    @Test
    @DisplayName("repositório JSON gera arquivo com os dados")
    void jsonRepositoryShouldGenerateFile() throws IOException {
        ClassRepository repository = new JsonClassRepository();
        repository.save(sampleClass());

        Path file = tempDir.resolve("classes.json");
        repository.exportToFile(file.toString());

        assertTrue(Files.exists(file));
        String content = Files.readString(file);
        assertTrue(content.contains("CC101"));
        assertTrue(content.contains("Exam"));
    }
}
