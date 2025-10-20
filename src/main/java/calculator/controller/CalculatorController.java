package calculator.controller;

import calculator.model.StringCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {
    private final StringCalculator calculator;
    private final InputView inputView;
    private final OutputView outputView;

    public CalculatorController(StringCalculator calculator, InputView inputView, OutputView outputView) {
        this.calculator = calculator;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String input = inputView.readInput();
        int result = calculator.calculate(input);
        outputView.printResult(result);
    }
}
