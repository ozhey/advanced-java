import java.util.Random;

public class Main {

    private static final int NUM_OF_FLIGHTS = 10;

    public static void main(String[] args) {
        Random rnd = new Random();
        AirPort tlvAirPort = new AirPort("Tel Aviv");
        AirPort laAirPort = new AirPort("Los Angeles");
        for (int i = 1; i <= NUM_OF_FLIGHTS; i++) {
            if (rnd.nextBoolean()) { // randomly choose the direction of the flight
                new Flight(i, tlvAirPort, laAirPort).start();
            } else {
                new Flight(i, laAirPort, tlvAirPort).start();
            }
        }
    }
}
