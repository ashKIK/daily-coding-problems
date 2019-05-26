package Problems_31_40;

import java.util.ArrayList;
import java.util.List;

// Google.
//
// The power set of a set is the set of all its subsets.
// Write a function that, given a set, generates its power set.
//
// For example, given the set {1, 2, 3},
// it should return {{}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}}.
//
// You may also use a list or array to represent a set.

/**
 * @author ashKIK
 */
public class Problem_37 {

  private static List<List<Integer>> powerSet(int... nums) {
    int size = nums.length;
    int subsetSize = 1 << size;

    List<List<Integer>> result = new ArrayList<>(subsetSize);

    for (int i = 0; i < subsetSize; i++) {
      List<Integer> list = new ArrayList<>();
      for (int j = 0; j < size; j++) {
        if (((i >> j) & 1) == 1) {
          list.add(nums[j]);
        }
      }
      result.add(list);
    }

    return result;
  }

  public static void main(String... args) {
    System.out.println(powerSet(1, 2, 3));
    // result =  [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
  }
}
