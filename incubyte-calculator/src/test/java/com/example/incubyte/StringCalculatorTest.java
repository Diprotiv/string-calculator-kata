package com.example.incubyte;


import com.example.incubyte.exceptions.StringCalculatorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


/**
 * Class to test the functionality of {@link StringCalculator}
 */
class StringCalculatorTest {

    @BeforeAll
    public static void setup() {
    }

    @Test
    void testAdd_HappyCase() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add(TestConstants.COMMA_SEPARATED_NUMBERS);
        Assertions.assertEquals(3, result);
    }

    @Test
    void testAdd_WithInvalidInput() {
        StringCalculator stringCalculator = new StringCalculator();
        Exception exception = Assertions.assertThrows(StringCalculatorException.class, () -> {
            stringCalculator.add(TestConstants.COMMA_SEPARATED_INVALID_NUMBERS);
        });
        Assertions.assertTrue(exception.getMessage().contains(TestConstants.INVALID_INPUT_FORMAT));
    }


    @Test
    void testAdd_WithBlankString() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add(TestConstants.EMPTY_STRING);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAdd_WithUnknownAmountOfNumbers() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add(TestConstants.COMMA_SEPARATED_UNKNOWN_AMOUNT_OF_NUMBERS);
        Assertions.assertEquals(6, result);
    }

    @Test
    void testAdd_WithNewLinesAsSeparators() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add(TestConstants.COMMA_SEPARATED_NUMBERS_WITH_NEWLINE);
        Assertions.assertEquals(6, result);
    }

    @Test
    void testAdd_WithNewLinesAsSeparatorsInvalidFormat() {
        StringCalculator stringCalculator = new StringCalculator();
        Exception exception = Assertions.assertThrows(StringCalculatorException.class, () -> {
            stringCalculator.add(TestConstants.COMMA_SEPARATED_INVALID_NUMBERS_WITH_NEWLINE);
        });
        Assertions.assertTrue(exception.getMessage().contains(TestConstants.INVALID_INPUT_FORMAT));
    }

    @Test
    void testAdd_WithSemiColonAsDelimiter() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add("//;\n1;2");
        Assertions.assertEquals(3, result);
    }

    @Test
    void testAdd_WithSemiColonAndNewLineAsDelimiter() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add("//;\n1;2\n3");
        Assertions.assertEquals(6, result);
    }

}
