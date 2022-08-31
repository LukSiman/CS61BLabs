package tester;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeEC {

    @Test
    public void randomizedTest() {
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> arrayDeque = new ArrayDequeSolution<>();

        int N = 10000;
        String failurePath = "";
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                Integer randVal = StdRandom.uniform(0, 100);

                failurePath = failurePath + "\naddLast(" + randVal + ")";

                studentDeque.addLast(randVal);
                arrayDeque.addLast(randVal);

                int studentSize = studentDeque.size();
                int arraySize = arrayDeque.size();

                assertEquals(failurePath, studentSize, arraySize);
                assertEquals(failurePath, randVal, studentDeque.get(studentSize - 1));
                assertEquals(failurePath, randVal, arrayDeque.get(arraySize - 1));
                assertEquals(failurePath, randVal, arrayDeque.getRecursive(arraySize - 1));
                assertEquals(failurePath, studentDeque.get(studentSize - 1), arrayDeque.get(arraySize - 1));
                assertEquals(failurePath, studentDeque.get(studentSize - 1), arrayDeque.getRecursive(arraySize - 1));
            } else if (operationNumber == 1) {
                // addFirst
                Integer randVal = StdRandom.uniform(0, 100);

                failurePath = failurePath + "\naddFirst(" + randVal + ")";

                studentDeque.addFirst(randVal);
                arrayDeque.addFirst(randVal);

                assertEquals(failurePath, randVal, studentDeque.get(0));
                assertEquals(failurePath, randVal, arrayDeque.get(0));
                assertEquals(failurePath, randVal, arrayDeque.getRecursive(0));
                assertEquals(failurePath, studentDeque.get(0), arrayDeque.get(0));
                assertEquals(failurePath, studentDeque.get(0), arrayDeque.getRecursive(0));
            } else if (operationNumber == 2) {
                // removeLast
                if (arrayDeque.size() > 0) {
                    failurePath = failurePath + "\nremoveLast()";

                    Integer studentRemove = studentDeque.removeLast();
                    Integer arrayRemove = arrayDeque.removeLast();

                    assertEquals(failurePath, studentRemove, arrayRemove);
                }
            } else if (operationNumber == 3) {
                // removeFirst
                if (arrayDeque.size() > 0) {
                    failurePath = failurePath + "\nremoveFirst()";

                    Integer studentRemove = studentDeque.removeFirst();
                    Integer arrayRemove = arrayDeque.removeFirst();

                    assertEquals(failurePath, studentRemove, arrayRemove);
                }
            }
        }
    }
}
