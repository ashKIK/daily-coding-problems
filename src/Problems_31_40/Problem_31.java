package Problems_31_40;

// Google.
//
// The edit distance between two strings refers to the minimum number of character
// insertions, deletions, and substitutions required to change one string to the other.
//
// For example, the edit distance between “kitten” and “sitting” is three:
// substitute the “k” for “s”, substitute the “e” for “i”, and append a “g”.
//
// Given two strings, compute the edit distance between them.

/**
 * @author ashKIK
 */
public class Problem_31 {

  private static int minDistanceBU(String one, String two) {
    int m = one.length();
    int n = two.length();

    int[][] cost = new int[m + 1][n + 1];

    for (int j = 0; j <= n; j++) {
      cost[0][j] = j;
    }

    for (int i = 0; i <= m; i++) {
      cost[i][0] = i;
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (one.charAt(i - 1) == two.charAt(j - 1)) {
          cost[i][j] = cost[i - 1][j - 1];
        } else {
          int insert = cost[i][j - 1] + 1;
          int delete = cost[i - 1][j] + 1;
          int update = cost[i - 1][j - 1] + 1;
          cost[i][j] = Math.min(Math.min(insert, delete), update);
        }
      }
    }

    return cost[m][n];
  }

  private static int minDistanceTD(String one, String two, int[][] dp) {
    if (one.length() == 0 || two.length() == 0) {
      return Math.max(one.length(), two.length());
    }
    int m = one.length();
    int n = two.length();
    if (dp[m][n] != 0) {
      return dp[m][n];
    }

    if (one.charAt(0) == two.charAt(0)) {
      dp[m][n] = minDistanceTD(one.substring(1), two.substring(1), dp);
      return dp[m][n];
    } else {
      int insert = minDistanceTD(one, two.substring(1), dp);
      int delete = minDistanceTD(one.substring(1), two, dp);
      int update = minDistanceTD(one.substring(1), two.substring(1), dp);
      dp[m][n] = Math.min(insert, Math.min(delete, update)) + 1;
      return dp[m][n];
    }
  }

  private static int minDistanceRecursive(String one, String two) {
    if (one.length() == 0 || two.length() == 0) {
      return Math.max(one.length(), two.length());
    }

    if (one.charAt(0) == two.charAt(0)) {
      return minDistanceRecursive(one.substring(1), two.substring(1)); // skip both characters
    } else {
      int insert = minDistanceRecursive(one, two.substring(1));
      int delete = minDistanceRecursive(one.substring(1), two);
      int update = minDistanceRecursive(one.substring(1), two.substring(1));

      return Math.min(insert, Math.min(delete, update)) + 1;
    }
  }

  public static void main(String... args) {
    String one = "kitten";
    String two = "sitting";

    System.out.println(minDistanceBU(one, two));         // 3

    int[][] dp = new int[one.length() + 1][two.length() + 1];
    System.out.println(minDistanceTD(one, two, dp));     // 3
    System.out.println(minDistanceRecursive(one, two));  // 3

    one = "markdown";
    two = "down";
    dp = new int[one.length() + 1][two.length() + 1];
    System.out.println(minDistanceBU(one, two));         // 4
    System.out.println(minDistanceTD(one, two, dp));     // 4
    System.out.println(minDistanceRecursive(one, two));  // 4
  }
}
