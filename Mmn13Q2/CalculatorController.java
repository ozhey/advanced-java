import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

// CalculatorController handles the interaction with the GUI.
public class CalculatorController {

	@FXML
	private TextArea display;
	private Calculator calculator;

	// constructor
	public CalculatorController() {
		calculator = new Calculator();
	}

	// handles the numerical buttons. sets the text accordingly.
	@FXML
	private void handleNum(Event event) {
		Button btn = (Button) event.getSource();
		calculator.number(btn.getText());
		displayResult();
	}

	// resets the calculator's memory and sets the calculator's text to empty
	@FXML
	private void handleDelete() {
		calculator.reset();
		displayResult();
	}

	// handles clicks on +-/* buttons
	@FXML
	private void handleOperation(Event event) {
		Button btn = (Button) event.getSource();
		calculator.operation(btn.getId());
		displayResult();
	}

	// handles clicks on the ± change sign button
	@FXML
	private void handleChangeSign() {
		calculator.changeSign();
		displayResult();
	}

	// calculates and displays the result.
	@FXML
	private void handleEquals() {
		calculator.equal();
		displayResult();
	}

	// handles comma
	@FXML
	private void handleComma() {
		calculator.comma();
		displayResult();
	}

	// handleKeyPress routes key presses to the corresponding function
	@FXML
	private void handleKeyPress(KeyEvent key) {
		String keyText = key.getText();
		switch (keyText) {
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
				calculator.number(keyText);
				break;
			case "+":
				calculator.operation("add");
				break;
			case "-":
				calculator.operation("substract");
				break;
			case "*":
				calculator.operation("multiply");
				break;
			case "/":
				calculator.operation("divide");
				break;
			case "=":
				calculator.equal();
				break;
			case ".":
				calculator.comma();
				break;
			case "±":
				calculator.changeSign();
				break;
			default:
				break;
		}
		displayResult();
	}

	private void displayResult() {
		display.setText(calculator.getCurrentResult());
	}

}