package deque;

import edu.princeton.cs.algs4.StdRandom;
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

        System.out.println("Printing out deque (FLL): ");
        lld1.printDeque();
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

        lld1.addFirst("back");
        assertEquals(2, lld1.size());

        lld1.addFirst("middle");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque (FFF): ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayListDeque<Integer> lld1 = new ArrayListDeque<Integer>();
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

        ArrayListDeque<Integer> lld1 = new ArrayListDeque<>();
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

        ArrayListDeque<String>  lld1 = new ArrayListDeque<String>();
        ArrayListDeque<Double>  lld2 = new ArrayListDeque<Double>();
        ArrayListDeque<Boolean> lld3 = new ArrayListDeque<Boolean>();

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

        ArrayListDeque<Integer> lld1 = new ArrayListDeque<Integer>();

        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* check if resizing works */
    public void resizeTestFirsts() {

        ArrayListDeque<Integer> lld1 = new ArrayListDeque<Integer>();

       for(int i = 0; i < 14; i++){
           lld1.addFirst(i);
       }

       assertEquals(14, lld1.size());
    }

    @Test
    /* check if resizing works */
    public void resizeTestLasts() {

        ArrayListDeque<Integer> lld1 = new ArrayListDeque<Integer>();

        for(int i = 0; i < 30; i++){
            lld1.addLast(i);
        }

        assertEquals(30, lld1.size());
    }

    @Test
    /* check if resizing works */
    public void resizeTestFirstsLasts() {

        ArrayListDeque<Integer> lld1 = new ArrayListDeque<Integer>();

        for(int i = 0; i < 15; i++){
            lld1.addFirst(i);
        }

        for(int i = 15; i < 46; i++){
            lld1.addLast(i);
        }

        assertEquals(46, lld1.size());
    }

    @Test
    /* check if resizing works */
    public void resizeTestAddAndRemove() {

        ArrayListDeque<Integer> lld1 = new ArrayListDeque<Integer>();

        for(int i = 0; i < 16; i++){
            lld1.addFirst(i);
        }

        assertEquals(16, lld1.size());

        for(int i = 0; i < 14; i++){
            lld1.removeFirst();
        }

        assertEquals(2, lld1.size());

        ArrayListDeque<Integer> lld2 = new ArrayListDeque<Integer>();

        for(int i = 0; i < 16; i++){
            lld2.addLast(i);
        }

        assertEquals(16, lld2.size());

        for(int i = 0; i < 14; i++){
            lld2.removeLast();
        }

        assertEquals(2, lld2.size());


        // mix first with last
        lld1 = new ArrayListDeque<Integer>();

        for(int i = 0; i < 16; i++){
            lld1.addFirst(i);
        }

        assertEquals(16, lld1.size());

        for(int i = 0; i < 14; i++){
            lld1.removeLast();
        }

        assertEquals(2, lld1.size());

        lld2 = new ArrayListDeque<Integer>();

        for(int i = 0; i < 16; i++){
            lld2.addLast(i);
        }

        assertEquals(16, lld2.size());

        for(int i = 0; i < 14; i++){
            lld2.removeFirst();
        }

        assertEquals(2, lld2.size());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayListDeque<Integer> lld1 = new ArrayListDeque<Integer>();
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
        ArrayListDeque<Integer> noResize = new ArrayListDeque<>();
        ArrayListDeque<Integer> buggyList = new ArrayListDeque<>();

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
    public void randomizedTest() {
        ArrayListDeque<Integer> L = new ArrayListDeque<>();
        ArrayListDeque<Integer> B = new ArrayListDeque<>();

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
}
