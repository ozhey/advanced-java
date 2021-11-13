import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

// associative sorted (ascending) table, capable of storing key-value pairs.
public class AssociationTable<K extends Comparable<K>, V> {
    
    private TreeMap<K, V> treemap;

    // constrcut empty AssociationTable
    public AssociationTable() {
        treemap = new TreeMap<K,V>();
    }

    // construct a new AssociationTable from a keys and values arrays
    public AssociationTable(K[] keys, V[] values) throws IllegalArgumentException {
        if (keys.length != values.length) {
            throw new IllegalArgumentException("error: got keys array and values array of a different length");
        }
        treemap = new TreeMap<K,V>();
        for (int i = 0; i < keys.length; i++) {
            treemap.put(keys[i], values[i]);
        }
    }

    // adds an element to the AssociationTable
    public void add(K key, V value) {
        treemap.put(key, value);
    }

    // gets a key and returns the corresponding value, or null if wasn't found
    public V get(K key) {
        return treemap.get(key);
    }

    // gets a key and returns a boolean value indicating if it exists in the table
    public boolean contains(K key) {
        if (treemap.containsKey(key)) {
            return true;
        }
        return false;
    }

    // gets a key. if the key exists, it removes it from the table and returns true
    // otherwise it does nothing and returns false
    public boolean remove(K key) {
        V value = treemap.remove(key);
        if (value != null) {
            return true;
        }
        return false;
    }

    // returns the size (number of key-value pairs) of the table
    public int size() {
        return treemap.size(); 
    }

    // returns an iterator to iterate over the table
    public Iterator<Map.Entry<K,V>> keyIterator() {
        return treemap.entrySet().iterator();
    }

}
