// Google.
//
// You are given an N by M 2D matrix of lowercase letters. Determine the minimum number of columns that can be removed to ensure that each row is ordered from top to bottom lexicographically. That is, the letter at each column is lexicographically later as you go down each row. It does not matter whether each row itself is ordered lexicographically.
//
// For example, given the following table:
//
// cba
// daf
// ghi
//
// This is not ordered because of the a in the center. We can remove the second column to make it ordered:
//
// ca
// df
// gi
//
// So your function should return 1, since we only needed to remove 1 column.
//
// As another example, given the following table:
//
// c
//
// Your function should return 0, since the rows are already ordered (there's only one row).
//
// As another example, given the following table:
//
// zyx
// wvu
// tsr
//
// Your function should return 3, since we would need to remove all the columns to order it.

/**
 * @author ashKIK
 */
public class Problem_76 {

  private static int minDeletionSize(String... table) {
    int count = 0;
    for (int c = 0; c < table[0].length(); c++) {
      for (int r = 0; r < table.length - 1; r++) {
        if (table[r].charAt(c) > table[r + 1].charAt(c)) {
          count++;
          break;
        }
      }
    }

    return count;
  }

  public static void main(String... args) {

    System.out.println(minDeletionSize("cba", "daf", "ghi"));
    System.out.println(minDeletionSize("c"));
    System.out.println(minDeletionSize("zyu", "wvu", "tsr"));
  }
}
