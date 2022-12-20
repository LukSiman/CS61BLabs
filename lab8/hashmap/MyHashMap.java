package hashmap;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;

    private static int DEFAULT_SIZE = 16;
    private static double DEFAULT_LOAD = 0.75;

    private double loadFactor;

    private int itemCount = 0;
    private int bucketCount = 0;

    // You should probably define some more!

    /**
     * Constructors
     */
    public MyHashMap() {
        this(DEFAULT_SIZE);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.buckets = createTable(initialSize);
        int index = 0;
        for (Collection<Node> bucketIterator : this.buckets) {
            this.buckets[index] = createBucket();
            index++;
        }
        this.bucketCount = initialSize;
        this.itemCount = 0;
        this.loadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        this.buckets = createTable(DEFAULT_SIZE);

        int index = 0;
        for (Collection<Node> bucketIterator : this.buckets) {
            this.buckets[index] = createBucket();
            index++;
        }

        this.itemCount = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int bucketIndex = Math.floorMod(key.hashCode(), this.bucketCount);

        Collection<Node> bucket = this.buckets[bucketIndex];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(K key) {
        int bucketIndex = Math.floorMod(key.hashCode(), this.bucketCount);

        Collection<Node> bucket = this.buckets[bucketIndex];

        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.itemCount;
    }

    @Override
    public void put(K key, V value) {
        Node node = createNode(key, value);
        int bucketIndex = Math.floorMod(node.key.hashCode(), this.bucketCount);

        if (containsKey(key)) {
            Collection<Node> bucket = this.buckets[bucketIndex];

            for (Node iterateNode : bucket) {
                if (iterateNode.key.equals(key)) {
                    iterateNode.value = node.value;
                    return;
                }
            }

        } else {
            double loadCheck = this.itemCount / this.bucketCount;
            int newSize = this.bucketCount * 2;

            if (loadCheck > this.loadFactor) {
                Collection<Node>[] newBuckets = createTable(newSize);

                int index = 0;
                for (Collection<Node> bucketIterator : newBuckets) {
                    newBuckets[index] = createBucket();
                    index++;
                }

                Iterator<K> itt = iterator();
                while (itt.hasNext()) {
                    K currKey = itt.next();
                    V currValue = get(currKey);
                    Node currNode = createNode(currKey, currValue);
                    int newBucketIndex = Math.floorMod(currNode.key.hashCode(), newSize);
                    newBuckets[newBucketIndex].add(currNode);
                }

                this.buckets = newBuckets;
            }
            buckets[bucketIndex].add(node);
            this.itemCount++;
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();

        for (Collection<Node> bucket : this.buckets) {
            for (Node node : bucket) {
                keys.add(node.key);
            }
        }

        return keys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        Set<K> keys = keySet();
        Iterator keyIterator = keys.iterator();

        return keyIterator;
//        return new Iterator<K>() {
//            @Override
//            public boolean hasNext() {
//                return false;
//            }
//
//            @Override
//            public K next() {
//                keys.
//                return null;
//            }
//        };
    }
}
