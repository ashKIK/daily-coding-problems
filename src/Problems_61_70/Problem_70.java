package Problems_61_70;

// Microsoft.
//
// A number is considered perfect if its digits sum up to exactly 10.
//
// Given a positive integer n, return the n-th perfect number.
//
// For example, given 1, you should return 19. Given 2, you should return 28.

/**
 * @author ashKIK
 */
public class Problem_70 {

  // O(n)
  // All multiples of 9 are present in arithmetic progression
  // 19, 28, 37, 46, 55, 64, 73, 82, 91, 100, 109, . . ,
  // There are numbers in above series whose sum of digits is not 9
  // we start with 19 and increment by 9 instead checking one by one
  private static int nthPerfectNumber(int n) {
    int count = 0;

    // for (int curr = 1; ; curr++) {
    for (int current = 19; ; current += 9) {
      int sum = 0;
      for (int x = current; x > 0; x /= 10) {
        sum += x % 10;
      }

      if (sum == 10) {
        count++;
      }

      if (count == n) {
        return current;
      }
    }
  }

  // O(1)
  // nth perfect number = nth number from A.P. + (9 * number_of_outliers)
  private static int nthPerfectNumberFast(int n) {
    int nthElement = 19 + (n - 1) * 9;
    int outliers = (int) Math.log10(nthElement) - 1;

    // nth perfect number
    nthElement += 9 * outliers;
    return nthElement;
  }

  public static void main(String... args) {

    System.out.println(nthPerfectNumber(1));
    System.out.println(nthPerfectNumber(2));

    System.out.println(nthPerfectNumberFast(1));
    System.out.println(nthPerfectNumberFast(2));
  }
}
