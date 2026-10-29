package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceCalculatorTest {
    @Test
    void acceptsDifferentPoliciesWithoutChangingCalculator() {
        PriceCalculator regular = new PriceCalculator(new NoDiscount());
        PriceCalculator discounted = new PriceCalculator(new PercentDiscount(20));

        assertEquals(100, regular.calculate(100), 0.000001);
        assertEquals(80, discounted.calculate(100), 0.000001);
    }

    @Test
    void delegatesPriceValidationToPolicy() {
        PriceCalculator calculator = new PriceCalculator(new PercentDiscount(20));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(-10));
    }

    @Test
    void rejectsNullPolicy() {
        assertThrows(IllegalArgumentException.class, () -> new PriceCalculator(null));
    }
}
