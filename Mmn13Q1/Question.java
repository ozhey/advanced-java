import java.util.ArrayList;
import java.util.Collections;

public class Question {
    
    private String question;
    private ArrayList<String> possibleAnswers;
    private String correctAnswer;
    
    public Question(String question, ArrayList<String> answers) {
        this.question = question;
        this.correctAnswer = answers.get(0); // the first answer is always the correct one
        Collections.shuffle(answers);
        this.possibleAnswers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<String> getAnswers() {
        return possibleAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

}
