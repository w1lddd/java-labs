package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataSampleTest {
    @Test
    void createsValidSample() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{2, 4});
        assertEquals("s1", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals(SampleStatus.NEW, sample.getStatus());
        assertArrayEquals(new double[]{2, 4}, sample.getFeatures());
        assertFalse(sample.isReady());
    }

    @Test
    void rejectsNullId() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample(null, "cat", SampleStatus.NEW, new double[]{1}));
    }

    @Test
    void rejectsEmptyAndBlankIds() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("", "cat", SampleStatus.NEW, new double[]{1}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample(" \t", "cat", SampleStatus.NEW, new double[]{1}));
    }

    @Test
    void rejectsNullLabel() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s1", null, SampleStatus.NEW, new double[]{1}));
    }

    @Test
    void rejectsEmptyAndBlankLabels() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s1", "", SampleStatus.NEW, new double[]{1}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s1", " \n", SampleStatus.NEW, new double[]{1}));
    }

    @Test
    void rejectsNullInitialStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s1", "cat", null, new double[]{1}));
    }

    @Test
    void rejectsNullFeatures() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s1", "cat", SampleStatus.NEW, null));
    }

    @Test
    void rejectsEmptyFeatures() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s1", "cat", SampleStatus.NEW, new double[]{}));
    }

    @Test
    void rejectsNonFiniteFeatures() {
        for (double x : new double[]{Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class,
                    () -> new DataSample("s1", "cat", SampleStatus.NEW, new double[]{x}));
        }
    }

    @Test
    void changesStatusAndReadiness() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{1});
        sample.changeStatus(SampleStatus.READY);
        assertEquals(SampleStatus.READY, sample.getStatus());
        assertTrue(sample.isReady());
        sample.changeStatus(SampleStatus.REJECTED);
        assertEquals(SampleStatus.REJECTED, sample.getStatus());
        assertFalse(sample.isReady());
    }

    @Test
    void rejectsNullStatusWithoutChangingState() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.READY, new double[]{1});
        assertThrows(IllegalArgumentException.class, () -> sample.changeStatus(null));
        assertEquals(SampleStatus.READY, sample.getStatus());
        assertTrue(sample.isReady());
    }

    @Test
    void computesFractionalAverage() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{1, 2});
        assertEquals(1.5, sample.averageFeatures(), 0.000001);
    }

    @Test
    void handlesNegativeAndMixedFeatures() {
        DataSample negative = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{-5, -3, -1});
        DataSample mixed = new DataSample("s2", "dog", SampleStatus.READY, new double[]{-2, 0, 2});
        assertEquals(-3, negative.averageFeatures(), 0.000001);
        assertEquals(0, mixed.averageFeatures(), 0.000001);
    }

    @Test
    void handlesSingleFeature() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{7});
        assertEquals(7, sample.averageFeatures(), 0.000001);
    }

    @Test
    void avoidsOverflowForLargeFeatures() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW,
                new double[]{Double.MAX_VALUE, Double.MAX_VALUE});
        assertEquals(Double.MAX_VALUE, sample.averageFeatures());
    }

    @Test
    void copiesFeaturesInConstructor() {
        double[] original = {2, 4};
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, original);
        original[0] = 100;
        assertArrayEquals(new double[]{2, 4}, sample.getFeatures());
        assertEquals(3, sample.averageFeatures(), 0.000001);
    }

    @Test
    void copiesFeaturesInAccessor() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{2, 4});
        double[] copy = sample.getFeatures();
        copy[0] = 100;
        assertArrayEquals(new double[]{2, 4}, sample.getFeatures());
        assertNotSame(copy, sample.getFeatures());
        assertEquals(3, sample.averageFeatures(), 0.000001);
    }

    @Test
    void calculatingAverageDoesNotChangeFeatures() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, new double[]{2, 4});
        sample.averageFeatures();
        assertArrayEquals(new double[]{2, 4}, sample.getFeatures());
    }
}
