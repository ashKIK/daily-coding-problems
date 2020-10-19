import java.util.Arrays;

// Uber.
//
// Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
//
// For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
//
// Follow-up: what if you can't use division?

/**
 * @author ashKIK
 */
public class Problem_2 {

  private static final double EPS = 1e-9;

  // without division operator
  private static int[] product2(int[] arr) {
    int n = arr.length;

    double sum = 0.0;
    for (int value : arr) {
      sum += Math.log10(value);
    }

    // output product for each index
    // anti log to find original product value
    int[] res = new int[n];
    for (int i = 0; i < n; i++) {
      res[i] = (int) (EPS + Math.pow(10.0, sum - Math.log10(arr[i])));
    }

    return res;
  }

  // with division operator
  private static int[] product1(int[] arr) {
    int n = arr.length;

    if (n < 2) {
      return null;
    }

    int[] res = new int[n];

    long product = Arrays.stream(arr).asLongStream().reduce(1L, (a, b) -> a * b);

    for (int i = 0; i < n; i++) {
      res[i] = (int) (product / arr[i]);
    }

    return res;
  }

  public static void main(String... args) {
    System.out.println(Arrays.toString(product2(new int[]{11, 24, 53, 74, 95})));
    System.out.println(Arrays.toString(product1(new int[]{11, 24, 53, 74, 95})));
    System.out.println(Arrays.toString(product2(new int[]{31, 72, 43})));
    System.out.println(Arrays.toString(product1(new int[]{31, 72, 43})));
  }
}
