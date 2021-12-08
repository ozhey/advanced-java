import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.ArrayList;

// the controller class for the quiz. 
public class GameController {

    @FXML private Text question;
    @FXML private Text answerOne;
    @FXML private Text answerTwo;
    @FXML private Text answerThree;
    @FXML private Text answerFour;
    @FXML private Button btnOne;
    @FXML private Button btnThree;
    @FXML private Button btnTwo;
    @FXML private Button btnFour;
    @FXML private Text result;
    @FXML private Button next;
    private Text[] answersText; // an array of 4 text elements that contain the answers
    private Game game;

    public GameController() {
        game = new Game("exam");
    }

    // initializes the game and starts it
    @FXML
    private void initialize() {
        answersText = new Text[] { answerOne, answerTwo, answerThree, answerFour };
        if (game.hasQuestions()) {
            result.setText("");
            loadQuestion();
        } else {
            disableAnsBtns();
            next.setDisable(true);
            question.setFill(Color.RED);
            question.setText("Error: the questions file wasn't found or did not contain any questions");
        }
    }

    // starts the game and sets the current question to 0
    private void restartGame() {
        game.restart();
        enableAnsBtns();
        next.setText("Next Question");
        result.setText("");
        loadQuestion();
    }

    // gets the user's answer.
    @FXML
    private void handleAnswerClick(Event event) {
        Button btn = (Button) event.getSource();
        String id = btn.getId();
        switch (id) {
            case "btnOne":
                submitAnswer(answerOne.getText());
                break;
            case "btnTwo":
                submitAnswer(answerTwo.getText());
                break;
            case "btnThree":
                submitAnswer(answerThree.getText());
                break;
            case "btnFour":
                submitAnswer(answerFour.getText());
                break;
            default:
                break;
        }
        disableAnsBtns();
    }

    // loads the next question, or the result if there are no more questions.
    @FXML
    private void handleNext() {
        game.nextQuestion();
        if (next.getText().equals("End Quiz")) {
            result.setFill(Color.BLACK);
            result.setText("Your result is: " + String.format("%.1f", game.getScore()));
            next.setText("Start a New Game");
        } else if (next.getText().equals("Start a New Game")) {
            restartGame();
        } else {
            if (game.isLastQuestion()) {
                next.setText("End Quiz");
            }
            loadQuestion();
            enableAnsBtns();
            result.setText("");
        }
    }

    // loads the current question to the screen
    private void loadQuestion() {
        question.setText("Question: " + game.getQuestion());
        ArrayList<String> answers = game.getPossibleAnswers();
        for (int i = 0; i < answersText.length; i++) {
            answersText[i].setText(answers.get(i));
        }
    }

    // gets an answer and prints to the user if the answer was correct or not.
    private void submitAnswer(String answer) {
        if (game.submitAnswer(answer)) {
            result.setFill(Color.GREEN);
            result.setText("Correct!");
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