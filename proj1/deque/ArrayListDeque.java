package deque;

public class ArrayListDeque<T> {
    private T[] items;
    private int size;
    private int maxSize;
    private int nextFirst;
    private int nextLast;
    private static int INITIAL_SIZE = 8;

    public ArrayListDeque() {
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

    // Checks if the given index is null
    private boolean nullCheck(int index) {
        if (this.items[index] == null) {
            return true;
        }

        return false;
    }

    // checks if array list needs resizing
    private void resizeCheck() {
        if (this.size == this.items.length) {
            resize(this.size * 2);
        }
    }

    // Return boolean whether list is empty
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    // Constant time
    // return size of the list
    public int size() {
        return this.size;
    }

    //Prints the list from first to last item
    public void printDeque() {
    }

    // removes the first item in the list and returns it
    // No looping or recursion
    public T removeFirst() {


        return null;
    }

    // removes the last item in the list and returns it
    // No looping or recursion
    public T removeLast() {
        if ((this.size < this.items.length / 4) && (this.size > 4)) {
            resize(this.size);
        }

        T x = get(2); // wrong
        this.items[this.size - 1] = null;
        this.size = this.size - 1;
        return x;
    }


    // gets the item at the specified index
    public T get(int i) {
        return this.items[i];
    }

    // resizes the array
    private void resize(int capacity) {
        T[] arrayToResize = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            arrayToResize[i] = this.items[i];
        }
        this.items = arrayToResize;
    }
}
