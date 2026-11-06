package org.aptech.sis.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class GradeCalculatorTest {

    @Test
    void shouldConvertGradeAccordingToExamRule() {
        // Sample data from exam paper: score1 = 8.5, score2 = 7.0 -> 0.3*8.5 + 0.7*7.0 = 7.45 -> B
        assertEquals(new BigDecimal("7.45"), GradeCalculator.calculateCombinedScore(new BigDecimal("8.5"), new BigDecimal("7.0")));
        assertEquals("B", GradeCalculator.convertScoreToGrade(new BigDecimal("8.5"), new BigDecimal("7.0")));

        // 8.0 to 10.0 -> A
        assertEquals("A", GradeCalculator.convertScoreToGrade(new BigDecimal("8.0"), new BigDecimal("8.0")));
        assertEquals("A", GradeCalculator.convertScoreToGrade(new BigDecimal("10.0"), new BigDecimal("9.5")));

        // 6.0 to 7.9 -> B
        assertEquals("B", GradeCalculator.convertScoreToGrade(new BigDecimal("6.0"), new BigDecimal("6.0")));

        // 4.0 to 5.9 -> D
        assertEquals("D", GradeCalculator.convertScoreToGrade(new BigDecimal("4.0"), new BigDecimal("5.0")));

        // less than 4.0 -> F
        assertEquals("F", GradeCalculator.convertScoreToGrade(new BigDecimal("3.0"), new BigDecimal("3.5")));
    }
}

