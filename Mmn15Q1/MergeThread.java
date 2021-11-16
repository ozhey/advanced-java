import java.util.ArrayList;

// a thread that takes part in the merge sort proccess.
// the thread merges two arrays from the array in a loop untill the sorting is done.
public class MergeThread extends Thread {

    private MergeSort merger;

    public MergeThread(MergeSort merger) {
        this.merger = merger;
    }

    // the thread will remove two arrays from the merger, merge them and insert them back to the merger.
    // the thread will keep doing it untill there are no more couples to merge.
    public void run() {
        ArrayList<ArrayList<Integer>> couple;
        couple = merger.removeCouple();
        while (couple != null) {
            merger.insert(merge(couple.remove(0), couple.remove(0)));
            couple = merger.removeCouple();
        }
    }

    // gets two sorted arraylists and returns a sorted merged arraylist
    private ArrayList<Integer> merge(ArrayList<Integer> l1, ArrayList<Integer> l2) {
        ArrayList<Integer> merged = new ArrayList<>();
        int i, j;
        for (i = 0, j = 0; i < l1.size() && j < l2.size();) {
            if (l1.get(i) < l2.get(j)) {
                merged.add(l1.get(i));
                i++;
            } else {
                merged.add(l2.get(j));
                j++;
            }
        }
        while (i < l1.size()) {
            merged.add(l1.get(i++));
        }
        while (j < l2.size()) {
            merged.add(l2.get(j++));
        }
        return merged;
    }
}
