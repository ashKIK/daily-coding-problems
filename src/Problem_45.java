import java.util.Random;

// Two Sigma.
//
// Using a function rand5() that returns an integer from 1 to 5 (inclusive) with uniform probability,
// implement a function rand7() that returns an integer from 1 to 7 (inclusive).

/**
 * @author ashKIK
 */
public class Problem_45 {

  // Rejection Sampling

  private static Random random = new Random();

  private static int rand5() {
    return random.nextInt(5) + 1;
  }

  private static int rand7() {
    int x = 1 + ((rand5() * rand5()) % 8);
    return x == 8 ? 1 : x;
  }

  // Average calls to rand5 -> ~2.2
  private static int rand7Fast() {
    int r = rand5();
    int n = 5;
    do {
      r = (r - 1) * 5 + rand5();
      int m = n * 5 / 7 * 7;
      if (r <= m) {
        return r % 7 + 1;
      }
      r -= m;
      n = n * 5 - m;
    } while (true);
  }

  // Average calls to rand7 -> 2.45
  private static int rand10() {
    int row, col, idx;
    do {
      row = rand7();
      col = rand7();
      idx = col + (row - 1) * 7;
    } while (idx > 40);
    return 1 + (idx - 1) % 10;
  }

  // Average calls to rand7 -> 2.2123
  private static int rand10Fast() {
    while (true) {
      int a = rand7();
      int b = rand7();
      int idx = b + (a - 1) * 7;
      if (idx <= 40) {
        return 1 + (idx - 1) % 10;
      }
      a = idx - 40;
      b = rand7();
      // get uniform dist from 1 - 63
      idx = b + (a - 1) * 7;
      if (idx <= 60) {
        return 1 + (idx - 1) % 10;
      }
      a = idx - 60;
      b = rand7();
      // get uniform dist from 1 - 21
      idx = b + (a - 1) * 7;
      if (idx <= 20) {
        return 1 + (idx - 1) % 10;
      }
    }
  }
}
