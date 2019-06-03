package Problems_61_70;

import java.util.Arrays;

// Facebook.
//
// Given a list of integers, return the largest product that can be made by multiplying any three integers.
//
// For example, if the list is [-10, -10, 5, 2], we should return 500, since that's -10 * -10 * 5.
//
// You can assume the list has at least three integers.

/**
 * @author ashKIK
 */
public class Problem_69 {

  private static int maxTriplet(int... nums) {
    int size = nums.length;
    Arrays.sort(nums);
    int res = nums[size - 1];
    return Math.max(res, nums[size - 3] * nums[size - 2] * nums[size - 1]);
  }

  public static void main(String... args) {

    System.out.println(maxTriplet(-10, -10, 5, 2));   // 500
  }
}
