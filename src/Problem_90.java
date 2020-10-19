// Google.
//
// Given an integer n and a list of integers l,
// write a function that randomly generates a number from 0 to n-1 that isn't in l (uniform).

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author ashKIK
 */
public class Problem_90 {

  Random random;
  int listSize = 0;
  List<int[]> list;

  private void build(int N, int[] blacklist) {
    random = new Random();

    Arrays.sort(blacklist);

    list = new ArrayList<>();

    int start = 0;
    for (int value : blacklist) {
      if (start != value) {
        list.add(new int[]{start, value});
        start = value + 1;
      } else {
        start++;
      }
    }

    if (start < N) {
      list.add(new int[]{start, N});
    }

    listSize = list.size();
  }

  public int pick() {
    int index = random.nextInt(listSize);
    int[] next = list.get(index);
    return random.nextInt(next[1] - next[0]) + next[0];
  }
}
