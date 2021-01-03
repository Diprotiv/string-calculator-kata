package com.example.incubyte;

import com.example.incubyte.constants.ApplicationConstants;
import com.example.incubyte.exceptions.StringCalculatorException;
import lombok.NonNull;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to demonstrate the functionality of Simple Calculator.
 */
public class StringCalculator {
    private static final String DELIMITER = "^(\\/\\/)(.+)[\\n]";

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

    private List<String> splitAndValidate(@NonNull final String numbers) {
        final String delimiter = validateInput(numbers);
        String separator = "[" + delimiter + "|\n]";
        return Arrays.asList(StringUtils.split(RegExUtils.replaceAll(numbers, DELIMITER, StringUtils.EMPTY), separator));
    }

    private String validateInput(final String numbers) {
        final String delimiter = getDelimiter(numbers);
        String validRegex = "^(\\/\\/.+[\\n])?(\\d)+([" + delimiter + "|\\n](\\d)+)*$";
        if (!numbers.matches(validRegex)) {
            throw new StringCalculatorException(ApplicationConstants.INVALID_INPUT_FORMAT);
        }
        return delimiter;
    }

    private String getDelimiter(final String numbers) {
        Pattern pattern = Pattern.compile(DELIMITER);
        Matcher matcher = pattern.matcher(numbers);
        if (matcher.find()) {
            return matcher.group(2);
        } else {
            return ApplicationConstants.COMMA_SEPARATOR;
        }
    }
}
