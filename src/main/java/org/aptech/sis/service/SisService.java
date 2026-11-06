package org.aptech.sis.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.aptech.sis.dto.ScoreForm;
import org.aptech.sis.dto.StudentForm;
import org.aptech.sis.entity.Student;
import org.aptech.sis.entity.StudentScore;
import org.aptech.sis.entity.Subject;
import org.aptech.sis.repository.StudentRepository;
import org.aptech.sis.repository.StudentScoreRepository;
import org.aptech.sis.repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SisService {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final StudentScoreRepository studentScoreRepository;

    public SisService(
            StudentRepository studentRepository,
            SubjectRepository subjectRepository,
            StudentScoreRepository studentScoreRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.studentScoreRepository = studentScoreRepository;
    }

    @Transactional(readOnly = true)
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Subject> findAllSubjects() {
        return subjectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<StudentScore> findAllStudentScores(String keyword, String gradeFilter) {
        List<StudentScore> allScores = studentScoreRepository.findAllWithStudentAndSubject();

        String normalizedKeyword = keyword != null ? keyword.trim().toLowerCase(Locale.ROOT) : "";
        String normalizedGrade = gradeFilter != null ? gradeFilter.trim().toUpperCase(Locale.ROOT) : "";

        return allScores.stream()
                .filter(sc -> {
                    if (!normalizedKeyword.isEmpty()) {
                        String code = sc.getStudent() != null && sc.getStudent().getStudentCode() != null
                                ? sc.getStudent().getStudentCode().toLowerCase(Locale.ROOT) : "";
                        String name = sc.getStudent() != null && sc.getStudent().getFullName() != null
                                ? sc.getStudent().getFullName().toLowerCase(Locale.ROOT) : "";
                        String subjectName = sc.getSubject() != null && sc.getSubject().getSubjectName() != null
                                ? sc.getSubject().getSubjectName().toLowerCase(Locale.ROOT) : "";
                        String subjectCode = sc.getSubject() != null && sc.getSubject().getSubjectCode() != null
                                ? sc.getSubject().getSubjectCode().toLowerCase(Locale.ROOT) : "";
                        boolean matchKeyword = code.contains(normalizedKeyword)
                                || name.contains(normalizedKeyword)
                                || subjectName.contains(normalizedKeyword)
                                || subjectCode.contains(normalizedKeyword);
                        if (!matchKeyword) {
                            return false;
                        }
                    }
                    if (!normalizedGrade.isEmpty() && !"ALL".equals(normalizedGrade)) {
                        return normalizedGrade.equalsIgnoreCase(sc.getGrade());
                    }
                    return true;
                })
                .toList();
    }

    @Transactional
    public Student createStudent(StudentForm form) {
        Student student = new Student(
                form.getStudentCode().trim(),
                form.getFullName().trim(),
                form.getAddress() != null ? form.getAddress().trim() : null
        );
        return studentRepository.save(student);
    }

    @Transactional
    public StudentScore saveOrUpdateScore(ScoreForm form) {
        Student student = studentRepository.findById(form.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên có ID: " + form.getStudentId()));
        Subject subject = subjectRepository.findById(form.getSubjectId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy môn học có ID: " + form.getSubjectId()));

        StudentScore score;
        if (form.getStudentScoreId() != null) {
            score = studentScoreRepository.findById(form.getStudentScoreId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bản ghi điểm ID: " + form.getStudentScoreId()));
        } else {
            Optional<StudentScore> existing = studentScoreRepository
                    .findByStudent_StudentIdAndSubject_SubjectId(student.getStudentId(), subject.getSubjectId());
            score = existing.orElseGet(StudentScore::new);
        }

        score.setStudent(student);
        score.setSubject(subject);
        score.setScore1(form.getScore1());
        score.setScore2(form.getScore2());
        return studentScoreRepository.save(score);
    }

    @Transactional(readOnly = true)
    public ScoreForm getScoreFormById(Integer scoreId) {
        StudentScore score = studentScoreRepository.findByIdWithStudentAndSubject(scoreId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bản ghi điểm ID: " + scoreId));
        ScoreForm form = new ScoreForm(
                score.getStudent().getStudentId(),
                score.getSubject().getSubjectId(),
                score.getScore1(),
                score.getScore2()
        );
        form.setStudentScoreId(score.getStudentScoreId());
        return form;
    }
}

