// Stripe.
//
// Given an array of integers,
// find the first missing positive integer in linear time and constant space.
//
// In other words, find the lowest positive integer that does not exist in the array.
// The array can contain duplicates and negative numbers as well.
//
// For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.
//
// You can modify the input array in-place.

/**
 * @author ashKIK
 */
public class Problem_4 {

  private static int missingPositiveInteger(int[] arr) {
    if (arr == null || arr.length < 2) {
      return -1;
    }

    for (int i = 0; i < arr.length; ) {
      if (arr[i] > 0 && arr[i] <= arr.length && arr[i] - 1 != i) {
        swap(arr, i, arr[i] - 1);
      } else {
        i++;
      }
    }

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] - 1 != i) {
        return i + 1;
      }
    }

    return ~0;
  }

  private static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  public static void main(String... args) {
    System.out.println(missingPositiveInteger(new int[]{3, 4, -1, 1}));
    System.out.println(missingPositiveInteger(new int[]{1, 2, 0}));
  }
}
