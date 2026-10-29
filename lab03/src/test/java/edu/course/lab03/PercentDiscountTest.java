package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PercentDiscountTest {
    @Test
    void appliesTwentyPercentDiscount() {
        assertEquals(80, new PercentDiscount(20).apply(100), 0.000001);
    }

    @Test
    void acceptsZeroAndFullDiscount() {
        assertEquals(100, new PercentDiscount(0).apply(100), 0.000001);
        assertEquals(0, new PercentDiscount(100).apply(100), 0.000001);
    }

    @Test
    void handlesFractionalPercentAndZeroPrice() {
        assertEquals(175, new PercentDiscount(12.5).apply(200), 0.000001);
        assertEquals(0, new PercentDiscount(20).apply(0), 0.000001);
    }

    @Test
    void handlesLargePriceWithoutIntermediateOverflow() {
        assertEquals(Double.MAX_VALUE * 0.5, new PercentDiscount(50).apply(Double.MAX_VALUE));
    }

    @Test
    void rejectsInvalidPercent() {
        for (double percent : new double[]{-1, 101, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(percent));
        }
    }

    @Test
    void rejectsInvalidPrice() {
        PercentDiscount policy = new PercentDiscount(20);
        for (double price : new double[]{-1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> policy.apply(price));
        }
    }
}
