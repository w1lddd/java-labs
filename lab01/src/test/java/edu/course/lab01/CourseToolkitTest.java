package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }

    @Test
    void returnsTrueForZero() {
        boolean result = CourseToolkit.isEven(0);

        assertTrue(result);
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        boolean result = CourseToolkit.isEven(-8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForNegativeOddNumber() {
        assertFalse(CourseToolkit.isEven(-7));
    }

    @Test
    void numbersBelowTwoAreNotPrime() {
        assertFalse(CourseToolkit.isPrime(-7));
        assertFalse(CourseToolkit.isPrime(0));
        assertFalse(CourseToolkit.isPrime(1));
    }

    @Test
    void twoIsPrime() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void oddPrimesArePrime() {
        assertTrue(CourseToolkit.isPrime(3));
        assertTrue(CourseToolkit.isPrime(17));
    }

    @Test
    void compositeNumbersAreNotPrime() {
        assertFalse(CourseToolkit.isPrime(6));
        assertFalse(CourseToolkit.isPrime(15));
    }

    @Test
    void squaresOfPrimesAreNotPrime() {
        assertFalse(CourseToolkit.isPrime(4));
        assertFalse(CourseToolkit.isPrime(49));
    }

    @Test
    void handlesPrimeAtUpperIntBoundary() {
        assertTrue(CourseToolkit.isPrime(Integer.MAX_VALUE));
    }

    @Test
    void recognizesOddLengthPalindrome() {
        assertTrue(CourseToolkit.isPalindrome("топот"));
    }

    @Test
    void recognizesEvenLengthPalindrome() {
        assertTrue(CourseToolkit.isPalindrome("abba"));
    }

    @Test
    void rejectsNonPalindrome() {
        assertFalse(CourseToolkit.isPalindrome("java"));
    }

    @Test
    void palindromeCheckIsCaseSensitive() {
        assertFalse(CourseToolkit.isPalindrome("Abba"));
    }

    @Test
    void palindromeCheckPreservesSpaces() {
        assertFalse(CourseToolkit.isPalindrome("a a "));
        assertTrue(CourseToolkit.isPalindrome("a a"));
    }

    @Test
    void emptyAndSingleCharacterStringsArePalindromes() {
        assertTrue(CourseToolkit.isPalindrome(""));
        assertTrue(CourseToolkit.isPalindrome("a"));
    }

    @Test
    void palindromeRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }

    @Test
    void averageKeepsFractionalPart() {
        assertEquals(1.5, CourseToolkit.average(new int[]{1, 2}), 0.000001);
    }

    @Test
    void averageHandlesNegativeValues() {
        assertEquals(-3.0, CourseToolkit.average(new int[]{-5, -3, -1}), 0.000001);
    }

    @Test
    void averageHandlesMixedValues() {
        assertEquals(0.0, CourseToolkit.average(new int[]{-2, 0, 2}), 0.000001);
    }

    @Test
    void averageHandlesSingleValue() {
        assertEquals(7.0, CourseToolkit.average(new int[]{7}), 0.000001);
    }

    @Test
    void averageDoesNotOverflowIntSum() {
        assertEquals((double) Integer.MAX_VALUE,
                CourseToolkit.average(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}), 0.000001);
        assertEquals((double) Integer.MIN_VALUE,
                CourseToolkit.average(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE}), 0.000001);
    }

    @Test
    void averageRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
    }

    @Test
    void averageRejectsEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[]{}));
    }

    @Test
    void averageDoesNotChangeArray() {
        int[] values = {3, 1, 2};

        CourseToolkit.average(values);

        assertArrayEquals(new int[]{3, 1, 2}, values);
    }

    @Test
    void minIncludesFirstElement() {
        assertEquals(-5, CourseToolkit.min(new int[]{-5, 3, 7}));
    }

    @Test
    void minFindsLaterElement() {
        assertEquals(-9, CourseToolkit.min(new int[]{-1, -4, -9}));
    }

    @Test
    void minHandlesOnlyPositiveValues() {
        assertEquals(2, CourseToolkit.min(new int[]{5, 2, 8}));
    }

    @Test
    void minHandlesSingleElement() {
        assertEquals(7, CourseToolkit.min(new int[]{7}));
    }

    @Test
    void minRejectsNullAndEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.min(null));
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.min(new int[]{}));
    }

    @Test
    void maxIncludesFirstElement() {
        assertEquals(9, CourseToolkit.max(new int[]{9, 4, 2}));
    }

    @Test
    void maxFindsLaterElement() {
        assertEquals(9, CourseToolkit.max(new int[]{2, 4, 9}));
    }

    @Test
    void maxHandlesOnlyNegativeValues() {
        assertEquals(-2, CourseToolkit.max(new int[]{-8, -2, -5}));
    }

    @Test
    void maxHandlesSingleElement() {
        assertEquals(-7, CourseToolkit.max(new int[]{-7}));
    }

    @Test
    void maxRejectsNullAndEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.max(null));
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.max(new int[]{}));
    }

    @Test
    void minAndMaxHandleEqualValuesAndIntBoundaries() {
        assertEquals(4, CourseToolkit.min(new int[]{4, 4, 4}));
        assertEquals(4, CourseToolkit.max(new int[]{4, 4, 4}));
        int[] extremes = {0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        assertEquals(Integer.MIN_VALUE, CourseToolkit.min(extremes));
        assertEquals(Integer.MAX_VALUE, CourseToolkit.max(extremes));
    }

    @Test
    void minAndMaxDoNotChangeArray() {
        int[] values = {3, -1, 2};

        CourseToolkit.min(values);
        assertArrayEquals(new int[]{3, -1, 2}, values);

        CourseToolkit.max(values);
        assertArrayEquals(new int[]{3, -1, 2}, values);
    }
}
