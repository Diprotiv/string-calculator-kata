package com.example.incubyte;

import com.example.incubyte.constants.ApplicationConstants;
import com.example.incubyte.exceptions.StringCalculatorException;
import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class to demonstrate the functionality of Simple Calculator.
 */
public class StringCalculator {
    private static final String DELIMITER = "^(\\/\\/)(\\[.+\\])+[\\n]";
    private static final String NEGATIVE_NUMBER = "-(\\d)+";

    public Integer add(final String numbers) {
        if (StringUtils.isEmpty(numbers)) {
            return 0;
        }
        List<String> numbersList = splitAndValidate(numbers);
        return numbersList.stream()
                .map(Integer::parseInt)
                .filter(i -> i <= 1000)
                .mapToInt(i -> i)
                .sum();
    }


    private List<String> splitAndValidate(@NonNull final String numbers) {
        final String suffixForDelimiters = validateInput(numbers);
        String separator = "[" + suffixForDelimiters + "|\n]";
        List<String> numbersList = Arrays.asList(StringUtils.split(RegExUtils.replaceAll(numbers, DELIMITER, StringUtils.EMPTY), separator));
        validateIfNotNegative(numbersList);
        return numbersList;
    }

    private void validateIfNotNegative(final List<String> numbersList) {
        List<String> negativeNumbers = new ArrayList<>();
        numbersList.forEach(number -> {
            if (number.matches(NEGATIVE_NUMBER)) {
                negativeNumbers.add(number);
            }
        });
        if (CollectionUtils.isNotEmpty(negativeNumbers)) {
            throw new StringCalculatorException(StringUtils.joinWith(StringUtils.SPACE, ApplicationConstants.NEGATIVE_NUMBERS_INVALID_ERROR_MESSAGE,
                    String.join(ApplicationConstants.COMMA_SEPARATOR, negativeNumbers)));
        }
    }

    private String validateInput(final String numbers) {
        final List<String> delimiters = Arrays.asList(getDelimiter(numbers).split("\\s+"));
        final String prefixForDelimiters = delimiters.stream()
                .map(item -> StringUtils.join("(\\[", RegExUtils.replaceAll(item, "\\p{Punct}", "\\\\$0") + "\\])"))
                .collect(Collectors.joining());
        final String suffixForDelimiters = delimiters.stream()
                .map(item -> RegExUtils.replaceAll(item, "\\p{Punct}", "\\\\$0"))
                .collect(Collectors.joining(ApplicationConstants.OR_SEPARATOR));
        String validRegex = "^(\\/\\/(" + prefixForDelimiters + ")[\\n])?((-)?\\d)+((" + suffixForDelimiters + "|\\n)((-)?\\d)+)*$";
        if (!numbers.matches(validRegex)) {
            throw new StringCalculatorException(ApplicationConstants.INVALID_INPUT_FORMAT_ERROR_MESSAGE);
        }
        return suffixForDelimiters;
    }

    private String getDelimiter(final String numbers) {
        Pattern pattern = Pattern.compile(DELIMITER);
        Matcher matcher = pattern.matcher(numbers);
        if (matcher.find()) {
            String delimitersPattern = matcher.group(2);
            delimitersPattern = RegExUtils.replaceAll(delimitersPattern, "\\]\\[", " ");
            delimitersPattern = RegExUtils.removeAll(delimitersPattern, "\\[");
            delimitersPattern = RegExUtils.removeAll(delimitersPattern, "\\]");
            return delimitersPattern;
        } else {
            return ApplicationConstants.COMMA_SEPARATOR;
        }
    }
}
