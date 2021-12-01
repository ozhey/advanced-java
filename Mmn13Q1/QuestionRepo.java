import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// QuestionRepo reads a file and constructs a list of questions.
public class QuestionRepo {

    private static final int NUM_OF_ANSWERS = 4; // every question consists of 5 lines - 1 question and 4 answers
    private ArrayList<Question> questions;

    // constructor. reads the file into the questions and answers ArrayLists.
    public QuestionRepo(String fileName) {
        String currentQuestion = "";
        questions = new ArrayList<>();
        ArrayList<String> answersList = new ArrayList<>();
        try (Scanner input = new Scanner(new File(fileName + ".txt"))){
            while (input.hasNext()) {
                currentQuestion = input.nextLine();;
                for (int i = 0; i < NUM_OF_ANSWERS; i++) {
                    answersList.add(input.nextLine()); 
                }
                this.questions.add(new Question(currentQuestion, answersList));
                answersList = new ArrayList<>();
            }
        } catch (FileNotFoundException e) { }
    }

    // returns question number questionNum
    public Question getQuestion(int questionNum) {
        return questions.get(questionNum);
    }

    // returns the total number of questions
    public int getNumOfQuestions() {
        return questions.size();
    }


}