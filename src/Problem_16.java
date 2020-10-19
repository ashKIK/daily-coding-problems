import java.util.Arrays;

// Twitter.
//
// You run an e-commerce website and want to record the last N order ids in a log.
// Implement a data structure to accomplish this, with the following API:
//
// record(order_id): adds the order_id to the log
// get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.
//
// You should be as efficient with time and space as possible.

/**
 * @author ashKIK
 */
public class Problem_16 {

  // Use circular buffer
  private static class Logger {

    private int currIdx;
    private int maxSize;
    private int[] circularBuffer;

    Logger(int n) {
      this.currIdx = 0;
      this.maxSize = n;
      this.circularBuffer = new int[n];
    }

    // O(1)
    private void record(int orderId) {
      circularBuffer[currIdx] = orderId;
      currIdx = (currIdx + 1) % maxSize;
    }

    // O(1)
    private int getLast(int i) {
      return circularBuffer[(currIdx - i + maxSize) % maxSize];
    }
  }

  public static void main(String... args) {

    Integer[] ids = {10, 7, 2, 14, 55, 233, 664};

    Logger logger = new Logger(ids.length);
    Arrays.stream(ids).forEachOrdered(logger::record);

    System.out.println(logger.getLast(5));
    System.out.println(logger.getLast(4));
    System.out.println(logger.getLast(3));
  }
}
