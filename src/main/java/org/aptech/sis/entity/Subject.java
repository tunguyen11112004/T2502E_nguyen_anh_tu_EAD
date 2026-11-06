package org.aptech.sis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Question 1: Entity Subject mapping table subject_t.
 */
@Entity
@Table(name = "subject_t")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "subject_code", length = 20, nullable = false)
    private String subjectCode;

    @Column(name = "subject_name", length = 100, nullable = false)
    private String subjectName;

    @Column(name = "credit", nullable = false)
    private Integer credit;

    public Subject() {
    }

    public Subject(String subjectCode, String subjectName, Integer credit) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credit = credit;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}

