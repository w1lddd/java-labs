package edu.course.lab02;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(100);
        account.deposit(50);
        account.withdraw(30);
        System.out.println("Баланс после пополнения и списания: " + account.getBalance());

        try {
            account.withdraw(200);
        } catch (IllegalArgumentException e) {
            System.out.println("Ожидаемая ошибка: " + e.getMessage());
        }
        System.out.println("Баланс после ошибки: " + account.getBalance());

        double[] features = {2, 4, 6};
        DataSample sample = new DataSample("s1", "cat", SampleStatus.NEW, features);
        System.out.println("Наблюдение: " + sample.getId() + ", метка: " + sample.getLabel());
        System.out.println("Признаки: " + Arrays.toString(sample.getFeatures()));
        System.out.println("Среднее признаков: " + sample.averageFeatures());
        System.out.println("Начальный статус: " + sample.getStatus());
        System.out.println("Наблюдение готово: " + sample.isReady());

        sample.changeStatus(SampleStatus.READY);
        System.out.println("Новый статус: " + sample.getStatus());
        System.out.println("Наблюдение готово: " + sample.isReady());

        features[0] = 100;
        System.out.println("После изменения исходного массива: " + Arrays.toString(sample.getFeatures()));

        double[] copy = sample.getFeatures();
        copy[1] = 200;
        System.out.println("После изменения полученной копии: " + Arrays.toString(sample.getFeatures()));
    }
}
