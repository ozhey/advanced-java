import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

// an airport allows flights to depart or land at one of it's runways.
// it also 
public class AirPort {

    private static final int NUM_OF_RUNWAYS = 3;
    private final String name;
    private Queue<Integer> queue;
    private int runwaysInUse;
    private boolean[] runways;
    private Random rnd = new Random();

    // constructor
    public AirPort(String name) {
        queue = new PriorityQueue<Integer>();
        runwaysInUse = 0;
        runways = new boolean[NUM_OF_RUNWAYS];
        this.name = name;
    }

    // departure procedure for a specific flight
    public void depart(int flightNumber) {
        int runwayNum = startProcedure(flightNumber);
        System.out.println("Flight number " + flightNumber + " has started the procedure of departure from airport "
                + name + ", runway number " + (runwayNum + 1));
        // simulate departure by sleeping for 2 to 5 seconds
        try {
            Thread.sleep((long) (rnd.nextInt(3000) + 2000));
        } catch (InterruptedException e) {
        }
        System.out.println("Flight number " + flightNumber + " has departed from airport " + name + ", runway number "
                + (runwayNum + 1));
        finishProcedure(flightNumber, runwayNum);
    }

    // landing procedure for a specific flight
    public void land(int flightNumber) {
        System.out.println("Flight number " + flightNumber
                + " finished it's flight and is now waiting to land at airport " + name);
        int runwayNum = startProcedure(flightNumber);
        System.out.println("Flight number " + flightNumber + " has started landing procedure at airport " + name
                + ", runway number " + (runwayNum + 1));
        // simulate landing by sleeping for 2 to 5 seconds
        try {
            Thread.sleep((long) (rnd.nextInt(3000) + 2000));
        } catch (InterruptedException e) {
        }
        System.out.println("Flight number " + flightNumber + " has landed at airport " + name + ", runway number "
                + (runwayNum + 1));
        finishProcedure(flightNumber, runwayNum);
    }

    // starts a departure or landing procedure and occupies the runway
    private synchronized int startProcedure(int flightNumber) {
        queue.add(flightNumber);
        while (runwaysInUse == NUM_OF_RUNWAYS || queue.peek() != flightNumber) {
            System.out.println("Flight number " + flightNumber + " doesn't have a free runway, and is waiting :(");
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        queue.remove();
        runwaysInUse++;
        return availableRunway();
    }

    // finishes a departure or landing procedure and frees the runway
    private synchronized void finishProcedure(int flightNumber, int runwayNum) {
        runwaysInUse--;
        notifyAll();
        runways[runwayNum] = false;
    }

    // looks for an available runway and returns it, or -1 if no runway is available
    private synchronized int availableRunway() {
        for (int i = 0; i < runways.length; i++) {
            if (!runways[i]) {
                runways[i] = true;
                return i;
            }
        }
        // should never happen, we only call this method when there are available runways
        return -1;
    }

}
