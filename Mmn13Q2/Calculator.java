// this class implements the required logic for the calculator application
// the calculator's logic is based on Apple's macOS basic calculator and follows the rules of the assignment.
// it computes the result on every operation, so it doesn't help with operator precedence.
public class Calculator {

    private String operator;
    private double prevNum, curNum;
    private boolean opFlag; // will be true if the last action was an operation
    private String text;

    // constructor
    public Calculator() {
        text = "";
        operator = "";
        prevNum = 0;
        curNum = 0;
        opFlag = false;
    }

    // returns the current result so it can be displayed on screen
    public String getCurrentResult() {
        return text;
    }

    // resets the calculator's memory
    public void reset() {
        text = "";
        prevNum = 0;
        curNum = 0;
        operator = "";
        opFlag = false;
    }

    // changes the sign of the current number.
    public void changeSign() {
        double number = parseDouble(text);
        text = fixedNumber(-number);
    }

    // calculates the result
    public void equal() {
        curNum = parseDouble(text);
        text = calcResult();
        prevNum = parseDouble(text);
        operator = "";
        opFlag = false;
    }

    // adds a comma to the current text unless there's already a comma
    public void comma() {
        String curText = text;
        if (opFlag || curText.length() == 0) {
            text = "0.";
        } else if (!curText.contains(".")) {
            text = curText + ".";
        }
        opFlag = false;
    }

    // gets a string representing an operation (+, -, * or /). It
    // calculates the last result, and only then it changes the current operator.
    public void operation(String op) {
        if (opFlag) {
            operator = op;
        } else {
            opFlag = true;
            curNum = parseDouble(text);
            text = calcResult();
            prevNum = parseDouble(text);
            operator = op;
        }
    }

    // gets a number and adds it to the currently displayed number
    public void number(String num) {
        if (opFlag) {
            text = num;
        } else {
            text += num;
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
                // supposedly it's not possible to insert input that's not convertable to a
                // double
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
