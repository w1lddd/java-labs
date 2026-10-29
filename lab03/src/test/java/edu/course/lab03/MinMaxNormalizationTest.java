package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MinMaxNormalizationTest {
    private final NormalizationStrategy strategy = new MinMaxNormalization();

    @Test
    void mapsMinimumAndMaximumToZeroAndOne() {
        assertArrayEquals(new double[]{0, 0.5, 1},
                strategy.normalize(new double[]{10, 20, 30}), 0.000001);
    }

    @Test
    void preservesOrderAndHandlesNegativeValues() {
        assertArrayEquals(new double[]{1, 0, 0.25},
                strategy.normalize(new double[]{3, -5, -3}), 0.000001);
    }

    @Test
    void returnsZerosForConstantAndSingleValue() {
        assertArrayEquals(new double[]{0, 0, 0}, strategy.normalize(new double[]{7, 7, 7}), 0.000001);
        assertArrayEquals(new double[]{0}, strategy.normalize(new double[]{-4}), 0.000001);
    }

    @Test
    void leavesInputUnchangedAndReturnsNewArray() {
        double[] values = {30, 10, 20};
        double[] result = strategy.normalize(values);
        assertArrayEquals(new double[]{30, 10, 20}, values);
        assertNotSame(values, result);
        result[0] = 100;
        assertArrayEquals(new double[]{30, 10, 20}, values);
    }

    @Test
    void rejectsNullAndEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(null));
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(new double[]{}));
    }

    @Test
    void rejectsNonFiniteValues() {
        for (double x : new double[]{Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            double[] values = {1, x};
            assertThrows(IllegalArgumentException.class, () -> strategy.normalize(values));
            assertArrayEquals(new double[]{1, x}, values);
        }
    }

    @Test
    void handlesRangeLargerThanMaximumDouble() {
        assertArrayEquals(new double[]{0, 0.5, 1},
                strategy.normalize(new double[]{-Double.MAX_VALUE, 0, Double.MAX_VALUE}), 0.000001);
    }
}
