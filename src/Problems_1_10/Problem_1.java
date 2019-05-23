package Problems_1_10;

import java.util.HashSet;
import java.util.Set;

// Google.

// Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
//
// For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.

/**
 * @author ashKIK
 */
public class Problem_1 {

  private static boolean check(int[] arr, int k) {
    if (arr == null || arr.length < 2) {
      return false;
    }

    Set<Integer> set = new HashSet<>();

    for (int val : arr) {
      if (set.contains(val)) {
        return true;
      }
      set.add(k - val);
    }

    return false;
  }

  public static void main(String... args) {
    int[] arr = {44, 13, 39, 71};
    System.out.println(check(arr, 10));
  }
}
