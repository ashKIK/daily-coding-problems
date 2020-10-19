// Facebook.
//
// A builder is looking to build a row of N houses that can be of K different colors.
// He has a goal of minimizing cost while ensuring that no two neighboring houses are of the same color.
//
// Given an N by K matrix where the nth row and kth column
// represents the cost to build the nth house with kth color,
// return the minimum cost which achieves this goal.

/**
 * @author ashKIK
 */
public class Problem_19 {

  private static int minimumCost(int[][] costs) {
    if (costs == null || costs.length == 0) {
      return 0;
    }

    int prevMin = 0, prevSecondMin = 0, prevColor = -1;

    for (int i = 0; i < costs.length; i++) {
      int curMin = Integer.MAX_VALUE;
      int curSecondMin = Integer.MAX_VALUE;
      int curColor = -1;
      for (int j = 0; j < costs[0].length; j++) {
        costs[i][j] = costs[i][j] + (prevColor == j ? prevSecondMin : prevMin);
        if (costs[i][j] < curMin) {
          curSecondMin = curMin;
          curMin = costs[i][j];
          curColor = j;
        } else if (costs[i][j] < curSecondMin) {
          curSecondMin = costs[i][j];
        }
      }
      prevMin = curMin;
      prevSecondMin = curSecondMin;
      prevColor = curColor;
    }

    return prevMin;
  }

  // Optimization.
  // The rule is adjacent 2 houses cannot be in same color,
  // which means, they can be chosen from the lowest two costs.
  // If we know the lowest 2 ahead of the 3-level-for loop, we can reduce the most-inner loop.
  private static int minimumCostFast(int[][] costs) {
    if (costs == null || costs.length == 0) {
      return 0;
    } else if (costs.length == 1 && costs[0].length == 1) {
      return costs[0][0];
    }
    int minCost = Integer.MAX_VALUE;
    int n = costs.length;
    int k = costs[0].length;
    int[][] dp = new int[n + 1][k];
    // Paint 0 houese should be initialized with 0 cost
    for (int j = 0; j < k; j++) {
      dp[0][j] = 0;
    }

    // iterate over house #
    for (int i = 1; i <= n; i++) {
      // Find minSecond and min indexes
      // min value: dp[i - 1][minIndex]
      // 2nd min value: dp[i - 1][minSecIndex]
      int minIndex = -1;
      int minSecIndex = -1;
      for (int j = 0; j < k; j++) {
        if (minIndex == -1 || dp[i - 1][j] < dp[i - 1][minIndex]) {
          minSecIndex = minIndex;
          minIndex = j;
        } else if (minSecIndex == -1 || dp[i - 1][j] < dp[i - 1][minSecIndex]) {
          minSecIndex = j;
        }
      }

      // DP Processing
      for (int j = 0; j < k; j++) { // choose color for house i - 1
        // if color at minIndex is chosen for dp[i], then the remaining min is at minSecIndex
        if (j == minIndex) {
          dp[i][j] = dp[i - 1][minSecIndex] + costs[i - 1][j];
        } else { // if color is not chosen at minIndex, minIndex will represent the overall min
          dp[i][j] = dp[i - 1][minIndex] + costs[i - 1][j];
        }
        if (i == n) {
          minCost = Math.min(minCost, dp[i][j]);
        }
      }
    }

    return minCost;
  }

  // Rolling array, O(k) space
  private static int minimumCostFastRolling(int[][] costs) {

    if (costs == null || costs.length == 0) {
      return 0;
    } else if (costs.length == 1 && costs[0].length == 1) {
      return costs[0][0];
    }

    int minCost = Integer.MAX_VALUE;
    int n = costs.length, k = costs[0].length;
    int[][] dp = new int[2][k]; // dp[0][j] = 0; for j=[0 ~ k)

    for (int i = 1; i <= n; i++) {
      int minIndex = -1;
      int minSecIndex = -1;
      for (int j = 0; j < k; j++) {
        if (minIndex == -1 || dp[(i - 1) % 2][j] < dp[(i - 1) % 2][minIndex]) {
          minSecIndex = minIndex;
          minIndex = j;
        } else if (minSecIndex == -1 || dp[(i - 1) % 2][j] < dp[(i - 1) % 2][minSecIndex]) {
          minSecIndex = j;
        }
      }

      // DP Processing
      for (int j = 0; j < k; j++) {
        if (j == minIndex) {
          dp[i % 2][j] = dp[(i - 1) % 2][minSecIndex] + costs[i - 1][j];
        } else {
          dp[i % 2][j] = dp[(i - 1) % 2][minIndex] + costs[i - 1][j];
        }
        if (i == n) {
          minCost = Math.min(minCost, dp[i % 2][j]);
        }
      }
    }

    return minCost;
  }
}
