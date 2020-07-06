import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private BSTMap left, right;
    private K k;
    private V v;

    BSTMap(K key, V value) {
        k = key;
        v = value;
        left = null;
        right = null;
    }

    BSTMap() {
        k = null;
        v = null;
        left = null;
        right = null;
    }
    /** Removes all of the mappings from this map. */
    public void clear() {
        this.left = null;
        this.right = null;
        k = null;
        v = null;
    }

    private V findKey(BSTMap T, K key) {
        if (T == null) return null;
        else if (T.k.compareTo(key) == 0) return (V)T.v;
        else if (T.k.compareTo(key) > 0) return findKey(T.left, key);
        else return findKey(T.right, key);
    }
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (this.k == null) return false;
        else if (this.k.compareTo(key) == 0) return true;
        else if (this.k.compareTo(key) > 0) return (findKey(this.left, key) != null);
        else return (findKey(this.right, key) != null);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (k == null)
            return null;
        else if (this.k.compareTo(key) == 0) return (V)this.v;
        else if (this.k.compareTo(key) > 0) return findKey(this.left, key);
        else return findKey(this.right, key);
    }

    private int count(BSTMap T) {
        if (T == null) return 0;
        else return 1 + count(T.left) + count(T.right);
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        if (k == null) return 0;
        else return 1 + count(this.left) + count(this.right);
    }

    private BSTMap putResursive(BSTMap T, K key, V value) {
        if (T == null) return new BSTMap(key,value);
        else if (T.k.compareTo(key) == 0) {
            T.v = value;
            return T;
        }
        else if ((T.k).compareTo(key) > 0) {
            T.left = putResursive(T.left, key,value);
        }
        else T.right = putResursive(T.right, key,value);
        return T;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (k == null) {
            k = key;
            v = value;
        }
        else if (this.k.compareTo(key) == 0) {
            this.v = value;
        }
        else if ((this.k).compareTo(key) > 0) {
            this.left = putResursive(this.left, key,value);
        }
        else this.right = putResursive(this.right, key,value);
    }

    public void printInOrder() {

    }
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
