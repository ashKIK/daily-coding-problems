package Problems_31_40;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

// Microsoft.
//
// Compute the running median of a sequence of numbers.
// That is, given a stream of numbers, print out the median of the list so far on each new element.
//
// Recall that the median of an even-numbered list is the average of the two middle numbers.
//
// For example, given the sequence [2, 1, 5, 7, 2, 0, 5], your algorithm should print out:
//
// 2
// 1.5
// 2
// 3.5
// 2
// 2
// 2

/**
 * @author ashKIK
 */
public class Problem_33 {

  private static void runningMedian(Stream<Integer> stream) {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>();

    AtomicReference<Double> result = new AtomicReference<>((double) 0);

    stream.forEachOrdered(value -> {
      maxHeap.offer(value);
      minHeap.offer(maxHeap.poll());

      if (minHeap.size() > maxHeap.size()) {
        maxHeap.offer(minHeap.poll());
      }

      if (minHeap.size() == maxHeap.size()) {
        result.set(((double) (minHeap.peek() + maxHeap.peek())) / 2.0);
      } else {
        result.set((double) maxHeap.peek());
      }

      System.out.println(result.get());
    });
  }

  public static void main(String... args) {

    Integer[] integers = {2, 1, 5, 7, 2, 0, 5};
    runningMedian(Arrays.stream(integers));
  }
}
