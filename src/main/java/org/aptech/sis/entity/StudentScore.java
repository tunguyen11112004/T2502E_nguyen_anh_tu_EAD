package org.aptech.sis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import org.aptech.sis.util.GradeCalculator;

/**
 * Question 2: Entity Score mapping table student_score_t.
 */
@Entity
@Table(name = "student_score_t")
public class StudentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id")
    private Integer studentScoreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "fk_student_id"))
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", foreignKey = @ForeignKey(name = "fk_subject_id"))
    private Subject subject;

    @Column(name = "score1", precision = 5, scale = 2)
    private BigDecimal score1;

    @Column(name = "score2", precision = 5, scale = 2)
    private BigDecimal score2;

    public StudentScore() {
    }

    public StudentScore(Student student, Subject subject, BigDecimal score1, BigDecimal score2) {
        this.student = student;
        this.subject = subject;
        this.score1 = score1;
        this.score2 = score2;
    }

    public Integer getStudentScoreId() {
        return studentScoreId;
    }

    public void setStudentScoreId(Integer studentScoreId) {
        this.studentScoreId = studentScoreId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public BigDecimal getScore1() {
        return score1;
    }

    public void setScore1(BigDecimal score1) {
        this.score1 = score1;
    }

    public BigDecimal getScore2() {
        return score2;
    }

    public void setScore2(BigDecimal score2) {
        this.score2 = score2;
    }

    @Transient
    public BigDecimal getCombinedScore() {
        return GradeCalculator.calculateCombinedScore(score1, score2);
    }

    @Transient
    public String getGrade() {
        return GradeCalculator.convertScoreToGrade(score1, score2);
    }
}

