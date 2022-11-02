package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {

    private class BSTNode {
        private BSTNode left;
        private BSTNode right;
        private K key;
        private V value;

        private BSTNode() {
        }

        private BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size;
    private BSTNode head;

    public BSTMap() {
        BSTNode node = new BSTNode();
        this.size = 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        BSTNode result = getValue(this.head, key);
        return result.value;
    }

    private BSTNode getValue(BSTNode node, K key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getValue(node.left, key);
        } else {
            return getValue(node.right, key);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        this.size++;
    }

    public void printInOrder() {

    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
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
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }
}
