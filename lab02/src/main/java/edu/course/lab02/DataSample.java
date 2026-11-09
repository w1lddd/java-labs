package edu.course.lab02;

public final class DataSample {
    private final String id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, SampleStatus status, double[] features) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Идентификатор не должен быть пустым");
        }
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("Метка не должна быть пустой");
        }
        if (status == null) {
            throw new IllegalArgumentException("Статус не должен быть null");
        }
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException("Массив признаков не должен быть пустым");
        }
        for (double x : features) {
            if (!Double.isFinite(x)) {
                throw new IllegalArgumentException("Признаки должны быть конечными числами");
            }
        }
        this.id = id;
        this.label = label;
        this.status = status;
        this.features = features.clone();
    }

    public void changeStatus(SampleStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Статус не должен быть null");
        }
        this.status = status;
    }

    public boolean isReady() {
        return status == SampleStatus.READY;
    }

    public double averageFeatures() {
        double s = 0;
        for (double x : features) {
            s += x / features.length;
        }
        return s;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public double[] getFeatures() {
        return features.clone();
    }
}
