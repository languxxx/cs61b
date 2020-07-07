import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V>{
    private int bucketNum, N;
    private double loadFactor;
    private ArrayList<Entry<K, V>> bucket;

    private static class Entry<K, V> implements Map.Entry<K, V>{
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        public void setNext(Entry<K, V> next) {
            if (next == null) {
                throw new IllegalArgumentException ("Given next entry is null");
            }
            else if (this.next != null) {
                setNext(this.next);
            }
            else {
                this.next = next;
            }
        }
    }

    public MyHashMap() {
        this.bucketNum = 16;
        this.loadFactor = 0.75;
        this.N = 0;
        bucket = new ArrayList<Entry<K, V>>(this.bucketNum);
        bucket.addAll(Collections.nCopies(bucketNum, null));
    }

    public MyHashMap(int initialSize) {
        this.bucketNum = initialSize;
        this.loadFactor = 0.75;
        this.N = 0;
        bucket = new ArrayList<Entry<K, V>>(this.bucketNum);
        bucket.addAll(Collections.nCopies(bucketNum, null));
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.bucketNum = initialSize;
        this.loadFactor = loadFactor;
        this.N = 0;
        bucket = new ArrayList<Entry<K, V>>(this.bucketNum);
        bucket.addAll(Collections.nCopies(bucketNum, null));
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        this.N = 0;
        bucket = new ArrayList<Entry<K, V>>(this.bucketNum);
        bucket.addAll(Collections.nCopies(bucketNum, null));
    }

    /** Return a value in the range 0 .. bins.size ()-1, based on * the hash code of KEY. */
    private int hash (K key) {
        if (key == null)
            return 0;
        else
            return (0x7fffffff & key.hashCode ()) % bucket.size ();
    }

    private Entry<K, V> findKey(int code, K key) {
        Entry<K, V> e = bucket.get(code);
        while (e != null) {
            if (e.getKey().equals(key))
                return e;
            else
                e = e.getNext();
        }
        return e;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        int code = hash(key);
        return findKey(code, key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        int code = hash(key);
        Entry<K, V> e = findKey(code, key);
        if (e == null) {
            return null;
        }
        else return e.getValue();
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return N;
    }

    private void grow() {
        int oldBucketNum = this.bucketNum;
        this.bucketNum *= 2;
        ArrayList<Entry<K, V>> newBucket = new ArrayList<Entry<K, V>>(this.bucketNum);
        newBucket.addAll(Collections.nCopies(this.bucketNum, null));

        Entry<K, V> e;
        for (int i = 0; i < oldBucketNum; i++) {
            e = bucket.get(i);
            while (e != null) {
                put(e.getKey(), e.getValue(), newBucket);
                e = e.getNext();
            }
        }

        this.bucket = newBucket;
    }

    private boolean put(K key, V value, ArrayList<Entry<K, V>> bins) {
        int code = hash(key);
        Entry<K, V> e = bins.get(code);
        Entry<K, V> next = new Entry<>(key, value);

        if (e == null) {
            bins.set(code, next);
            return true;
        }

        while (e.getNext() != null) {
            if (e.getKey().equals(key)) {
                e.setValue(value);
                return false;
            }
            else
                e = e.getNext();
        }

        if (e.getKey().equals(key)) {
            e.setValue(value);
            return false;
        }
        else
            e.setNext(next);
            return true;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if ((double) N/(double) bucketNum >= loadFactor){
            grow();
        }

        boolean putFlag;
        putFlag = put(key, value, bucket);
        if (putFlag)
            N++;

    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        Entry<K, V> e;
        for (int i = 0; i < bucketNum; i++) {
            e = bucket.get(i);
            while (e != null) {
                set.add(e.getKey());
                e = e.getNext();
            }
        }
        return set;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new UnsupportedOperationException("Method 'remove' unimplemented");
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Method 'remove' unimplemented");
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
