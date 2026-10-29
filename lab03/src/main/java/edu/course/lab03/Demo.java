package edu.course.lab03;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        PriceCalculator regular = new PriceCalculator(new NoDiscount());
        PriceCalculator discounted = new PriceCalculator(new PercentDiscount(20));
        System.out.println("Цена без скидки: " + regular.calculate(100));
        System.out.println("Цена со скидкой 20%: " + discounted.calculate(100));

        DataSample sample = new DataSample(new double[]{10, 20, 30});
        FeatureProcessor minMax = new FeatureProcessor(new MinMaxNormalization());
        FeatureProcessor centering = new FeatureProcessor(new MeanCenteringNormalization());

        System.out.println("Исходные признаки: " + Arrays.toString(sample.getFeatures()));
        System.out.println("Min-max: " + Arrays.toString(minMax.process(sample.getFeatures())));
        System.out.println("Центрирование: " + Arrays.toString(centering.process(sample.getFeatures())));
        System.out.println("Признаки после обработки: " + Arrays.toString(sample.getFeatures()));
    }
}
