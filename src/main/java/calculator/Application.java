package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Application {
    private static final Pattern START_PATTERN = Pattern.compile("^\\/\\/\\W\\\\n");

    public static void main(String[] args) {
        System.out.println("사용자가 입력할 문자열을 입력해주세요.");
        String userInput = Console.readLine();

        int result = calculateSum(userInput.strip());
        System.out.println("결과 : " + result);
    }

    public static int calculateSum(String userInput) {
        int sum = 0;
        Matcher matcher = START_PATTERN.matcher(userInput);

        if (matcher.find()) {
            char customDelimiter = userInput.charAt(2);

            String numberPart = userInput.substring(matcher.end());
            sum = Arrays.stream(numberPart.split("[:" + Pattern.quote(String.valueOf(customDelimiter)) + ",]"))
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .sum();
        } else {
            String numberPart = userInput;
            sum = Arrays.stream(numberPart.split("[:,]"))
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .sum();
        }
        return sum;
    }
}
