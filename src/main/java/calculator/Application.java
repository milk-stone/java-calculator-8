package calculator;

import camp.nextstep.edu.missionutils.Console;
public class Application {
    public static void main(String[] args) {
        System.out.println("사용자가 입력할 문자열을 입력해주세요.");
        String userInput = Console.readLine();

        int result = calculateSum(userInput.strip());
        System.out.println("결과 : " + result);
    }

    public static int calculateSum(String userInput) {
        int sum = 0;
        return sum;
    }
}
