package Problems_51_60;

import java.util.Arrays;

// Facebook.
//
// Given a multiset of integers,
// return whether it can be partitioned into two subsets whose sums are the same.
//
// For example, given the multiset {15, 5, 20, 10, 35, 15, 10}, it would return true,
// since we can split it up into {15, 5, 10, 15, 10} and {20, 35}, which both add up to 55.
//
// Given the multiset {15, 5, 20, 10, 35}, it would return false,
// since we can't split it up into two subsets that add up to the same sum.

/**
 * @author ashKIK
 */
public class Problem_60 {

  private static boolean canPartition1D(int[] nums) {
    if (nums.length < 2) {
      return false;
    }

    int sum = Arrays.stream(nums).sum();

    if (sum % 2 == 1) {
      return false;
    }

    int target = sum / 2;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;
    for (int num : nums) {
      for (int s = target; s >= 0; s--) {
        if (s - num >= 0) {
          dp[s] = dp[s] || dp[s - num];
        }
      }
    }

    return dp[target];
  }

  private static boolean canPartition2D(int[] nums) {
    int size = nums.length;

    if (size < 2) {
      return false;
    }

    int sum = Arrays.stream(nums).sum();

    if (sum % 2 == 1) {
      return false;
    }

    int target = sum / 2;
    boolean[][] dp = new boolean[size + 1][target + 1];
    dp[0][0] = true;
    for (int i = 1; i <= size; i++) {
      for (int j = 1; j <= target; j++) {
        dp[i][j] |= dp[i - 1][j];
        if (j >= nums[i - 1]) {
          dp[i][j] |= dp[i - 1][j - nums[i - 1]];
        }
      }
      if (dp[i][target]) {
        return true;
      }
    }

    return false;
  }

  private static boolean canPartitionSubsetSum(int[] nums) {
    int size = nums.length;

    if (size < 2) {
      return false;
    }

    int sum = Arrays.stream(nums).sum();

    if (sum % 2 == 1) {
      return false;
    }

    boolean[][] subset = new boolean[size + 1][sum + 1];

    for (int i = 0; i <= size; i++) {
      subset[i][0] = true;
    }

    for (int j = 1; j <= sum; j++) {
      subset[0][j] = false;
    }

    for (int i = 1; i <= size; i++) {
      for (int j = 1; j <= sum; j++) {
        if (nums[i - 1] > j) {
          subset[i][j] = subset[i - 1][j];
        } else {
          subset[i][j] = subset[i - 1][j] || subset[i - 1][j - nums[i - 1]];
        }
      }
    }

    return subset[size][sum];
  }

  private static boolean canPartitionDFS(int[] nums) {
    if (nums.length < 2) {
      return false;
    }

    int sum = Arrays.stream(nums).sum();

    if (sum % 2 == 1) {
      return false;
    }

    int target = sum / 2;
    Arrays.sort(nums);
    return dfs(nums, 0, target);
  }

  private static boolean dfs(int[] nums, int pos, int target) {
    if (target == 0) {
      return true;
    }
    int size = nums.length;
    for (int i = pos; i < size; i++) {
      if (i > pos && nums[i] == nums[i - 1]) {
        continue;
      }
      if (nums[i] > target) {
        break;
      }
      if (dfs(nums, i + 1, target - nums[i])) {
        return true;
      }
    }
    return false;
  }

  public static void main(String... args) {

    int[] nums1 = {15, 5, 20, 10, 35, 15, 10};
    int[] nums2 = {15, 5, 20, 10, 35};

    System.out.println("1D DP     : " + canPartition1D(nums1) + " " + canPartition1D(nums2));
    System.out.println("2D DP     : " + canPartition2D(nums1) + " " + canPartition2D(nums2));
    System.out.println(
        "SubsetSum : " + canPartitionSubsetSum(nums1) + " " + canPartitionSubsetSum(nums2));
    System.out.println("DFS       : " + canPartitionDFS(nums1) + " " + canPartitionDFS(nums2));
  }
}
