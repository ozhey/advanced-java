import java.util.Random;

public class Main {

    private static final int NUM_OF_FLIGHTS = 10;

    public static void main(String[] args) {
        Random rnd = new Random();
        AirPort telAviv = new AirPort("Tel Aviv");
        AirPort losAngeles = new AirPort("Los Angeles");
        for (int i = 1; i <= NUM_OF_FLIGHTS; i++) {
            if (rnd.nextBoolean()) {
                new Flight(i, telAviv, losAngeles).start();
            } else {
                new Flight(i, losAngeles, telAviv).start();
            }
        }
    }

}
