import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private Node root;

    private class Node{
        private K Key;
        private V Val;
        private Node left;
        private Node right;
        private int size;

        private Node(K Key, V Val){
            this.Key = Key;
            this.Val = Val;
            size = 1;
        }
    }

    public Node put(Node n, K key, V value){
        if (n == null){
            return new Node(key, value);
        }
        int cmp = key.compareTo(n.Key);
        if (cmp < 0){
            n.left = put(n.left, key, value);
        } else if (cmp > 0) {
            n.right = put(n.right, key, value);
        } else{
            n.Val = value;
        }
        return n;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public V get(Node n, K key){
        if (n == null){
            return null;
        }
        int cmp = key.compareTo(n.Key);
        if (cmp < 0){
            return get(n.left, key);
        } else if (cmp > 0) {
            return get(n.right, key);
        } else {
            return n.Val;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    public boolean containsKey(Node n, K key){
        if (n == null){
            return false;
        }
        if (n.Key.equals(key)){
            return true;
        }
        else {
            return containsKey(n.left, key) || containsKey(n.right, key);
        }
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    public int size(Node n) {
        if (n == null){
            return 0;
        }
        return n.size + size(n.left) + size(n.right);
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size(root);
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        this.root = null;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
