package org.aptech.sis.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ScoreForm {

    private Integer studentScoreId;

    @NotNull(message = "Vui lòng chọn sinh viên")
    private Integer studentId;

    @NotNull(message = "Vui lòng chọn môn học")
    private Integer subjectId;

    @NotNull(message = "Vui lòng nhập Điểm 1 (Score 1)")
    @DecimalMin(value = "0.0", message = "Score 1 phải từ 0.0 đến 10.0")
    @DecimalMax(value = "10.0", message = "Score 1 phải từ 0.0 đến 10.0")
    private BigDecimal score1;

    @NotNull(message = "Vui lòng nhập Điểm 2 (Score 2)")
    @DecimalMin(value = "0.0", message = "Score 2 phải từ 0.0 đến 10.0")
    @DecimalMax(value = "10.0", message = "Score 2 phải từ 0.0 đến 10.0")
    private BigDecimal score2;

    public ScoreForm() {
    }

    public ScoreForm(Integer studentId, Integer subjectId, BigDecimal score1, BigDecimal score2) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.score1 = score1;
        this.score2 = score2;
    }

    public Integer getStudentScoreId() {
        return studentScoreId;
    }

    public void setStudentScoreId(Integer studentScoreId) {
        this.studentScoreId = studentScoreId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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
}

