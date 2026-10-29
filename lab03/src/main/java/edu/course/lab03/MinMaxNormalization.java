package edu.course.lab03;

public class MinMaxNormalization implements NormalizationStrategy {
    @Override
    public double[] normalize(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть null или пустым");
        }

        double min = values[0];
        double max = values[0];
        for (double x : values) {
            if (!Double.isFinite(x)) {
                throw new IllegalArgumentException("Признаки должны быть конечными числами");
            }
            if (x < min) {
                min = x;
            }
            if (x > max) {
                max = x;
            }
        }

        double[] result = new double[values.length];
        if (min == max) {
            return result;
        }

        double range = max - min;
        for (int i = 0; i < values.length; i++) {
            if (Double.isInfinite(range)) {
                result[i] = (values[i] / 2 - min / 2) / (max / 2 - min / 2);
            } else {
                result[i] = (values[i] - min) / range;
            }
        }
        return result;
    }
}
