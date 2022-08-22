package gh2;


import deque.ArrayDeque;
import deque.Deque;

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /**
     * Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday.
     */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayDeque<>();
        int size = (int) Math.round(SR / frequency);
        while (size > 0) {
            buffer.addFirst(0.00);
            size--;
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
        for (int i = 0; i < buffer.size(); i++) {
            buffer.removeFirst();
            double r = Math.random() - 0.5;
            r = Math.round(r*10000.0)/10000.000;
            buffer.addLast(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        int size = buffer.size();
//        for (int i = 1; i < size - 1; i++) {
            double first = buffer.removeFirst();
            double second = buffer.removeFirst();
            buffer.addFirst(second);
            double newDouble = (first + second) / 2 * DECAY;
            buffer.addLast(newDouble);
//        }
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        double string = buffer.removeFirst();
        buffer.addFirst(string);
        return string;
    }
}
