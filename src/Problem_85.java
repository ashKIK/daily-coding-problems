// Facebook.
//
// Given three 32-bit integers x, y, and b, return x if b is 1 and y if b is 0,
// using only mathematical or bit operations. You can assume b can only be 1 or 0.

/**
 * @author ashKIK
 */
public class Problem_85 {

  private static int iAbs(int v) {
    int mask = v >> 31;
    return (v ^ mask) - mask;
  }

  private static int getNum(int x, int y, int b) {
    return x * b + y * iAbs((b - 1));
  }

  public static void main(String... args) {

    System.out.println(getNum(5, 8, 0));
    System.out.println(getNum(5, 8, 1));
  }
}
