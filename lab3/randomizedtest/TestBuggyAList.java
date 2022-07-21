package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> noResize = new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();

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
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

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
                if(L.size() > 0){
                    assertTrue(L.getLast() == B.getLast());
                }

                if(B.size() > 0){
                    assertTrue(L.getLast() == B.getLast());
                }

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
