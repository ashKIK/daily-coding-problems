import java.util.Arrays;

// Amazon.
//
// There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time.
// Given N, write a function that returns the number of unique ways you can climb the staircase.
// The order of the steps matters.
//
// For example, if N is 4, then there are 5 unique ways:
//
// 1, 1, 1, 1
// 2, 1, 1
// 1, 2, 1
// 1, 1, 2
// 2, 2
//
// What if, instead of being able to climb 1 or 2 steps at a time,
// you could climb any number from a set of positive integers X?
//
// For example, if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.

/**
 * @author ashKIK
 */
public class Problem_12 {

  private static int countUniqueWays(int n, int[] steps) {
    Arrays.sort(steps);
    return countUniqueWays(n, 0, 0, steps);
  }

  private static int countUniqueWays(int n, int current, int result, int[] steps) {
    if (current == n) {
      result++;
    }

    if (current < n) {
      for (int step : steps) {
        result = countUniqueWays(n, current + step, result, steps);
      }
    }

    return result;
  }

  public static void main(String... args) {
    System.out.println(countUniqueWays(10, new int[]{1, 2, 5}));
    System.out.println(countUniqueWays(18, new int[]{3, 5, 7}));
  }
}
