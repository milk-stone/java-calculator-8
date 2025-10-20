package calculator.model;

import calculator.model.dto.ParsedInput;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CustomParser {

    private static final Pattern START_PATTERN = Pattern.compile("^\\/\\/\\W\\\\n");

    public static ParsedInput parse(String userInput) {
        String delimiter = ":,";
        String numberPart = userInput;

        char customDelimiter = findCustomDelimiter(userInput);

        if (customDelimiter != '\0' && !isAllowedCustomDelimiter(customDelimiter)) {
            throw new IllegalArgumentException("커스텀 구별자는 특수 문자여야 합니다. 입력된 값: " + customDelimiter);
        }

        if (customDelimiter != '\0') {
            delimiter = delimiter + customDelimiter;
            numberPart = userInput.substring(5);
        }

        String fullDelimiter = "[" + delimiter + "]";

        List<Integer> validNumbers = getIntegers(numberPart, fullDelimiter);
        return new ParsedInput(validNumbers);
    }

    private static List<Integer> getIntegers(String numberPart, String fullDelimiter) {
        String[] splitComponents;
        if (numberPart.isEmpty()) {
            splitComponents = new String[0];
        } else {
            splitComponents = numberPart.split(fullDelimiter);
        }

        List<Integer> validNumbers = new ArrayList<>();

        try {
            for (String component : splitComponents) {
                if (component.isEmpty()) {
                    continue;
                }

                int number = Integer.parseInt(component);

                if (number < 0) {
                    throw new IllegalArgumentException("양수가 입력되어야 합니다. 오류가 발생한 값 : " + number);
                }
                validNumbers.add(number);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 숫자 형식입니다. ", e);
        }
        return validNumbers;
    }


    private static char findCustomDelimiter(String userInput) {
        Matcher matcher = START_PATTERN.matcher(userInput);
        if (matcher.find()) {
            return userInput.charAt(2);
        }
        return '\0';
    }

    private static boolean isAllowedCustomDelimiter(char customDelimiter) {
        return !Character.isLetterOrDigit(customDelimiter);
    }
}
