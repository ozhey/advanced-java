import java.util.ArrayList;

// MergeSort intent is to sort an array with the merge sort algorithm using multi-threading
public class MergeSort {

    private ArrayList<ArrayList<Integer>> lists;
    private int maxThreads, waiting;
    private boolean done;

    // constructor. creates the repo with m threads and n arrays
    public MergeSort(int[] arr, int m) {
        lists = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < arr.length; i++) {
            lists.add(new ArrayList<Integer>());
            lists.get(i).add(arr[i]);
        }
        done = false;
        waiting = 0;
        maxThreads = m;
    }

    // removes two lists, merges them and returns the merged list
    public synchronized ArrayList<ArrayList<Integer>> removeCouple() {
        while (lists.size() <= 1 && !done) {
            if (waiting < maxThreads - 1) {
                waiting++;
                try {
                    wait();
                } catch (InterruptedException e) {
                }
                waiting--;
            } else {
                done = true;
            }
        }
        if (done) {
            notifyAll();
            return null;
        } else {
            ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
            result.add(lists.remove(0));
            result.add(lists.remove(0));
            return result;
        }
    }

    // inserts an arraylist to the lists array and notifies all threads
    public synchronized void insert(ArrayList<Integer> list) {
        lists.add(list);
        notifyAll();
    }

    // returns the sorted array, only after the sorting proccess has finished
    public synchronized ArrayList<Integer> getResult() {
        while (!done) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        return lists.get(0);
    }

}