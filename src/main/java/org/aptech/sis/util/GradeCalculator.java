package org.aptech.sis.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for calculating combined score and converting to Grade letter
 * according to the exam specification:
 * Grade = 0.3 * score1 + 0.7 * score2
 * - 8.0 to 10  -> A
 * - 6.0 to 7.9 -> B
 * - 4.0 to 5.9 -> D
 * - less than 4.0 -> F
 */
public final class GradeCalculator {

    private static final BigDecimal WEIGHT_SCORE_1 = new BigDecimal("0.3");
    private static final BigDecimal WEIGHT_SCORE_2 = new BigDecimal("0.7");

    private static final BigDecimal THRESHOLD_A = new BigDecimal("8.0");
    private static final BigDecimal THRESHOLD_B = new BigDecimal("6.0");
    private static final BigDecimal THRESHOLD_D = new BigDecimal("4.0");

    private GradeCalculator() {
    }

    public static BigDecimal calculateCombinedScore(BigDecimal score1, BigDecimal score2) {
        BigDecimal s1 = score1 != null ? score1 : BigDecimal.ZERO;
        BigDecimal s2 = score2 != null ? score2 : BigDecimal.ZERO;
        return s1.multiply(WEIGHT_SCORE_1)
                .add(s2.multiply(WEIGHT_SCORE_2))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static String convertScoreToGrade(BigDecimal score1, BigDecimal score2) {
        BigDecimal combined = calculateCombinedScore(score1, score2);
        return convertCombinedScoreToGrade(combined);
    }

    public static String convertCombinedScoreToGrade(BigDecimal combinedScore) {
        if (combinedScore == null) {
            return "F";
        }
        if (combinedScore.compareTo(THRESHOLD_A) >= 0) {
            return "A";
        }
        if (combinedScore.compareTo(THRESHOLD_B) >= 0) {
            return "B";
        }
        if (combinedScore.compareTo(THRESHOLD_D) >= 0) {
            return "D";
        }
        return "F";
    }
}

