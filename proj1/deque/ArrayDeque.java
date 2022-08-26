package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INITIAL_SIZE = 8;

    public ArrayDeque() {
        this.items = (T[]) new Object[INITIAL_SIZE];
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
    }

    // Constant time
    // add item to the front of the list
    public void addFirst(T item) {
        resizeCheck();

        this.items[this.nextFirst] = item;

        // checks if nextFirst is below zero and loops around if true
        if (this.nextFirst == 0) {
            this.nextFirst = this.items.length - 1;
        } else {
            this.nextFirst--;
        }

        this.size++;
    }

    // Constant time
    // add item to the end of the list
    public void addLast(T item) {
        resizeCheck();

        this.items[this.nextLast] = item;

        // checks if nextLast is bigger than size and loops around
        if (this.nextLast == this.items.length - 1) {
            this.nextLast = 0;
        } else {
            this.nextLast++;
        }

        this.size++;
    }

    // Constant time
    // return size of the list
    public int size() {
        return this.size;
    }

    //Prints the list from first to last item
    public void printDeque() {
        int i = getFirstIndex();
        while (i != getLastIndex() + 1) {
            if (items[i] != null) {
                System.out.print(items[i] + " ");
            }

            if (i + 1 == items.length) {
                i = 0;
            } else {
                i++;
            }
        }
    }

    // removes the first item in the list and returns it
    public T removeFirst() {
        if ((this.size < this.items.length / 4) && this.items.length >= 16) {
            if (size < 4) {
                resize(4);
            } else {
                resize(this.size);
            }
        }

        int firstIndex = getFirstIndex();

        T removedItem = this.items[firstIndex];

        if (removedItem == null) {
            return null;
        }

        this.items[firstIndex] = null;
        this.size--;
        this.nextFirst = firstIndex;

        return removedItem;
    }

    // removes the last item in the list and returns it
    public T removeLast() {
        if ((this.size < this.items.length / 4) && this.items.length >= 16) {
            if (size < 4) {
                resize(4);
            } else {
                resize(this.size);
            }
        }

        int lastIndex = getLastIndex();

        T removedItem = this.items[lastIndex];

        if (removedItem == null) {
            return null;
        }

        this.items[lastIndex] = null;
        this.size--;
        this.nextLast = lastIndex;

        return removedItem;
    }

    private int getFirstIndex() {
        int firstIndex = this.nextFirst;
        if (firstIndex + 1 == this.items.length) {
            firstIndex = 0;
        } else {
            firstIndex++;
        }
        return firstIndex;
    }

    private int getLastIndex() {
        int lastIndex = this.nextLast;
        if (lastIndex == 0) {
            lastIndex = this.items.length - 1;
        } else {
            lastIndex--;
        }
        return lastIndex;
    }

    // gets the item at the specified index
    public T get(int index) {
        if (index >= this.size || index < 0) {
            return null;
        }

        int getIndex = getFirstIndex() + index;
        if (getIndex >= this.items.length) {
            getIndex = getIndex - this.items.length;
        }

        return this.items[getIndex];
    }

    // checks if array list needs resizing
    private void resizeCheck() {
//        System.out.println("Size:" + this.size + " and length: " + items.length);
        if (this.size == this.items.length) {
            resize(this.size * 2);
        }
    }

    // resizes the array
    private void resize(int capacity) {
//        System.out.println("Resizing to :" + capacity);
        T[] arrayToResize = (T[]) new Object[capacity];
        for (int i = getFirstIndex(), j = 0; j < this.size; j++) {

            arrayToResize[j] = this.items[i];

            if (i == items.length - 1) {
                i = 0;
            } else {
                i++;
            }
        }

        this.items = arrayToResize;

        this.nextFirst = this.items.length - 1;
        this.nextLast = this.size;
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int position;

        ArrayDequeIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            T returnItem = items[position];
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

        Deque<T> o = (ArrayDeque<T>) other;
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
