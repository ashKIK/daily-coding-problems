// Facebook.
//
// There is an N by M matrix of zeroes.
// Given N and M, write a function to count the number of ways of
// starting at the top-left corner and getting to the bottom-right corner.
// You can only move right or down.
//
// For example, given a 2 by 2 matrix, you should return 2,
// since there are two ways to get to the bottom-right:
//
// Right, then down
// Down, then right
//
// Given a 5 by 5 matrix, there are 70 ways to get to the bottom-right.

/**
 * @author ashKIK
 */
public class Problem_62 {

  private static int numberOfPaths(int m, int n) {
    int[] dp = new int[n];
    dp[0] = 1;

    for (int i = 0; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[j] += dp[j - 1];
      }
    }

    return dp[n - 1];
  }

  // calculate (m-1 + n-1) C (n-1)
  // (m+n-2)! / (n-1)! (m-1)!
  private static int numberOfPathsMath(int m, int n) {
    int count = 1;
    for (int i = n; i < (m + n - 1); i++) {
      count *= i;
      count /= (i - n + 1);
    }
    return count;
  }

  public static void main(String... args) {
    System.out.println(numberOfPathsMath(2, 2));
    System.out.println(numberOfPathsMath(3, 3));
    System.out.println(numberOfPathsMath(4, 4));
    System.out.println(numberOfPathsMath(5, 5));

    System.out.println();

    System.out.println(numberOfPaths(2, 2));
    System.out.println(numberOfPaths(3, 3));
    System.out.println(numberOfPaths(4, 4));
    System.out.println(numberOfPaths(5, 5));
  }
}
