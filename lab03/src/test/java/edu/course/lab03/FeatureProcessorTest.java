package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FeatureProcessorTest {
    @Test
    void replacesStrategyWithoutChangingProcessorOrSample() {
        DataSample sample = new DataSample(new double[]{10, 20, 30});
        FeatureProcessor minMax = new FeatureProcessor(new MinMaxNormalization());
        FeatureProcessor centering = new FeatureProcessor(new MeanCenteringNormalization());

        assertArrayEquals(new double[]{0, 0.5, 1}, minMax.process(sample.getFeatures()), 0.000001);
        assertArrayEquals(new double[]{-10, 0, 10}, centering.process(sample.getFeatures()), 0.000001);
        assertArrayEquals(new double[]{10, 20, 30}, sample.getFeatures());
    }

    @Test
    void doesNotChangeInputVector() {
        double[] values = {30, 10, 20};
        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());
        processor.process(values);
        assertArrayEquals(new double[]{30, 10, 20}, values);
    }

    @Test
    void delegatesInputValidationToStrategy() {
        FeatureProcessor processor = new FeatureProcessor(new MeanCenteringNormalization());
        assertThrows(IllegalArgumentException.class, () -> processor.process(null));
        assertThrows(IllegalArgumentException.class, () -> processor.process(new double[]{}));
    }

    @Test
    void rejectsNullStrategy() {
        assertThrows(IllegalArgumentException.class, () -> new FeatureProcessor(null));
    }
}
