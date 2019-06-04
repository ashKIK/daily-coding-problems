package Problems_71_80;

import java.util.Random;

// Two Sigma.
//
// Using a function rand7() that returns an integer from 1 to 7 (inclusive) with uniform probability,
// implement a function rand5() that returns an integer from 1 to 5 (inclusive).

/**
 * @author ashKIK
 */
public class Problem_71 {

  private static Random random = new Random();

  private static int rand7() {
    return random.nextInt(7) + 1;
  }

  private static int rand5() {
    int x = ~(1 << 31);
    while (x > 5) {
      x = rand7();
    }
    return x;
  }

  public static void main(String... args) {

    System.out.println(rand5());
    System.out.println(rand5());
    System.out.println(rand5());
    System.out.println(rand5());
    System.out.println(rand5());
    System.out.println(rand5());
    System.out.println(rand5());
  }
}
