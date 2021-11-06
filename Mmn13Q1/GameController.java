import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text; 
import java.util.ArrayList;

/**
 * GameController
 */
public class GameController {

    @FXML
    private Text question;
    @FXML
    private Text answerOne;
    @FXML
    private Text answerTwo;
    @FXML
    private Text answerThree;
    @FXML
    private Text answerFour;
    @FXML
    private Button btnOne;
    @FXML
    private Button btnTwo;
    @FXML
    private Button btnThree;
    @FXML
    private Button btnFour;
    @FXML    
    private Text result;
    @FXML
    private Button next;
    private Text[] answersText; // an array of 4 text elements that contain the answers
    
    private QuestionRepo repo;
    private int numOfQuestions;
    private int currQuestionNum;
    private Question currQuestion;
    private double score;
    private int MAX_SCORE = 100;
    
    public GameController() {
        repo = new QuestionRepo("exam");
        numOfQuestions = repo.getNumOfQuestions();
    }
    
    // starts the game - sets the current question to one and loads it.
    @FXML
    private void initialize() {
        currQuestionNum = 0;
        score = 0;
        result.setText("");
        answersText = new Text[] {answerOne, answerTwo, answerThree, answerFour};
        if (numOfQuestions == 0) { 
            disableAnsBtns();
            next.setDisable(true);
            question.setFill(Color.RED);
            question.setText("Error: the questions file wasn't found or did not contain any questions");
        } else {
            loadQuestion();
        }
    }

    // gets the user's answer.
    @FXML
    private void handleAnswerClick(Event event) {
        Button btn = (Button) event.getSource();
        String id = btn.getId();
        switch (id) {
            case "btnOne":
                showResult(answerOne.getText());
                break;
            case "btnTwo":
                showResult(answerTwo.getText());
                break;
            case "btnThree":
                showResult(answerThree.getText());
                break;
            case "btnFour":
                showResult(answerFour.getText());
                break;
            default:
                break;
        }
        disableAnsBtns();
    }
    
    // shows the next question, or the result of the game if there are no more questions.
    @FXML
    private void handleNext() {
        currQuestionNum++;
        if (next.getText().equals("End Quiz")) {
            result.setFill(Color.BLACK);
            result.setText("Your result is: " + String.format ("%.1f",score));
            next.setText("Start a New Game");
        } else if (currQuestionNum < numOfQuestions) {
            if (currQuestionNum == numOfQuestions - 1) {
                next.setText("End Quiz");
            }
            loadQuestion();
            enableAnsBtns();
            result.setText("");
        } else if (next.getText().equals("Start a New Game")) {
            initialize();
        }
    }

    
    // loads the current question to the screen
    private void loadQuestion() {
        currQuestion = repo.getQuestion(currQuestionNum);
        question.setText("Question: " + currQuestion.getQuestion());
        ArrayList<String> answers = currQuestion.getAnswers();
        for (int i = 0; i < answersText.length; i++) {
            answersText[i].setText(answers.get(i));
        }
    }

    // gets an answer and prints to the user if the answer was correct or not.
    private void showResult(String answer) {
        if (answer.equals(currQuestion.getCorrectAnswer())) {
            result.setFill(Color.GREEN);
            result.setText("Correct!");
            score += (double) MAX_SCORE / numOfQuestions;
        } else {
            result.setFill(Color.RED);
            result.setText("Wrong!");
        }
    }

    // enables the answer buttons
    private void disableAnsBtns() {
        btnOne.setDisable(true);
        btnTwo.setDisable(true);
        btnThree.setDisable(true);
        btnFour.setDisable(true);
    }

    // disables the answer buttons
    private void enableAnsBtns() {
        btnOne.setDisable(false);
        btnTwo.setDisable(false);
        btnThree.setDisable(false);
        btnFour.setDisable(false);
    }
 
}