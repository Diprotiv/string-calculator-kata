package com.example.incubyte;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Class to test the functionality of {@link StringCalculator}
 */
public class StringCalculatorTest {


    @BeforeAll
    public static void setup() {
    }

    @Test
    public void testAdd_HappyCase() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add(TestConstants.COMMA_SEPARATED_NUMBERS);
        Assertions.assertEquals(3, result);
    }

    @Test
    public void testAdd_BlankString() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add(TestConstants.EMPTY_STRING);
        Assertions.assertEquals(0, result);
    }
}