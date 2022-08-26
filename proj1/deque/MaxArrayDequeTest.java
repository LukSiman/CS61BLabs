package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class MaxArrayDequeTest {

    @Test
    public void checkMaxValue(){
        Comparator<Integer> comp = new ValueComparison();
        MaxArrayDeque<Integer> max = new MaxArrayDeque<>(comp);

        max.addFirst(1);
        max.addFirst(2);
        max.addFirst(3);

        assertEquals((Integer)3, max.max());
    }

    @Test
    public void checkMaxValueLowest(){
        Comparator<Integer> comp = new LowestValueComparison();
        MaxArrayDeque<Integer> max = new MaxArrayDeque<>(comp);

        max.addFirst(1);
        max.addFirst(2);
        max.addFirst(3);

        assertEquals((Integer)1, max.max());
    }

    @Test
    public void checkNullMax(){
        Comparator<Integer> comp = new ValueComparison();
        MaxArrayDeque<Integer> max = new MaxArrayDeque<>(comp);

        assertNull(max.max());
    }

    @Test
    public void checkMaxValueSame(){
        Comparator<Integer> comp = new ValueComparison();
        MaxArrayDeque<Integer> max = new MaxArrayDeque<>(comp);

        max.addFirst(1);
        max.addFirst(2);
        max.addFirst(3);

        assertEquals((Integer)3, max.max());
    }

    @Test
    public void checkMaxLetter(){
        Comparator<String> comp = new StringLetterComparison();
        MaxArrayDeque<String> max = new MaxArrayDeque<>(comp);

        max.addFirst("first");
        max.addFirst("middle");
        max.addFirst("back");
        max.addLast("Zero");

        assertEquals("Zero", max.max());
    }

    @Test
    public void checkMaxString(){
        Comparator<String> comp = new StringComparison();
        MaxArrayDeque<String> max = new MaxArrayDeque<>(comp);

        max.addFirst("first");
        max.addFirst("middle");
        max.addFirst("back");
        max.addLast("Zero");

        assertEquals("Zero", max.max());
    }

    @Test
    public void checkMaxStringZ(){
        Comparator<String> comp = new StringComparison();
        MaxArrayDeque<String> max = new MaxArrayDeque<>(comp);

        max.addFirst("first");
        max.addFirst("middle");
        max.addFirst("back");
        max.addLast("Zero");
        max.addLast("ZZZZZ");

        assertEquals("ZZZZZ", max.max());
    }

    @Test
    public void checkComparatorChange(){
        Comparator<Integer> comp = new ValueComparison();
        MaxArrayDeque<Integer> max = new MaxArrayDeque<>(comp);

        max.addFirst(10);
        max.addFirst(88);
        max.addFirst(3);
        max.addLast(14);
        max.addLast(999);

        assertEquals((Integer)999, max.max());

        Comparator<Integer> low = new LowestValueComparison();

        assertEquals((Integer)3, max.max(low));
    }

    @Test
    public void randomizedTest() {
        Comparator<Integer> low = new LowestValueComparison();
        MaxArrayDeque<Integer> L = new MaxArrayDeque<>(low);

        List<Integer> list = new ArrayList<>();
        int N = 100000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 5);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                list.add(randVal);
                L.addLast(randVal);
            } else if (operationNumber == 1) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                list.add(0, randVal);
                L.addFirst(randVal);
            } else if (operationNumber == 2) {
                // getLast
                int size = L.size();

            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() > 0) {
                    int lastList = list.remove(list.size() - 1);
                    int removeVal = L.removeLast();
                    assertEquals(lastList, removeVal);
                }
            } else if (operationNumber == 4) {
                // removeFirst
                if (L.size() > 0) {
                    ArrayDeque<Integer> test = L;
                    int firstList = list.remove(0);
                    int removeVal = L.removeFirst();
                    assertEquals(firstList, removeVal);
                }
            }
        }
    }
}