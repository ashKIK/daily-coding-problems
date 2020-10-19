// Facebook.
//
// Given a array of numbers representing the stock prices of a company in chronological order,
// write a function that calculates the maximum profit you could have made from buying and selling that stock once.
// You must buy before you can sell it.
//
// For example, given [9, 11, 8, 5, 7, 10], you should return 5,
// since you could buy the stock at 5 dollars and sell it at 10 dollars.

/**
 * @author ashKIK
 */
public class Problem_47 {

  private static int maxProfit(int... prices) {
    int maxProfit = 0;
    int min = prices[0];

    for (int day = 1; day < prices.length; day++) {
      int curMaxProfit = prices[day] - min;
      if (day == 1) {
        maxProfit = curMaxProfit;
      } else {
        maxProfit = Math.max(maxProfit, curMaxProfit);
      }
      min = Math.min(min, prices[day]);
    }

    return maxProfit;
  }

  public static void main(String... args) {
    System.out.println(maxProfit(9, 11, 8, 5, 7, 10));   // 5
  }
}
