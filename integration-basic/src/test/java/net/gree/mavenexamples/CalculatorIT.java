package net.gree.mavenexamples;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculatorIT {
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();

        assertEquals(3, calculator.add(1, 2));
    }
}
