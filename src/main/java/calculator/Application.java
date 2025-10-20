package calculator;

import calculator.controller.CalculatorController;
import calculator.model.StringCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;


public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        StringCalculator calculator = new StringCalculator();

        CalculatorController controller = new CalculatorController(calculator, inputView, outputView);

        controller.run();
    }
}
