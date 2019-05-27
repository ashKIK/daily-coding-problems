package Problems_31_40;

// Google.
//
// Given an array of integers where every integer occurs three times except for one integer,
// which only occurs once, find and return the non-duplicated integer.
//
// For example, given [6, 1, 3, 3, 3, 6, 6], return 1. Given [13, 19, 13, 13], return 19.
//
// Do this in O(N) time and O(1) space.

/**
 * @author ashKIK
 */
public class Problem_40 {

  private static int findMissingInteger(int... arr) {
    int ones = 0;
    int twos = 0;
    for (int value : arr) {
      ones = (ones ^ value) & ~twos;
      twos = (twos ^ value) & ~ones;
    }
    return ones;
  }

  public static void main(String... args) {
    System.out.println(findMissingInteger(6, 1, 3, 3, 3, 6, 6)); // 1
    System.out.println(findMissingInteger(13, 19, 13, 13));      // 9
  }
}
