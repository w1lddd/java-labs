package edu.course.lab03;

public class PriceCalculator {
    private final DiscountPolicy policy;

    public PriceCalculator(DiscountPolicy policy) {
        if (policy == null) {
            throw new IllegalArgumentException("Политика скидки не должна быть null");
        }
        this.policy = policy;
    }

    public double calculate(double price) {
        return policy.apply(price);
    }
}
