package org.aptech.sis.config;

import java.math.BigDecimal;
import java.util.List;
import org.aptech.sis.entity.Student;
import org.aptech.sis.entity.StudentScore;
import org.aptech.sis.entity.Subject;
import org.aptech.sis.repository.StudentRepository;
import org.aptech.sis.repository.StudentScoreRepository;
import org.aptech.sis.repository.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Seeds the exact sample data from the exam paper if the database tables are empty.
 */
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedExamSampleData(
            SubjectRepository subjectRepository,
            StudentRepository studentRepository,
            StudentScoreRepository studentScoreRepository) {
        return args -> {
            if (subjectRepository.count() == 0) {
                Subject javaSubject = new Subject("JAVA", "Java Programming", 4);
                Subject phpSubject = new Subject("PHP", "PHP Programming", 3);
                Subject wdaSubject = new Subject("WDA", "Web Development and Applications", 3);
                subjectRepository.saveAll(List.of(javaSubject, phpSubject, wdaSubject));
            }

            if (studentRepository.count() == 0) {
                Student sampleStudent = new Student("2007A10", "Nguyễn Văn A", "Hà Nội");
                studentRepository.save(sampleStudent);
            }

            if (studentScoreRepository.count() == 0) {
                Student firstStudent = studentRepository.findAll().stream().findFirst().orElse(null);
                Subject firstSubject = subjectRepository.findBySubjectCodeIgnoreCase("JAVA")
                        .orElseGet(() -> subjectRepository.findAll().stream().findFirst().orElse(null));
                if (firstStudent != null && firstSubject != null) {
                    StudentScore sampleScore = new StudentScore(
                            firstStudent,
                            firstSubject,
                            new BigDecimal("8.50"),
                            new BigDecimal("7.00")
                    );
                    studentScoreRepository.save(sampleScore);
                }
            }
        };
    }
}

