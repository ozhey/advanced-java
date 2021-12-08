import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// QuestionRepo reads a file and constructs a list of questions.
public class Game {

    private static final int NUM_OF_ANSWERS = 4; // every question consists of 5 lines - 1 question and 4 answers
    private ArrayList<Question> questions;
    private int MAX_SCORE = 100;
    private int numOfQuestions;
    private int currQuestionNum;
    private double score;

    // constructor. reads the file into the questions and answers ArrayLists.
    public Game(String fileName) {
        score = 0;
        currQuestionNum = 0;
        questions = new ArrayList<>();
        ArrayList<String> answersList = new ArrayList<>();
        String currentQuestion = "";
        try (Scanner input = new Scanner(new File(fileName + ".txt"))) {
            while (input.hasNext()) {
                currentQuestion = input.nextLine();
                for (int i = 0; i < NUM_OF_ANSWERS; i++) {
                    answersList.add(input.nextLine());
                }
                this.questions.add(new Question(currentQuestion, answersList));
                answersList = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
        }
        numOfQuestions = questions.size();
    }

    // returns the current question's text
    public String getQuestion() {
        return questions.get(currQuestionNum).getQuestion();
    }

    // returns possible answers
    public ArrayList<String> getPossibleAnswers() {
        return questions.get(currQuestionNum).getAnswers();
    }

    // increments current question by one
    public void nextQuestion() {
        currQuestionNum++;
    }

    // returns the current score
    public double getScore() {
        return score;
    }

    // gets an answer and returns if it was correct or not, and adds score
    public boolean submitAnswer(String answer) {
        if (answer.equals(questions.get(currQuestionNum).getCorrectAnswer())) {
            score += (double) MAX_SCORE / numOfQuestions;
            return true;
        }
        return false;
    }

    // returns true if it's the last question or false if not
    public boolean isLastQuestion() {
        if (currQuestionNum == numOfQuestions - 1) {
            return true;
        }
        return false;
    }

    // return true if there's at least one question in the game
    public boolean hasQuestions() {
        if (numOfQuestions > 0) {
            return true;
        }
        return false;
    }

    // restarts the game
    public void restart() {
        score = 0;
        currQuestionNum = 0;
    }
}