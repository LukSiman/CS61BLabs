package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        if (this.size() == 0) {
            return null;
        }

        int maxIndex = 0;

        for
            (int i = 0; i < this.size(); i++) {
            int compareValue = comparator.compare(this.get(i), this.get(maxIndex));
            if (compareValue > 0) {
                maxIndex = i;
            }
        }

        return this.get(maxIndex);
    }


    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }

        int maxIndex = 0;

        for (int i = 0; i < this.size(); i++) {
            int compareValue = c.compare(this.get(i), this.get(maxIndex));
            if (compareValue > 0) {
                maxIndex = i;
            }
        }

        return this.get(maxIndex);
    }
}
