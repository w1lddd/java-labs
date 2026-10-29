package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataSampleTest {
    @Test
    void copiesFeaturesInConstructor() {
        double[] values = {10, 20, 30};
        DataSample sample = new DataSample(values);
        values[0] = 100;
        assertArrayEquals(new double[]{10, 20, 30}, sample.getFeatures());
    }

    @Test
    void returnsCopyOfFeatures() {
        DataSample sample = new DataSample(new double[]{10, 20, 30});
        double[] copy = sample.getFeatures();
        copy[0] = 100;
        assertArrayEquals(new double[]{10, 20, 30}, sample.getFeatures());
        assertNotSame(copy, sample.getFeatures());
    }

    @Test
    void rejectsNullAndEmptyFeatures() {
        assertThrows(IllegalArgumentException.class, () -> new DataSample(null));
        assertThrows(IllegalArgumentException.class, () -> new DataSample(new double[]{}));
    }

    @Test
    void rejectsNonFiniteFeatures() {
        for (double x : new double[]{Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> new DataSample(new double[]{x}));
        }
    }
}
