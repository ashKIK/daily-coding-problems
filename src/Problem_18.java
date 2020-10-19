import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Google.
//
// Given an array of integers and a number k, where 1 <= k <= length of the array,
// compute the maximum values of each subarray of length k.
//
// For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8], since:
//
// 10 = max(10, 5, 2)
// 7 = max(5, 2, 7)
// 8 = max(2, 7, 8)
// 8 = max(7, 8, 7)
//
// Do this in O(n) time and O(k) space.
// You can modify the input array in-place and you do not need to store the results.
// You can simply print them out as you compute them.

/**
 * @author ashKIK
 */
public class Problem_18 {

  private static int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return new int[0];
    }

    int[] result = new int[nums.length - k + 1];

    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 0; i < nums.length; i++) {

      // remove the element which is out for next window (next k elements)
      if (!deque.isEmpty() && deque.peekFirst() == i - k) {
        deque.removeFirst();
      }

      // remove all the elements which are smaller than the next element = index i
      while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
        deque.removeLast();
      }

      //add new element at the end
      deque.addLast(i);

      if (i + 1 >= k) {
        result[i + 1 - k] = nums[deque.peekFirst()];
      }
    }

    return result;
  }

  public static void main(String... args) {
    int[] nums = {1, 2, 3, 2, 4, 1, 5, 6, 1, 10};
    int k = 3;
    Arrays.stream(maxSlidingWindow(nums, k))
        .mapToObj(max -> max + " ")
        .forEachOrdered(System.out::print);
  }
}
