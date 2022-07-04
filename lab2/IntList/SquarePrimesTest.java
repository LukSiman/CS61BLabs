package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes1() {
        IntList lst = IntList.of(2, 4, 6, 8, 12);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 4 -> 6 -> 8 -> 12", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes2() {
        IntList lst = IntList.of(13, 17, 19, 23, 29);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("169 -> 289 -> 361 -> 529 -> 841", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes3() {
        IntList lst = IntList.of(2, 3, 6, 8, 12);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 6 -> 8 -> 12", lst.toString());
        assertTrue(changed);
    }
}
