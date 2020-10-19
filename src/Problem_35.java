import java.util.Arrays;

// Google.
//
// Given an array of strictly the characters 'R', 'G', and 'B',
// segregate the values of the array so that all the Rs come first, the Gs come second, and the Bs come last.
// You can only swap elements of the array.
//
// Do this in linear time and in-place.
//
// For example, given the array ['G', 'B', 'R', 'R', 'B', 'R', 'G'],
// it should become ['R', 'R', 'R', 'G', 'G', 'B', 'B'].

/**
 * @author ashKIK
 */
public class Problem_35 {

  private static void swap(char[] arr, int i, int j) {
    char ch = arr[i];
    arr[i] = arr[j];
    arr[j] = ch;
  }

  private static void sortRGB(char[] arr) {
    int r = 0;
    int b = arr.length - 1;
    int i = 0;

    while (i <= b) {
      if (arr[i] == 'R') {
        swap(arr, i++, r++);
      } else if (arr[i] == 'B') {
        swap(arr, i, b--);
      } else {
        i++;
      }
    }
  }

  public static void main(String... args) {
    char[] arr = {'G', 'B', 'R', 'R', 'B', 'R', 'G'};
    sortRGB(arr);
    System.out.println(Arrays.toString(arr));
  }
}
