package org.aptech.sis.repository;

import java.util.Optional;
import org.aptech.sis.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Optional<Subject> findBySubjectCodeIgnoreCase(String subjectCode);
}

