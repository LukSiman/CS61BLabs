package deque;

import java.util.Comparator;

public class StringLetterComparison implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        o1 = o1.toLowerCase();
        o2 = o2.toLowerCase();

        if (o1.charAt(0) > o2.charAt(0)) {
            return 1;
        } else if (o1.charAt(0) < o2.charAt(0)) {
            return -1;
        }
        return 0;
    }
}
