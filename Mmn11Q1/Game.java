import java.util.Scanner;

// Represents a guessing game object. Initialize a game object and call the start method to begin playing. 
public class Game {

    private boolean didWin; // if the user guessed the word, didWin will become true and the game will start over
    private Word word;
    private static final Scanner sc = new Scanner(System.in);

    public Game() {
        this.word = new Word(Vocabulary.getRandomWord());
        this.didWin = false;
    }

    // Start starts the game loop
    public void start() {
        String input = "";
        System.out.printf("%n%nWelcome to the word guessing game. You will need to guess a random word. In each turn you will guess one charecter.%n");
        System.out.printf("To exit at any time, enter 'exit'%n%n");
        while (!input.equals("exit")) {
            if (didWin) {
                System.out.printf("%nYou guessed it right! The word is: " + word.getWord() + ". It took you "
                        + word.getNumOfGuesses() + " guesses.%n%n");
                word.resetWord(Vocabulary.getRandomWord());
                didWin = false;
            }
            String availableChars = word.getAvailableCharecters();
            System.out.printf("%n" + word.getWordWithUnderscores() + "%n");
            System.out.println("The available charecters are: " + availableChars);
            System.out.print("Try to guess the word. Enter a charecter: ");
            input = sc.nextLine();
            if (input.equals("exit")) {
                continue;
            }
            if (input.length() != 1) {
                System.out.println("Error - enter exactly one charecter.");
                continue;
            }
            char inputChar = input.charAt(0);
            if (input.length() != 1 || inputChar < 'a' || inputChar > 'z' || availableChars.indexOf(inputChar) == -1) {
                System.out.println("Error - enter a lowercase english charecter which weren't chosen before.");
                continue;
            }
            didWin = word.guess(inputChar);
        }
        sc.close();
    }

}