import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// QuestionRepo reads a file and constructs a list of questions.
public class QuestionRepo {

    private static final int NUM_OF_LINES_PER_Q = 5; // every question consists of 5 lines - 1 question and 4 answers
    private Question[] questions;

    // constructor. reads the file into the questions and answers ArrayLists.
    public QuestionRepo(String fileName) {
        int counter = 0;
        ArrayList<String> questionsList = new ArrayList<String>();
        ArrayList<ArrayList<String>> answersList = new ArrayList<ArrayList<String>>();
        try (Scanner input = new Scanner(new File(fileName + ".txt"))){
            while (input.hasNext()) {
                String st = input.nextLine();
                if (counter % NUM_OF_LINES_PER_Q == 0) { // If the line number divides with no remainder, it's a question
                    questionsList.add(st);
                } else {
                    if (counter % NUM_OF_LINES_PER_Q == 1) { // If the line number divides with a remainder of 1, it's the first answer for the question
                        answersList.add(new ArrayList<String>());
                    }
                    answersList.get(counter / NUM_OF_LINES_PER_Q).add(st); // Add the answer to the corresponding question
                }
                counter++;
            }
        } catch (FileNotFoundException e) {
            this.questions = new Question[0];
        }
        this.questions = new Question[questionsList.size()];
        for (int i = 0; i < this.questions.length; i++) {
            this.questions[i] = new Question(questionsList.get(i), answersList.get(i));
        }
    }

    // returns question number questionNum
    public Question getQuestion(int questionNum) {
        return questions[questionNum];
    }

    // returns the total number of questions
    public int getNumOfQuestions() {
        return questions.length;
    }


}