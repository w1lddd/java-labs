package edu.course.lab03;

public class MeanCenteringNormalization implements NormalizationStrategy {
    @Override
    public double[] normalize(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть null или пустым");
        }

        double mean = 0;
        for (double x : values) {
            if (!Double.isFinite(x)) {
                throw new IllegalArgumentException("Признаки должны быть конечными числами");
            }
            mean += x / values.length;
        }

        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] - mean;
            if (!Double.isFinite(result[i])) {
                throw new ArithmeticException("Переполнение при центрировании");
            }
        }
        return result;
    }
}
