package Problems_71_80;

import java.util.Arrays;

// Microsoft.
//
// Given an array of numbers, find the length of the longest increasing subsequence in the array.
// The subsequence does not necessarily have to be contiguous.
//
// For example, given the array [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15],
// the longest increasing subsequence has length 6: it is 0, 2, 6, 9, 11, 15.

/**
 * @author ashKIK
 */
public class Problem_75 {

  // O(n*lg(n))
  private static int lisBS(int... nums) {
    int[] dp = new int[nums.length];
    int length = 0;

    for (int num : nums) {
      int idx = Arrays.binarySearch(dp, 0, length, num);
      if (idx < 0) {
        idx = -(idx + 1);
      }

      dp[idx] = num;
      if (idx == length) {
        length++;
      }
    }

    return length;
  }

  // O(n^2)
  private static int lisDP(int... nums) {
    int size = nums.length;

    if (size == 0) {
      return 0;
    }

    int[] dp = new int[size];
    dp[0] = 1;

    int maxans = 1;
    for (int i = 1; i < size; i++) {
      int maxval = 0;
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          maxval = Math.max(maxval, dp[j]);
        }
      }
      dp[i] = maxval + 1;
      maxans = Math.max(maxans, dp[i]);
    }

    return maxans;
  }

  public static void main(String... args) {
    int[] nums = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};

    System.out.println(lisBS(nums));
    System.out.println(lisDP(nums));
  }
}
