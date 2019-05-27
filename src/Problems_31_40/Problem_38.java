package Problems_31_40;

// Microsoft.
//
// You have an N by N board. Write a function that, given N,
// returns the number of possible arrangements of the board where N queens can be placed
// on the board without threatening each other,
//
// i.e. no two queens share the same row, column, or diagonal.

/**
 * @author ashKIK
 */
public class Problem_38 {

  private static int size;
  private static int count;

  private static void solve(int row, int ld, int rd) {
    if (row == size) { // have placed row queens
      count++;
      return;
    }
    int pos = size & (~(row | ld | rd)); // valid positions
    while (pos != 0) {
      int p = pos & (-pos); // rightmost position
      pos -= p; // have tried this
      solve(row + p, (ld + p) << 1, (rd + p) >>> 1);
    }
  }

  private static int countSolutions(int n) {
    count = 0;
    size = (1 << n) - 1;
    solve(0, 0, 0);
    return count;
  }

  public static void main(String... args) {
    System.out.println(countSolutions(6));  //   4
    System.out.println(countSolutions(7));  //  40
    System.out.println(countSolutions(8));  //  92
    System.out.println(countSolutions(9));  // 352
    System.out.println(countSolutions(10)); // 724
  }
}
