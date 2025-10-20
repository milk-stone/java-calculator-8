package calculator.model;

import calculator.model.dto.ParsedInput;

public class StringCalculator {
    public int calculate(String userInput) {
        ParsedInput parsedInput = CustomParser.parse(userInput);
        int sum = 0;
        for (int number : parsedInput.components()) {
            sum += number;
        }
        return sum;
    }
}
