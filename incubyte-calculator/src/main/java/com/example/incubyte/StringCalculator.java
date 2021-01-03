package com.example.incubyte;


import com.example.incubyte.constants.ApplicationConstants;
import com.example.incubyte.exceptions.StringCalculatorException;
import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Class to demonstrate the functionality of Simple Calculator.
 */
public class StringCalculator {

    private static final String COMMA_OR_NEWLINE_SEPARATOR = "[,|\n]";
    private static final String VALID_INPUT_REGEX = "^(\\d)+([,|\\n](\\d)+)*$";

    public Integer add(final String numbers) {
        if (StringUtils.isEmpty(numbers)) {
            return 0;
        }
        List<String> numbersList = splitAndValidate(numbers);
        return numbersList.stream()
                .map(Integer::parseInt)
                .mapToInt(i -> i)
                .sum();
    }

    private List<String> splitAndValidate(@NotNull final String numbers) {
        validateInput(numbers);
        return Arrays.asList(StringUtils.split(numbers, COMMA_OR_NEWLINE_SEPARATOR));
    }

    private void validateInput(final String numbers) {
        if (!numbers.matches(VALID_INPUT_REGEX)) {
            throw new StringCalculatorException(ApplicationConstants.INVALID_INPUT_FORMAT);
        }
    }
}
