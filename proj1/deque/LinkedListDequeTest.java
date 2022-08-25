package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

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

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void testThreeAddThreeRemove() {
        LinkedListDeque<Integer> noResize = new LinkedListDeque<>();
        LinkedListDeque<Integer> buggyList = new LinkedListDeque<>();

        noResize.addLast(4);
        buggyList.addLast(4);
        noResize.addLast(5);
        buggyList.addLast(5);
        noResize.addLast(6);
        buggyList.addLast(6);

        assertTrue(noResize.size() == buggyList.size());
        assertTrue(noResize.removeLast() == buggyList.removeLast());
        assertTrue(noResize.removeLast() == buggyList.removeLast());
        assertTrue(noResize.removeLast() == buggyList.removeLast());
    }

    @Test
    public void testGetIterative() {
        LinkedListDeque<Integer> noResize = new LinkedListDeque<>();

        noResize.addLast(4);;
        noResize.addLast(5);
        noResize.addLast(6);

        assertTrue((noResize.get(2) == 6));
        assertTrue((noResize.get(1) == 5));
        assertTrue((noResize.get(0) == 4));
    }

    @Test
    public void testGetRecursive() {
        LinkedListDeque<Integer> noResize = new LinkedListDeque<>();

        noResize.addLast(4);
        noResize.addLast(5);
        noResize.addLast(6);

        assertTrue((noResize.getRecursive(2) == 6));
        assertTrue((noResize.getRecursive(1) == 5));
        assertTrue((noResize.getRecursive(0) == 4));
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        LinkedListDeque<Integer> B = new LinkedListDeque<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeB = B.size();
                assertTrue(size == sizeB);
            } else if(operationNumber == 2){
                // getLast
                assertTrue(L.get(1) == B.get(1));
                assertTrue(L.get(2) == B.get(2));
                assertTrue(L.get(0) == B.get(0));
                assertTrue(L.get(5) == B.get(5));
                assertTrue(L.get(900) == B.get(900));

                int size = L.size();
                int sizeB = B.size();
                assertTrue(size == sizeB);

            } else if(operationNumber == 3){
                // removeLast
                if(L.size() > 0){
                    int removeVal = L.removeLast();
                    int BremoveVal = B.removeLast();
                    assertTrue(removeVal == BremoveVal);
                }
            }
        }
    }

    @Test
    public void testEquality(){
        LinkedListDeque<Integer> dequeA = new LinkedListDeque<>();
        assertEquals(dequeA,dequeA);

        LinkedListDeque<Integer> dequeB = new LinkedListDeque<>();
        assertEquals(dequeA,dequeB);

        dequeA.addLast(5);
        dequeA.addFirst(99);

        dequeB.addLast(5);
        dequeB.addFirst(99);

        assertEquals(dequeA,dequeB);
    }
}
