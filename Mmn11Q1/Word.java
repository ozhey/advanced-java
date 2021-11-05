
// Word represents a word that's supposed be guessed by a user. 
public class Word {

    private String word;
    private boolean[] guessed; // characters of the current word which were guessed correctly
    private boolean[] guessedChars; // characters the user tried to guess
    private int numbOfGuesses; 
    private static final int NUM_OF_ENGLISH_CHARS = 26;

    // Constructor
    public Word(String word) {
        newWord(word);
    }

    public void newWord(String word) {
        this.word = word;
        this.guessed = new boolean[word.length()];
        this.guessedChars = new boolean[NUM_OF_ENGLISH_CHARS];
        this.numbOfGuesses = 0;
    }

    // Gets a character from the user, checks if the word contains it, and marks
    // it if the guess was correct. Returns true if the user guessed the whole word,
    // or false if there are still more characters to guess.
    public boolean guess(char c) {
        numbOfGuesses++;
        int numOfCharsGuessed = 0;
        int inputCharIndex = (int) (c - 'a');
        guessedChars[inputCharIndex] = true;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                guessed[i] = true;
            }
            if (guessed[i]) {
                numOfCharsGuessed++;
            }
        }
        if (numOfCharsGuessed == guessed.length) {
            return true;
        }
        return false;
    }

    // Returns the current word.
    public String getWord() {
        return word;
    }

    // returns the number of guesses
    public int getNumOfGuesses() {
        return numbOfGuesses;
    }

    // Returns the current word, but replaces all of the uncovered characters with underscores.
    public String getWordWithUnderscores() {
        String wordStatus = "";
        for (int i = 0; i < guessed.length; i++) {
            if (guessed[i]) {
                wordStatus = wordStatus + word.charAt(i) + " ";
            } else {
                wordStatus = wordStatus + "_ ";
            }
        }
        return wordStatus;
    }

    // returns an ordered string of all of the available chars
    // example: "a c e x z"
    public String getAvailableCharacters() {
        String available = "";
        for (int i = 0; i < guessedChars.length; i++) {
            if (!guessedChars[i]) {
                available = available + (char) (i + 'a') + " ";
            }
        }
        return available;
    }
}
