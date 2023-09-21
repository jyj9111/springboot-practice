package com.example.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculateTests {
    private Calculator calculator;

    @BeforeEach
    public void init() {
        this.calculator = new Calculator();
    }

    @Test
    public void additionTest() {
//        Calculator calculator = new Calculator();
        assertEquals(5, calculator.add(2, 3));
        assertNotEquals(5, calculator.add(3, 3));
    }

    @Test
    public void subtractionTest() {
//        Calculator calculator = new Calculator();
        assertEquals(3, calculator.sub(5, 2));
    }

    @Test
    public void multipleTest() {
//        Calculator calculator = new Calculator();
        assertEquals(8, calculator.mul(4, 2));
        assertNotEquals(8, calculator.mul(3, 2));
    }

    @Test
    public void divisionTest() {
//        Calculator calculator = new Calculator();
        assertEquals(2, calculator.divide(4, 2));
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(6, 0));
    }

    private class Calculator {
        public int add(int a, int b) {
            return a + b;
        }

        public int sub(int a, int b) {
            return a - b;
        }

        public int mul(int a, int b) {
            return a * b;
        }

        public int divide(int a, int b) {
            if (b == 0) throw new IllegalArgumentException("division by zero");
            return a / b;
        }
    }
}
