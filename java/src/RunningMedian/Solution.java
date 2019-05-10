package RunningMedian;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        RunningMedian rm = new RunningMedian();

        for (int i = 0; i < n; i++) {
            int aItem = scanner.nextInt();
            System.out.println(rm.add(aItem));
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        }

        scanner.close();
    }
}

/**
 * Adds values one at a time and returns the current median after each insertion
 */
class RunningMedian {

    /**
     * MinHeap for the upper half of the values added so far
     */
    private PriorityQueue<Integer> upperHalf;

    /**
     * MaxHeap for the lower half of the values added so far
     */
    private PriorityQueue<Integer> lowerHalf;

    RunningMedian() {
        upperHalf = new PriorityQueue<>(16, Integer::compare);
        lowerHalf = new PriorityQueue<>(16, (a,b) -> -1 * Integer.compare(a, b));
    }

    /**
     * @param value new value to add
     * @return new median
     */
    float add(int value) {
        // nothing added yet so just throw it in the upper half
        if ( upperHalf.peek() == null ) {
            upperHalf.add(value);
            return getMedian();
        }

        // put new value in upper or lower half
        if (upperHalf.peek() > value) {
            lowerHalf.add(value);
        } else {
            upperHalf.add(value);
        }

        return getMedian();
    }

    /**
     * @return median of combined halves
     */
    private float getMedian() {
        balance();
        // halves are same size
        if (upperHalf.size() == lowerHalf.size()) {
            return (float)(upperHalf.peek() + lowerHalf.peek())/2;
        }

        // upper half has the median
        if (upperHalf.size() > lowerHalf.size()){
            return (float) upperHalf.peek();
        }

        // lower half has the median
        return (float) lowerHalf.peek();
    }

    /**
     * Moves one element at a time between halves until they differ in size by 1 or 0
     */
    private void balance() {
        while(Math.abs(upperHalf.size() - lowerHalf.size()) > 1) {
            if(upperHalf.size() > lowerHalf.size()){
                lowerHalf.add(upperHalf.poll());
            } else {
                upperHalf.add(lowerHalf.poll());
            }
        }
    }
}
