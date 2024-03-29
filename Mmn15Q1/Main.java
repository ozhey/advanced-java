import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int MIN_NUMBER = 1; // inclusive
    private static final int MAX_NUMBER = 101; // exclusive

    public static void main(String[] args) {
        Random rnd = new Random();
        Scanner scan = new Scanner(System.in);
        int n, m;
        System.out.println("Enter the amount of threads that you want to be used");
        m = getIntFromInput(scan);
        System.out.println("Enter the amount of numbers that you want to be in the array that's going to be sorted");
        n = getIntFromInput(scan);
        scan.close();
        int[] array = rnd.ints(n, MIN_NUMBER, MAX_NUMBER).toArray();
        MergeSort merger = new MergeSort(array, m);
        MergeThread[] threads = new MergeThread[m];
        for (int i = 0; i < m; i++) {
            threads[i] = new MergeThread(merger);
        }
        for (int i = 0; i < m; i++) {
            threads[i].start();
        }
        System.out.println(merger.getResult());
    }

    // gets an integer from the user, safely
    private static int getIntFromInput(Scanner scan) {
        int num = 0;
        while (num <= 0) {
            if (scan.hasNextInt()) {
                num = scan.nextInt();
            }
            if (num <= 0) {
                System.out.println("Error! Enter an integer bigger than 0");
                scan.nextLine();
            }
        }
        return num;
    }
}
