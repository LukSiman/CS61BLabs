package deque;

public class LinkedListDeque<T> implements Deque<T>{
    private class Node{
        public T item;
        public Node prev;
        public Node next;

        public Node(T item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque(){
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    // No looping or recursion
    // Add item as the first node
    public void addFirst(T item){
        Node firstItem = new Node(item, this.sentinel, this.sentinel.next);
        this.sentinel.next.prev = firstItem;
        this.sentinel.next = firstItem;
        this.size++;
    }

    // No looping or recursion
    // Add item as the last node
    public void addLast(T item){
        Node lastItem = new Node(item, this.sentinel.prev, this.sentinel);
        this.sentinel.prev.next = lastItem;
        this.sentinel.prev = lastItem;
        this.size++;
    }

    // Return boolean whether list is empty
//    public boolean isEmpty(){
//        if(this.size == 0){
//            return true;
//        }
//        return false;
//    }

    // Constant time
    // return size of the list
    public int size(){
        return this.size;
    }

    //Prints the list from first to last item
    public void printDeque(){
        Node current = this.sentinel.next;
        while(current != this.sentinel){
            System.out.println(current.item);
            current = current.next;
        }
    }

    // removes the first item in the list and returns it
    // No looping or recursion
    public T removeFirst(){
        T removedItem = this.sentinel.next.item;
        if(removedItem == null){
            return null;
        }

        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        this.size--;

        return removedItem;
    }

    // removes the last item in the list and returns it
    // No looping or recursion
    public T removeLast(){
        T removedItem = this.sentinel.prev.item;
        if(removedItem == null){
            return null;
        }

        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        this.size--;

        return removedItem;
    }

    // gets the item at the specified index
    // Use iteration
    public T get(int index){
        // if index larger than the list return null
        if(index > this.size - 1){
            return null;
        }

        Node getNode = this.sentinel.next;
        T getItem = getNode.item;

        // loop through the list until the specified index
        for(int i = 0; i <= index; i++){
            if(i == index){
                return getItem;
            }
            getNode = getNode.next;
        }

        // if not found return null
        return null;
    }

    public T getRecursive(int index){
        return null;
    }

//    public Iterator<T> iterator(){}

//    public boolean equals(Object o){}
}
