// Amazon.
//
// Given an array of numbers, find the maximum sum of any contiguous subarray of the array.
//
// For example, given the array [34, -50, 42, 14, -5, 86], the maximum sum would be 137,
// since we would take elements 42, 14, -5, and 86.
//
// Given the array [-5, -1, -8, -9], the maximum sum would be 0, since we would not take any elements.
//
// Do this in O(N) time.

/**
 * @author ashKIK
 */
public class Problem_49 {

  private static int kadane(int... arr) {

    int maxSoFar = arr[0];
    int maxEndingHere = arr[0];

    for (int i = 1; i < arr.length; i++) {
      maxEndingHere = maxEndingHere + arr[i];
      maxEndingHere = Math.max(maxEndingHere, arr[i]);
      maxSoFar = Integer.max(maxSoFar, maxEndingHere);
    }

    return maxSoFar;
  }

  public static void main(String... args) {

    System.out.println(kadane(34, -50, 42, 14, -5, 86));
    System.out.println(kadane(-5, -1, -8, -9));
  }
}
