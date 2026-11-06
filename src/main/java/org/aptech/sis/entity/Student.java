package org.aptech.sis.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Question 1: Entity Student mapping table student_t.
 */
@Entity
@Table(name = "student_t")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "student_code", length = 20, nullable = false)
    private String studentCode;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "address", length = 255)
    private String address;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentScore> scores = new ArrayList<>();

    public Student() {
    }

    public Student(String studentCode, String fullName, String address) {
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.address = address;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<StudentScore> getScores() {
        return scores;
    }

    public void setScores(List<StudentScore> scores) {
        this.scores = scores;
    }
}

