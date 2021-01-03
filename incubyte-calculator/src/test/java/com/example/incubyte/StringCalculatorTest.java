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
        Exception exception = Assertions.assertThrows(StringCalculatorException.class, () -> stringCalculator.add(TestConstants.COMMA_SEPARATED_INVALID_NUMBERS));
        Assertions.assertTrue(exception.getMessage().contains(TestConstants.INVALID_INPUT_FORMAT_ERROR_MESSAGE));
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
        Exception exception = Assertions.assertThrows(StringCalculatorException.class, () -> stringCalculator.add(TestConstants.COMMA_SEPARATED_INVALID_NUMBERS_WITH_NEWLINE));
        Assertions.assertTrue(exception.getMessage().contains(TestConstants.INVALID_INPUT_FORMAT_ERROR_MESSAGE));
    }

    @Test
    void testAdd_WithSemiColonAsDelimiter() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add(TestConstants.NUMBERS_WITH_SEMICOLON);
        Assertions.assertEquals(3, result);
    }

    @Test
    void testAdd_WithSemiColonAndNewLineAsDelimiter() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add(TestConstants.NUMBERS_WITH_SEMICOLON_AND_NEWLINE);
        Assertions.assertEquals(6, result);
    }

    @Test
    void testAdd_WithNegativeNumbers() {
        StringCalculator stringCalculator = new StringCalculator();
        Exception exception = Assertions.assertThrows(StringCalculatorException.class, () -> stringCalculator.add(TestConstants.INPUT_WITH_NEGATIVE_NUMBERS));
        Assertions.assertTrue(exception.getMessage().contains(TestConstants.NEGATIVE_NUMBERS_INVALID_ERROR_MESSAGE));
        Assertions.assertTrue(exception.getMessage().contains("-2"));
    }

    @Test
    void testAdd_WithMultipleNegativeNumbers() {
        StringCalculator stringCalculator = new StringCalculator();
        Exception exception = Assertions.assertThrows(StringCalculatorException.class, () -> stringCalculator.add(TestConstants.INPUT_WITH_MULTIPLE_NEGATIVE_NUMBERS));
        Assertions.assertTrue(exception.getMessage().contains(TestConstants.NEGATIVE_NUMBERS_INVALID_ERROR_MESSAGE));
        Assertions.assertTrue(exception.getMessage().contains("-2,-3"));
    }

    @Test
    void testAdd_WithNumbersGreaterThanThousand() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add(TestConstants.INPUT_WITH_NUMBERS_GREATER_THAN_THOUSAND);
        Assertions.assertEquals(2, result);
    }

    @Test
    void testAdd_WithDelimitersOfAnyLength() {
        StringCalculator stringCalculator = new StringCalculator();
        Integer result = stringCalculator.add(TestConstants.NUMBERS_WITH_MORE_THAN_ONE_CHARACTER_DELIMITER);
        Assertions.assertEquals(6, result);
    }

}
