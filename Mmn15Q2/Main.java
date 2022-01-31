import java.util.Random;

public class Main {

    private static final int NUM_OF_FLIGHTS = 10;

    public static void main(String[] args) {
        Random rnd = new Random();
        AirPort tlvAirPort = new AirPort("Tel Aviv");
        AirPort laAirPort = new AirPort("Los Angeles");
        Flight[] flights = new Flight[NUM_OF_FLIGHTS];
        for (int i = 0; i < NUM_OF_FLIGHTS; i++) {
            if (rnd.nextBoolean()) { // randomly choose the direction of the flight
                flights[i] = new Flight(i+1, tlvAirPort, laAirPort);
            } else {
                flights[i] = new Flight(i+1, laAirPort, tlvAirPort);
            }
        }
        for (int i = 0; i < NUM_OF_FLIGHTS; i++) {
            flights[i].start();
        }
    }
}
