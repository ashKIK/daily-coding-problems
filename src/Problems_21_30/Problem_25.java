package Problems_21_30;

// Facebook.
//
// Implement regular expression matching with the following special characters:
//
// . (period) which matches any single character
// * (asterisk) which matches zero or more of the preceding element
//
// That is, implement a function that takes in a string and a valid regular expression and
// returns whether or not the string matches the regular expression.
//
// For example, given the regular expression "ra." and the string "ray", your function should return true.
// The same regular expression on the string "raymond" should return false.
//
// Given the regular expression ".*at" and the string "chat", your function should return true.
// The same regular expression on the string "chats" should return false.

/**
 * @author ashKIK
 */
public class Problem_25 {

  private static boolean matches(char[] text, char[] regex) {

    int m = text.length;
    int n = regex.length;

    boolean[][] dp = new boolean[m + 1][n + 1];
    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 && j == 0) {
          dp[i][j] = true;
          continue;
        }
        if (j == 0) {
          continue;
        }
        if (regex[j - 1] == '*') {
          dp[i][j] = (j > 1 && dp[i][j - 2]) ||
              (i > 0 && j > 1 && regex[j - 2] == text[i - 1] && (dp[i - 1][j])) ||
              (i > 0 && j > 1 && regex[j - 2] == '.' && (dp[i - 1][j]));
        } else {
          dp[i][j] =
              i > 0 && dp[i - 1][j - 1] && (text[i - 1] == regex[j - 1] || regex[j - 1] == '.');
        }
      }
    }

    return dp[m][n];
  }

  public static void main(String... args) {
    System.out.println(matches("ray".toCharArray(), "ra.".toCharArray()));
    System.out.println(matches("raymond".toCharArray(), "ra.".toCharArray()));
    System.out.println(matches("chat".toCharArray(), ".*at".toCharArray()));
    System.out.println(matches("chats".toCharArray(), ".*at".toCharArray()));
    System.out.println(matches("aaaaaaaaaaaa".toCharArray(), "*aaaa".toCharArray()));
  }
}
