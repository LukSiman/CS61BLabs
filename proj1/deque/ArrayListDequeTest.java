package deque;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ArrayListDequeTest {

    @Test
    public void addIsEmptySizeTest() {
        ArrayListDeque<String> lld1 = new ArrayListDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

//        System.out.println("Printing out deque: ");
//        lld1.printDeque();
    }

    @Test
    public void addFirstOnly() {
        ArrayListDeque<String> lld1 = new ArrayListDeque<>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addFirst("middle");
        assertEquals(2, lld1.size());

        lld1.addFirst("back");
        assertEquals(3, lld1.size());

//        System.out.println("Printing out deque: ");
//        lld1.printDeque();
    }
}
