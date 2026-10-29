package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NoDiscountTest {
    @Test
    void preservesPrice() {
        assertEquals(125.5, new NoDiscount().apply(125.5));
    }

    @Test
    void acceptsZeroPrice() {
        assertEquals(0, new NoDiscount().apply(0));
    }

    @Test
    void rejectsInvalidPrice() {
        for (double price : new double[]{-1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}) {
            assertThrows(IllegalArgumentException.class, () -> new NoDiscount().apply(price));
        }
    }
}
