package Problems_61_70;

// Square.
//
// Assume you have access to a function toss_biased()
// which returns 0 or 1 with a probability that's not 50-50 (but also not 0-100 or 100-0).
// You do not know the bias of the coin.
//
// Write a function to simulate an unbiased coin toss.

/**
 * @author ashKIK
 */
public class Problem_66 {

  // dummy
  private static int tossBiased() {
    return 0;
  }

  private static int tossUnbiased() {
    int val1 = tossBiased();
    int val2 = tossBiased();

    if (val1 == 0 && val2 == 1) {
      return 0;
    }
    if (val1 == 1 && val2 == 1) {
      return 0;
    }

    return tossUnbiased();
  }
}
