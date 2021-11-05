import java.util.Random;

// Vocabulary's only designation is to deliver a single random word from a pre defined array of words.
// It's not meant to be instantiated.
public final class Vocabulary {

    // auto generated array of random words
    private static final String[] vocabulary = { "judgment", "delicate", "bullet", "admire", "mechanical", "prestige",
            "smooth", "vertical", "haircut", "clay", "fan", "soldier", "stream", "magazine", "pull", "guarantee",
            "goal", "classroom", "cruelty", "sign", "person", "astonishing", "van", "shrink", "late", "teenager",
            "posture", "pier", "burn", "copyright", "disposition", "split", "subject", "soft", "mature", "term",
            "belong", "garlic", "cucumber", "engineer", "snow", "sum", "ranch", "mirror", "pension", "prosecution",
            "attachment", "powder", "experience", "roll", "tournament", "harm", "carrot", "capture", "fix", "pattern",
            "call", "profile", "island", "transition", "trace", "dance", "fly", "mixture", "shy", "triangle", "dealer",
            "forecast", "licence", "vote", "sight", "guide", "spokesperson", "subject", "small", "charismatic", "mind",
            "brain", "charm", "battlefield", "cheque", "brand", "layer", "cereal", "duty", "hobby", "fling", "deer",
            "abundant", "constitutional", "assume", "produce", "muggy", "atmosphere", "rare", "extreme", "dignity",
            "glare", "bishop", "discover" };

    // private constructor to avoid unnecessary intantiation of the class
    private Vocabulary() {
    }

    // returns a random word from the vocabulary.
    public static String getRandomWord() {
        Random rnd = new Random();
        return vocabulary[rnd.nextInt(vocabulary.length)];
    }

}