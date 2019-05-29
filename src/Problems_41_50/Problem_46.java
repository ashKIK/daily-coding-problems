package Problems_41_50;

// Amazon.
//
// Given a string, find the longest palindromic contiguous substring.
// If there are more than one with the maximum length, return any one.
//
// For example, the longest palindromic substring of "aabcdcb" is "bcdcb".
// The longest palindromic substring of "bananas" is "anana".

/**
 * @author ashKIK
 */
public class Problem_46 {

  // Manacher's Algorithm
  // O(n)
  private static String longestPalindromeSubstringM(String str) {

    if (str == null || str.length() <= 1) {
      return str;
    }

    int len = str.length();
    char[] t = new char[2 * len + 3];
    t[0] = '$';
    t[2 * len + 2] = '@';
    for (int i = 0; i < len; i++) {
      t[2 * i + 1] = '#';
      t[2 * i + 2] = str.charAt(i);
    }
    t[2 * len + 1] = '#';
    //array to keep track of len of palindromic substring
    int[] p = new int[t.length];
    int center = 0;
    int right = 0;
    for (int i = 1; i < t.length - 1; i++) {
      // mirror is calculated as center - (i - center)
      int mirror = 2 * center - i;
      // if i is within the right boundary take min between the mirror or length till right boundary
      if (right > i) {
        p[i] = Math.min(right - i, p[mirror]);
      }
      // check the length of palindromic substring and updated in p array
      while (t[i + (1 + p[i])] == t[i - (1 + p[i])]) {
        p[i]++;
      }
      // if i goes beyond the boundary adjust the boundary and the center
      if (i + p[i] > right) {
        center = i;
        right = i + p[i];
      }
    }
    int min = Integer.MIN_VALUE;
    int length = 0;
    // find the center and length of substring by looking at the maximum element in p
    for (int i = 0; i < len << 1; i++) {
      if (p[i + 2] > min) {
        center = i + 2;
        length = p[i + 2];
        min = p[i + 2];
      }
    }

    return str.substring((center - 1 - length) / 2, (center - 1 + length) / 2);
  }

  // DP
  // dp[i][j] denote whether a substring from i to j is palindrome
  // O(n^2) space
  private static String longestPalindromeSubstringDP(String str) {

    if (str == null || str.length() <= 1) {
      return str;
    }

    int len = str.length();
    int maxLen = 1;
    boolean[][] dp = new boolean[len][len];

    String longest = null;
    for (int l = 0; l < len; l++) {
      for (int i = 0; i < len - l; i++) {
        int j = i + l;
        if (str.charAt(i) == str.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
          dp[i][j] = true;

          if (j - i + 1 > maxLen) {
            maxLen = j - i + 1;
            longest = str.substring(i, j + 1);
          }
        }
      }
    }

    return longest;
  }

  // DP
  // O(3*n) space. Optimization
  private static String longestPalindromeSubstringDP2(String str) {

    if (str == null || str.length() <= 1) {
      return str;
    }

    char[] chr = str.toCharArray();
    int len = chr.length;
    int[][] dp = new int[3][len];
    int start, end, maxStart = 0, maxEnd = 0, max = 0;

    for (int perLen = 1; perLen <= len; perLen++) {
      for (start = 0; start <= len - perLen; start++) {
        end = start + perLen - 1;
        if (start == end) {
          dp[0][start] = 1;
        } else if (chr[start] == chr[end] && dp[2][start + 1] == perLen - 2) {
          dp[0][start] = dp[2][start + 1] + 2;
        } else {
          dp[0][start] = 0;
        }
        if (dp[0][start] > max) {
          max = dp[0][start];
          maxStart = start;
          maxEnd = end;
        }
      }
      swap(dp);
    }

    return str.substring(maxStart, maxEnd + 1);
  }

  private static void swap(int[][] dp) {
    int[] temp = dp[2];
    dp[2] = dp[1];
    dp[1] = dp[0];
    dp[0] = temp;
  }

  public static void main(String... args) {
    System.out.println(longestPalindromeSubstringM("aabcdcb"));
    System.out.println(longestPalindromeSubstringM("bananas"));

    System.out.println(longestPalindromeSubstringDP("aabcdcb"));
    System.out.println(longestPalindromeSubstringDP("bananas"));

    System.out.println(longestPalindromeSubstringDP2("aabcdcb"));
    System.out.println(longestPalindromeSubstringDP2("bananas"));
  }
}
