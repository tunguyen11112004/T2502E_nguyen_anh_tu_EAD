package org.aptech.sis.repository;

import java.util.Optional;
import org.aptech.sis.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByStudentCodeIgnoreCase(String studentCode);
}

