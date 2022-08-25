package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements Comparable<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        if (this.size() == 0) {
            return null;
        }

        int maxIndex = 0;

        for(int i = 0; i < this.size(); i++){
            int compareValue = comparator.compare((T)this.get(i), (T)this.get(maxIndex));
            if(compareValue > 0){
                maxIndex = i;
            }
        }

        return (T) this.get(maxIndex);
    }


    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        }

        int maxIndex = 0;

        for(int i = 0; i < this.size(); i++){
            int compareValue = c.compare((T)this.get(i), (T)this.get(maxIndex));
            if(compareValue > 0){
                maxIndex = i;
            }
        }

        return (T) this.get(maxIndex);
    }

    @Override
    public int compareTo(T anotherObject) {
        return 0;
    }
}
