package org.example.academic.system.service;

import org.example.academic.system.exception.ClassNotFoundException;
import org.example.academic.system.exception.InvalidAssessmentException;
import org.example.academic.system.model.*;
import org.example.academic.system.repository.AcademicClassRepository;
import org.example.academic.system.validation.DomainValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lógica de negócio para registro de avaliações em turmas (TUS-2397).
 * Movida de AcademicSystemController/AcademicService para isolar a
 * responsabilidade de avaliações. Independente de teclado e menu (AC8).
 */
public class AssessmentService {

    private static final Logger log = LoggerFactory.getLogger(AssessmentService.class);
    private final AcademicClassRepository repository;

    public AssessmentService(AcademicClassRepository repository) {
        this.repository = repository;
    }

    /**
     * Registra uma avaliação em uma turma existente (US-2361, TUS-2397).
     * <ul>
     *   <li>AC3: encontra a turma, cria o tipo correto, valida e adiciona;</li>
     *   <li>AC6: tipo inválido não adiciona avaliação (lança antes do add);</li>
     *   <li>AC7: código inexistente não adiciona avaliação.</li>
     * </ul>
     *
     * @param classCode      código da turma alvo
     * @param assessmentType 1=Exam, 2=PracticalAssignment, 3=Seminar, 4=Assignment
     * @param value          nota (0-10)
     * @param weight         peso (0-1)
     */
    public void registerAssessment(String classCode, int assessmentType, double value, double weight) {
        AcademicClass academicClass = repository.findByCode(classCode)
                .orElseThrow(() -> new ClassNotFoundException(classCode)); // AC7

        Assessment assessment = createAssessment(assessmentType, value, weight); // AC6
        DomainValidator.validate(assessment);
        academicClass.addAssessment(assessment);
        log.info("Assessment '{}' registered in class '{}'", assessment.getType(), classCode);
    }

    private Assessment createAssessment(int type, double value, double weight) {
        return switch (type) {
            case 1 -> new Exam(value, weight);
            case 2 -> new PracticalAssignment(value, weight);
            case 3 -> new Seminar(value, weight);
            case 4 -> new Assignment(value, weight);
            default -> throw new InvalidAssessmentException(
                    "Invalid assessment type: " + type
                            + ". Valid types: 1=Exam, 2=PracticalAssignment, 3=Seminar, 4=Assignment");
        };
    }
}
