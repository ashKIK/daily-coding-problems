package Problems_41_50;

import java.util.ArrayDeque;
import java.util.Deque;

// Google.
//
// Given a list of integers S and a target number k,
// write a function that returns a subset of S that adds up to k.
// If such a subset cannot be made, then return null.
//
// Integers can appear more than once in the list.
// You may assume all numbers in the list are positive.
//
// For example, given S = [12, 1, 61, 5, 9, 2] and k = 24,
// return [12, 9, 2, 1] since it sums up to 24.

/**
 * @author ashKIK
 */
public class Problem_42 {

  private static void getSubset(int[] S, int k) {
    Deque<Integer> stack = new ArrayDeque<>();
    findSubset(S, 0, k, stack);
    System.out.println(stack);
  }

  private static boolean findSubset(int[] arr, int i, int k, Deque<Integer> stack) {
    if (k == 0) {
      return true;
    }
    if (k < 0 || i >= arr.length) {
      return false;
    }

    for (; i < arr.length; i++) {
      stack.push(arr[i]);
      if (findSubset(arr, i + 1, k - arr[i], stack)) {
        return true;
      }
      stack.pop();
    }

    return false;
  }

  public static void main(String... args) {
    getSubset(new int[]{12, 1, 61, 5, 9, 2}, 24);
  }
}
