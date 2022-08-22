package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class MaxArrayDequeTest {

    @Test
    public void checkMaxValue(){
        Comparator<Integer> comp = new ValueComparison();
        MaxArrayDeque<Integer> max = new MaxArrayDeque<>(comp);

        max.addFirst(1);
        max.addFirst(2);
        max.addFirst(3);

        assertEquals(3, max.max());
    }

    @Test
    public void checkMaxValueLowest(){
        Comparator<Integer> comp = new LowestValueComparison();
        MaxArrayDeque<Integer> max = new MaxArrayDeque<>(comp);

        max.addFirst(1);
        max.addFirst(2);
        max.addFirst(3);

        assertEquals(1, max.max());
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

        assertEquals(3, max.max());
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

        assertEquals(999, max.max());

        Comparator<Integer> low = new LowestValueComparison();

        assertEquals(3, max.max(low));
    }

}