package Problems_81_90;

// Google.
//
// Given a string of parentheses, write a function to compute the minimum number of parentheses
// to be removed to make the string valid (i.e. each open parenthesis is eventually closed).
//
// For example, given the string "()())()", you should return 1.
// Given the string ")(", you should return 2, since we must remove all of them.

/**
 * @author ashKIK
 */
public class Problem_86 {

  private static int removeParentheses(String str) {
    int count = 0;
    int ans = 0;

    int size = str.length();
    for (int i = 0; i < size; i++) {
      if (str.charAt(i) == '(') {
        count++;
      } else if (str.charAt(i) == ')') {
        count--;
      } else {
        continue;
      }

      if (count >= 0) {
        continue;
      }

      ans++;
      count = 0;
    }

    return ans + Math.abs(count);
  }

  public static void main(String... args) {

    System.out.println(removeParentheses("((()))"));
    System.out.println(removeParentheses("()())()"));
    System.out.println(removeParentheses(")("));
  }
}
