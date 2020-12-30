package com.example.incubyte;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * Class to demonstrate the functionality of Simple Calculator.
 */
public class StringCalculator {

    private static final String COMMA_SEPARATOR = ",";

    public Integer add(final String numbers) {
        if (StringUtils.isEmpty(numbers)) {
            return 0;
        }
        return Arrays.stream(StringUtils.split(numbers, COMMA_SEPARATOR))
               .filter(StringUtils::isNotEmpty)
               .map(Integer::parseInt)
               .mapToInt(i -> i)
               .sum();
    }
}
