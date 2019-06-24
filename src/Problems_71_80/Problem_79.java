package Problems_71_80;

import java.util.Stack;

// Facebook.
//
// Given an array of integers,
// write a function to determine whether the array could become non-decreasing by modifying at most 1 element.
//
// For example, given the array [10, 5, 7], you should return true,
// since we can modify the 10 into a 1 to make the array non-decreasing.
//
// Given the array [10, 5, 1], you should return false,
// since we can't modify any one element to get a non-decreasing array.

/**
 * @author ashKIK
 */
public class Problem_79 {

  private static boolean check(int[] arr) {
    int replace = 0;
    Stack<Integer> stack = new Stack<>();

    for (int value : arr) {
      while (!stack.isEmpty() && value < stack.peek()) {
        stack.pop();
        replace++;
      }
      if (replace > 1) {
        return false;
      }
      stack.push(value);
    }

    return true;
  }

  public static void main(String... args) {

    System.out.println(check(new int[]{10, 5, 7}));
    System.out.println(check(new int[]{10, 5, 1}));
  }
}
