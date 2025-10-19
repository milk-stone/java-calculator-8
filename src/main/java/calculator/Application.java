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
        String numberPart = userInput;
        sum = Arrays.stream(numberPart.split("[:,]"))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .sum();
        return sum;
    }
}
