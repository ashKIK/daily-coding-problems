import java.util.Stack;

// Facebook.
//
// Given a string of round, curly, and square open and closing brackets,
// return whether the brackets are balanced (well-formed).
//
// For example, given the string "([])[]({})", you should return true.
//
// Given the string "([)]" or "((()", you should return false.

/**
 * @author ashKIK
 */
public class Problem_27 {

  private static boolean isBalanced(String str) {
    Stack<Character> stack = new Stack<>();

    int strLength = str.length();
    for (int i = 0; i < strLength; i++) {
      char ch = str.charAt(i);

      if (ch == '(' || ch == '[' || ch == '{') {
        stack.push(ch);
      } else {
        if (stack.isEmpty()) {
          return false;
        }

        char prev = stack.pop();

        if (prev == '(' && ch != ')') {
          return false;
        }
        if (prev == '[' && ch != ']') {
          return false;
        }
        if (prev == '{' && ch != '}') {
          return false;
        }
      }
    }

    return stack.isEmpty();
  }

  public static void main(String... args) {
    System.out.println(isBalanced("([])[]({})"));   // true
    System.out.println(isBalanced("([)]"));         // false
    System.out.println(isBalanced("((()"));         // false
  }
}
