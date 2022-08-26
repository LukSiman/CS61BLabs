package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    // No looping or recursion
    // Add item as the first node
    public void addFirst(T item) {
        Node firstItem = new Node(item, this.sentinel, this.sentinel.next);
        this.sentinel.next.prev = firstItem;
        this.sentinel.next = firstItem;
        this.size++;
    }

    // No looping or recursion
    // Add item as the last node
    public void addLast(T item) {
        Node lastItem = new Node(item, this.sentinel.prev, this.sentinel);
        this.sentinel.prev.next = lastItem;
        this.sentinel.prev = lastItem;
        this.size++;
    }

    // Constant time
    // return size of the list
    public int size() {
        return this.size;
    }

    //Prints the list from first to last item
    public void printDeque() {
        Node current = this.sentinel.next;
        while (current != this.sentinel) {
            System.out.println(current.item);
            current = current.next;
        }
    }

    // removes the first item in the list and returns it
    // No looping or recursion
    public T removeFirst() {
        T removedItem = this.sentinel.next.item;
        if (removedItem == null) {
            return null;
        }

        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        this.size--;

        return removedItem;
    }

    // removes the last item in the list and returns it
    // No looping or recursion
    public T removeLast() {
        T removedItem = this.sentinel.prev.item;
        if (removedItem == null) {
            return null;
        }

        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        this.size--;

        return removedItem;
    }

    // gets the item at the specified index
    // Use iteration
    public T get(int index) {
        // if index larger than the list return null
        if (index > this.size - 1) {
            return null;
        }

        Node getNode = this.sentinel.next;

        // loop through the list until the specified index
        for (int i = 0; i < size; i++) {
            T getItem = getNode.item;
            if (i == index) {
                return getItem;
            }
            getNode = getNode.next;
        }

        // if not found return null
        return null;
    }

    // gets the item at the specified index recursively
    public T getRecursive(int index) {
        Node head = this.sentinel;
        if (index == 0) {
            return this.sentinel.next.item;
        }

        this.sentinel = this.sentinel.next;

        index--;
        T item = getRecursive(index);

        sentinel = head;
        return item;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int position;

        LinkedListDequeIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            T returnItem = get(position);
            position++;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }

        if (!(other instanceof Deque<?>)) {
            return false;
        }

        Deque<T> o = (Deque<T>) other;
        if (o.size() != this.size()) {
            return false;
        }

        int i = 0;
        for (T item : this) {
            if (!item.equals(o.get(i))) {
                return false;
            }
            i++;
        }
        return true;
    }
}
