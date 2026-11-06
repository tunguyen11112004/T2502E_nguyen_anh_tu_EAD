package org.aptech.sis.repository;

import java.util.List;
import java.util.Optional;
import org.aptech.sis.entity.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentScoreRepository extends JpaRepository<StudentScore, Integer> {

    @Query("SELECT sc FROM StudentScore sc JOIN FETCH sc.student st JOIN FETCH sc.subject sub ORDER BY sc.studentScoreId ASC")
    List<StudentScore> findAllWithStudentAndSubject();

    @Query("SELECT sc FROM StudentScore sc JOIN FETCH sc.student st JOIN FETCH sc.subject sub WHERE sc.studentScoreId = :id")
    Optional<StudentScore> findByIdWithStudentAndSubject(Integer id);

    Optional<StudentScore> findByStudent_StudentIdAndSubject_SubjectId(Integer studentId, Integer subjectId);
}

