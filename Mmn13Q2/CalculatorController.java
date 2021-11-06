import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

// CalculatorController contains all the logic of the Calculator and the interaction with the GUI.
// The calculator's logic is based on Apple's OSX basic calculator and follows the rules of the assignment.
// It computes the result on every operation (so it can't help with operator precedence).
public class CalculatorController {

	@FXML
	private TextArea display;
	private String operator;
	private double prevNum, curNum;
	private boolean opFlag; // will be true if the last action was an operation

	// constructor
	public CalculatorController() {
		prevNum = 0;
		curNum = 0;
		operator = "";
		opFlag = false;
	}

	// handleKeyPress routes key presses to the correct function or handles them by
	// itself (numbers).
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
			executeNumberClick(keyText);
			break;
		case "+":
			executeOperation("add");
			break;
		case "-":
			executeOperation("substract");
			break;
		case "*":
			executeOperation("multiply");
			break;
		case "/":
			executeOperation("divide");
			break;
		case "=":
			handleEquals();
			break;
		case ".":
			handleComma();
			break;
		case "±":
			handleChangeSign();
			break;
		default:
			break;
		}
	}

	// shandles the numerical buttons. It sets the text accordingly.
	@FXML
	private void handleNum(Event event) {
		Button btn = (Button) event.getSource();
		String btnText = btn.getText();
		executeNumberClick(btnText);
	}

	// delete resets the calculator's memory and sets the calculator's text to an empty text
	@FXML
	private void handleDelete() {
		display.setText("");
		prevNum = 0;
		curNum = 0;
		operator = "";
		opFlag = false;
	}

	// handles clicks on +-/* buttons
	@FXML
	private void handleOperation(Event event) {
		Button btn = (Button) event.getSource();
		executeOperation(btn.getId());
	}

	// handleChangeSign simply changes the sign of the number that's displayed on screen.
	// Note: this is the way OSX's calculator works, this is why I implemented it this way :)
	@FXML
	private void handleChangeSign() {
		double number = parseDouble(display.getText());
		display.setText(fixedNumber(-number));
	}
	
	// calculates and displays the result.
	@FXML
	private void handleEquals() {
		curNum = parseDouble(display.getText());
		display.setText(calcResult());
		prevNum = parseDouble(display.getText());
		operator = "";
		opFlag = false;
	}
	
	// handleComma adds a comma to the current text, unless there's already a comma
	// (to prevent duplicates)
	@FXML
	private void handleComma() {
		String curText = display.getText();
		if (opFlag || curText.length() == 0) {
			display.setText("0.");
		} else if (!curText.contains(".")) {
			display.setText(curText + ".");
		}
		opFlag = false;
	}
	
	// executeOperation gets a string representing an operation (+, -, * or /). It
	// calculates the last result, and only then it changes the current operator.
	private void executeOperation(String op) {
		if (opFlag) {
			operator = op;
		} else {
			opFlag = true;
			curNum = parseDouble(display.getText());
			display.setText(calcResult());
			prevNum = parseDouble(display.getText());
			operator = op;
		}
	}

	// gets the entered number and sets the display accordingly
	private void executeNumberClick(String num) {
		if (opFlag) {
			display.setText(num);
		} else {
			display.setText(display.getText() + num);
		}
		opFlag = false;
	}

	// parseDouble is a wrapper around Double.parseDouble, but it also checks if the
	// parsed text isn't empty and returns 0 if the parsing fails.
	private double parseDouble(String text) {
		if (text != null && text.length() > 0) {
			try {
				return Double.parseDouble(text);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 0;
	}
	
	// calcResult computes the result based on the current operator
	private String calcResult() {
		switch (operator) {
			case "add":
			return fixedNumber(prevNum + curNum);
			case "divide":
			return fixedNumber(prevNum / curNum);
			case "substract":
			return fixedNumber(prevNum - curNum);
			case "multiply":
			return fixedNumber(prevNum * curNum);
			default:
			return fixedNumber(curNum);
		}
	}

	// fixedNumber makes sure the number is printed with a max of 5 digits after the
	// decimal separator, and with no redundant zeros.
	private String fixedNumber(double num) {
		if (num == (long) num)
			return String.format("%d", (long) num);
		return String.format("%.5f", num).replaceAll("0*$", "");
	}
}