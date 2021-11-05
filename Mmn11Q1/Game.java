import java.util.Scanner;

// Represents a guessing game object. Initialize a game object and call the start method to begin playing. 
public class Game {

    private boolean didWin; // if the user guessed the word, didWin will become true and the game will start over
    private Word word;
    private String input;
    private static final Scanner sc = new Scanner(System.in);

    // constructor
    public Game() {
        this.word = new Word(Vocabulary.getRandomWord());
        this.didWin = false;
        this.input = "";
    }

    // Start starts the game loop
    public void start() {
        System.out.printf("%n%nWelcome to the word guessing game. You will need to guess a random word. In each turn you will guess one character.%n");
        System.out.printf("To exit at any time, enter 'exit'%n%n");
        while (!input.equals("exit")) {
            if (didWin) {
                System.out.printf("%nYou guessed it right! The word is: " + word.getWord() + ". It took you "
                        + word.getNumOfGuesses() + " guesses.%n%n");
                word.newWord(Vocabulary.getRandomWord());
                didWin = false;
            }
            String availableChars = word.getAvailableCharacters();
            System.out.printf("%n" + word.getWordWithUnderscores() + "%n");
            System.out.println("The available characters are: " + availableChars);
            System.out.print("Try to guess the word. Enter a character: ");
            input = sc.nextLine();
            if (input.equals("exit")) {
                continue;
            }
            if (input.length() != 1) {
                System.out.println("Error - enter exactly one character.");
                continue;
            }
            char inputChar = input.charAt(0);
            if (input.length() != 1 || inputChar < 'a' || inputChar > 'z' || availableChars.indexOf(inputChar) == -1) {
                System.out.println("Error - enter a lowercase english character that wasn't chosen before.");
                continue;
            }
            didWin = word.guess(inputChar);
        }
        sc.close();
    }

}