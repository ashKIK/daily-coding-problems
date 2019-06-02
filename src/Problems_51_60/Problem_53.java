package Problems_51_60;

import java.util.Deque;
import java.util.LinkedList;

// Apple.
//
// Implement a queue using two stacks.
// Recall that a queue is a FIFO (first-in, first-out) data structure with the following methods:
// enqueue, which inserts an element into the queue,
// and dequeue, which removes it.

/**
 * @author ashKIK
 */
public class Problem_53 {

  private static class Queue<V> {

    private Deque<V> stackEnqueue = new LinkedList<>();
    private Deque<V> stackDequeue = new LinkedList<>();

    void enqueue(V value) {
      stackEnqueue.push(value);
    }

    V dequeue() {
      if (stackDequeue.isEmpty()) {
        while (!stackEnqueue.isEmpty()) {
          stackDequeue.push(stackEnqueue.remove());
        }
      }

      return stackDequeue.isEmpty() ? null : stackDequeue.remove();
    }
  }

  public static void main(String... args) {
    Queue<Integer> queue = new Queue<>();

    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    System.out.println(queue.dequeue());    // 1
    System.out.println(queue.dequeue());    // 2

    queue.enqueue(5);
    queue.enqueue(6);
    queue.enqueue(7);
    queue.enqueue(8);

    System.out.println(queue.dequeue());    // 3
    System.out.println(queue.dequeue());    // 4
    System.out.println(queue.dequeue());    // 5
    System.out.println(queue.dequeue());    // 6
    System.out.println(queue.dequeue());    // 7
    System.out.println(queue.dequeue());    // 8
  }
}
