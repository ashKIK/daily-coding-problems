package Problems_41_50;

import java.util.Arrays;

// Google.
//
// We can determine how "out of order" an array A is by counting the number of inversions it has.
// Two elements A[i] and A[j] form an inversion if A[i] > A[j] but i < j.
// That is, a smaller element appears after a larger element.
//
// Given an array, count the number of inversions it has. Do this faster than O(N^2) time.
//
// You may assume each element in the array is distinct.
//
// For example, a sorted list has zero inversions.
// The array [2, 4, 1, 3, 5] has three inversions: (2, 1), (4, 1), and (4, 3).
// The array [5, 4, 3, 2, 1] has ten inversions: every distinct pair forms an inversion.

/**
 * @author ashKIK
 */
public class Problem_44 {

  private static int merge(int[] arr, int[] aux, int low, int mid, int high) {
    int k = low, i = low, j = mid + 1;
    int inversionCount = 0;

    while (i <= mid && j <= high) {
      if (arr[i] <= arr[j]) {
        aux[k++] = arr[i++];
      } else {
        aux[k++] = arr[j++];
        inversionCount += (mid - i + 1);
      }
    }

    while (i <= mid) {
      aux[k++] = arr[i++];
    }

    for (i = low; i <= high; i++) {
      arr[i] = aux[i];
    }

    return inversionCount;
  }

  private static int mergeSort(int[] arr, int[] aux, int low, int high) {
    if (high == low) {
      return 0;
    }

    int mid = (low + ((high - low) >> 1));

    int inversionCount = mergeSort(arr, aux, low, mid);
    inversionCount += mergeSort(arr, aux, mid + 1, high);

    inversionCount += merge(arr, aux, low, mid, high);

    return inversionCount;
  }

  public static void main(String... args) {
    int[] arr = {5, 4, 3, 2, 1};
    int[] aux = Arrays.copyOf(arr, arr.length);

    System.out.println("Inversion count: " + mergeSort(arr, aux, 0, arr.length - 1));
  }
}
