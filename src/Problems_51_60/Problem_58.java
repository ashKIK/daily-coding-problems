package Problems_51_60;

// Amazon.
//
// An sorted array of integers was rotated an unknown number of times.
//
// Given such an array, find the index of the element in the array in faster than linear time.
// If the element doesn't exist in the array, return null.
//
// For example, given the array [13, 18, 25, 2, 8, 10] and the element 8,
// return 4 (the index of 8 in the array).
//
// You can assume all the integers in the array are unique.

/**
 * @author ashKIK
 */
public class Problem_58 {

  private static Integer find(int[] arr, int value, int left, int right) {
    if (left == right) {
      return arr[left] == value ? left : null;
    }

    int mid = left + (right - left) / 2;

    if (arr[left] <= arr[mid] &&
        arr[left] <= value &&
        value <= arr[mid] ||
        arr[mid + 1] <= arr[right] &&
            (arr[mid + 1] > value || value > arr[right])) {
      return find(arr, value, left, mid);
    }

    return find(arr, value, mid + 1, right);
  }

  private static Integer findIndexByValue(int[] arr, int value) {
    return find(arr, value, 0, arr.length - 1);
  }

  public static void main(String... args) {

    System.out.println(findIndexByValue(new int[]{13, 18, 25, 2, 8, 10}, 8));    // 4
  }
}
