import java.util.ArrayList;
import java.util.Collections;

// question represents a question in the quiz.
public class Question {
    
    private String question;
    private ArrayList<String> possibleAnswers;
    private String correctAnswer;
    
    public Question(String question, ArrayList<String> answers) {
        this.question = question;
        this.correctAnswer = answers.get(0); // the first answer is always the correct one
        Collections.shuffle(answers); // randomize the order of the questions
        this.possibleAnswers = answers;
    }

    // get the question itself
    public String getQuestion() {
        return question;
    }

    // get the list of possible answers
    public ArrayList<String> getAnswers() {
        return possibleAnswers;
    }

    // get the correct answer
    public String getCorrectAnswer() {
        return correctAnswer;
    }

}
