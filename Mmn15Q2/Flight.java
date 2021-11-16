import java.util.Random;

// Flight is a flight from one airport to another. 
public class Flight extends Thread {

    private int flightNumber;
    private AirPort to, from;
    private Random rnd = new Random();

    // constructor
    public Flight(int flightNumber, AirPort from, AirPort to) {
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
    }

    // starts the flight - from airport "from" to airport "to"
    public void run() {
        from.depart(flightNumber);
        // simulate flight by sleeping for 2 to 5 seconds
        try {
            Thread.sleep((long) (rnd.nextInt(3000) + 2000));
        } catch (InterruptedException e) {
        }
        to.land(flightNumber);
    }

}
