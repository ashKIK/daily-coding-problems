package Problems_61_70;

// Google.
//
// Implement integer exponentiation.
// That is, implement the pow(x, y) function, where x and y are integers and returns x^y.
//
// Do this faster than the naive method of repeated multiplication.

/**
 * @author ashKIK
 */
public class Problem_61 {

  // O(log n)
  private static int iPow(int base, int exp) {
    int result = 1;
    while (exp != 0) {
      if ((exp & 1) == 1) {
        result *= base;
      }
      exp >>= 1;
      base *= base;
    }

    return result;
  }

  // O(log n)
  private static double fPow(double base, int exp) {
    if (exp == 0) {
      return 1;
    }
    if (exp == 1) {
      return base;
    }
    if (exp == -1) {
      return 1 / base;
    }
    if (exp == 2) {
      return base * base;
    }
    return fPow(fPow(base, exp / 2), 2) * fPow(base, exp % 2);
  }

  public static void main(String... args) {
    System.out.println(iPow(0, 1));
    System.out.println(iPow(2, 3));
    System.out.println(iPow(3, 7));
    System.out.println(iPow(4, 11));

    System.out.println(fPow(0.0, 1));
    System.out.println(fPow(2.0, 3));
    System.out.println(fPow(3.0, 7));
    System.out.println(fPow(4.0, 11));
  }
}
