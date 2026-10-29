package edu.course.lab03;

public class DataSample {
    private final double[] features;

    public DataSample(double[] features) {
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException("Массив признаков не должен быть null или пустым");
        }
        for (double x : features) {
            if (!Double.isFinite(x)) {
                throw new IllegalArgumentException("Признаки должны быть конечными числами");
            }
        }
        this.features = features.clone();
    }

    public double[] getFeatures() {
        return features.clone();
    }
}
