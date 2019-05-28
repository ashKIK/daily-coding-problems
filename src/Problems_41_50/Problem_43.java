package Problems_41_50;

import java.util.Arrays;

// Amazon.
//
// Implement a stack that has the following methods:
//
// 1) push(val), which pushes an element onto the stack
// 2) pop(), which pops off and returns the topmost element of the stack.
//      If there are no elements in the stack, then it should throw an error or return null.
// 3) max(), which returns the maximum value in the stack currently.
//      If there are no elements in the stack, then it should throw an error or return null.
//
// Each method should run in constant time.

/**
 * @author ashKIK
 */
public class Problem_43 {

  private static final class Stack {

    private final int[] values;
    private final int[] maxValues;
    private int top = -1;

    Stack(int size) {
      values = new int[size];
      maxValues = new int[size];
    }

    void push(int val) {
      if (top + 1 == values.length) {
        throw new StackOverflowError();
      }

      top++;
      values[top] = val;
      maxValues[top] = Math.max(top == 0 ? Integer.MIN_VALUE : maxValues[top - 1], values[top]);
    }

    int pop() {
      if (top == -1) {
        throw new RuntimeException();
      }
      return values[top--];
    }

    int max() {
      if (top == -1) {
        throw new RuntimeException();
      }
      return maxValues[top];
    }
  }

  public static void main(String... args) {

    int[] arr = {9, 7, 19, 4, 10, 31, 5, 33};
    System.out.println(Arrays.toString(arr));

    Stack stack = new Stack(arr.length);

    for (int val : arr) {
      stack.push(val);
      System.out.print(stack.max() + ", ");
    }
  }
}
