package Problems_1_10;

// Facebook.
//
// Given the mapping a = 1, b = 2, ... z = 26, and an encoded message,
// count the number of ways it can be decoded.
//
// For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
//
// You can assume that the messages are decodable. For example, '001' is not allowed.

/**
 * @author ashKIK
 */
public class Problem_7 {

  private static int numDecodings(String str) {
    int n = str.length();
    if (n == 0) {
      return 0;
    }

    int[] memo = new int[n + 1];
    memo[n] = 1;
    memo[n - 1] = str.charAt(n - 1) == '0' ? 0 : 1;

    for (int i = n - 2; i >= 0; i--) {
      if (str.charAt(i) != '0') {
        if (Integer.parseInt(str.substring(i, i + 2)) <= 26) {
          memo[i] = memo[i + 1] + memo[i + 2];
        } else {
          memo[i] = memo[i + 1];
        }
      }
    }

    return memo[0];
  }

  public static void main(String... args) {
    System.out.println(numDecodings("111"));
  }
}
