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
            this.key = null;
            this.value = null;
            this.left = null;
            this.right = null;
        }

        private BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private int size;
    private BSTNode head;

    public BSTMap() {
        this.head = new BSTNode();
        this.size = 0;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        BSTNode result = getValue(this.head, key);
        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public V get(K key) {
        BSTNode result = getValue(this.head, key);
        if(result != null){
            return result.value;
        }
        return null;
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
        putKeyValue(this.head, key, value);
    }

    private BSTNode putKeyValue(BSTNode node, K key, V value) {
        if (node == null) {
            return new BSTNode(key, value);
        }

        if (node.key == null) {
            node.key = key;
            node.value = value;
            return node;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = putKeyValue(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = putKeyValue(node.right, key, value);
        } else {
            node.key = key;
            node.value = value;
            return node;
        }
        return node;
    }

    public void printInOrder(BSTNode node) {
        printInOrder(node.left);
        System.out.println(node.key);
        printInOrder(node.right);
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
