// Facebook.
//
// You are given an array of non-negative integers that represents a two-dimensional elevation map
// where each element is unit-width wall and the integer is the height.
// Suppose it will rain and all spots between two walls get filled up.
//
// Compute how many units of water remain trapped on the map in O(N) time and O(1) space.
//
// For example, given the input [2, 1, 2], we can hold 1 unit of water in the middle.
//
// Given the input [3, 0, 1, 3, 0, 5],
// we can hold 3 units in the first index, 2 in the second,
// and 3 in the fourth index (we cannot hold 5 since it would run off to the left),
// so we can trap 8 units of water.

/**
 * @author ashKIK
 */
public class Problem_30 {

  private static int trapWater(int[] height) {
    int maxIndex = 0;
    int size = height.length;

    for (int i = 1; i < size; i++) {
      maxIndex = (height[i] > height[maxIndex]) ? i : maxIndex;
    }

    int max = -1;
    int water = 0;

    for (int i = 0; i < maxIndex; i++) {
      max = (height[i] > max) ? height[i] : max;
      water += (max - height[i]);
    }

    max = -1;
    for (int i = size - 1; i > maxIndex; i--) {
      max = (height[i] > max) ? height[i] : max;
      water += (max - height[i]);
    }

    return water;
  }

  public static void main(String... args) {
    System.out.println(trapWater(new int[]{2, 1, 2}));          // 1
    System.out.println(trapWater(new int[]{3, 0, 1, 3, 0, 5})); // 8
  }
}
