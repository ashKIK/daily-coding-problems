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

  private static List<List<Integer>> powerSetBitMasking(int... nums) {

    int size = nums.length;
    int subsetSize = 1 << size;

    List<List<Integer>> result = new ArrayList<>(subsetSize);

    for (int i = 0; i < subsetSize; i++) {
      List<Integer> newSet = new ArrayList<>();
      for (int j = 0; j < size; j++) {
        if (((i >> j) & 1) == 1) {
          newSet.add(nums[j]);
        }
      }
      result.add(newSet);
    }

    return result;
  }

  private static List<List<Integer>> powerSetIterative(int... nums) {

    List<List<Integer>> result = new ArrayList<>();
    result.add(new ArrayList<>());

    for (int item : nums) {
      List<List<Integer>> newSet = new ArrayList<>();

      for (List<Integer> subset : result) {
        // copy all of the current powerset's subsets
        newSet.add(subset);

        // plus the subsets appended with the current item
        List<Integer> newSubset = new ArrayList<>(subset);
        newSubset.add(item);
        newSet.add(newSubset);
      }

      // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
      result = newSet;
    }
    return result;
  }

  // This implementation works on idea that each element in the original set
  // can either be in the power set or not in it.
  //
  // With n elements in the original set,
  // each combination can be represented by a binary string of length n.
  //
  // To get all possible combinations, all you need is a counter from 0 to 2^n - 1.
  // If the kth bit in the binary string is 1,
  // the kth element of the original set is in this combination.
  private static List<List<Integer>> powerSetBinaryString(int... nums) {

    List<List<Integer>> result = new ArrayList<>();

    int size = nums.length;
    int subsetSize = 1 << size;

    for (int i = 0; i < subsetSize; i++) {
      StringBuilder binary = new StringBuilder(Integer.toBinaryString(i));

      while (binary.length() < size) {
        binary.insert(0, "0"); // pad with 0's
      }

      List<Integer> newSet = new ArrayList<>();
      for (int j = 0; j < size; j++) {
        if (binary.charAt(j) == '1') {
          newSet.add(nums[j]);
        }
      }

      result.add(newSet);
    }
    return result;
  }

  public static void main(String... args) {
    System.out.println(powerSetBitMasking(1, 2, 3));
    System.out.println(powerSetIterative(1, 2, 3));
    System.out.println(powerSetBinaryString(1, 2, 3));
    // result =  [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
  }
}
