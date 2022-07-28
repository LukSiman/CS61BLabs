package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int maxSize;
    private int nextFirst;
    private int nextLast;
    private static int INITIAL_SIZE = 8;

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
        for (T item : this.items) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }

    // removes the first item in the list and returns it
    public T removeFirst() {
        if ((this.size < this.items.length / 4) && this.items.length >= 16) {
            resize(this.size);
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
            resize(this.size);
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

    private int getFirstIndex(){
        int firstIndex = this.nextFirst;
        if (firstIndex + 1 == this.items.length) {
            firstIndex = 0;
        } else {
            firstIndex++;
        }
        return firstIndex;
    }

    private int getLastIndex(){
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
        if(index >= this.items.length || index < 0){
            return null;
        }

        return this.items[index];
    }

    // resizes the array
    private void resize(int capacity) {
        T[] arrayToResize = (T[]) new Object[capacity];
        for (int i = getFirstIndex(), j = 0; j < this.size; j++) {

            arrayToResize[j] = this.items[i];

            if (i == this.size - 1) {
                i = 0;
            } else {
                i++;
            }
        }

        this.items = arrayToResize;

        this.nextFirst = this.items.length - 1;
        this.nextLast = this.size;
    }
}
