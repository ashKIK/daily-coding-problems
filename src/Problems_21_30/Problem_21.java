package Problems_21_30;

import java.util.Map;
import java.util.TreeMap;

// Snapchat.
//
// Given an array of time intervals (start, end) for classroom lectures (possibly overlapping),
// find the minimum number of rooms required.
//
// For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.

/**
 * @author ashKIK
 */
public class Problem_21 {

  private static class Interval {

    private int start;
    private int end;

    Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  private static int minimumRooms(Interval[] intervals) {
    Map<Integer, Integer> map = new TreeMap<>();

    for (Interval interval : intervals) {
      map.put(interval.start, map.getOrDefault(interval.start, 0) + 1);
      map.put(interval.end, map.getOrDefault(interval.end, 0) - 1);
    }

    int count = 0;
    int result = 0;

    for (int delta : map.values()) {
      result = Math.max(result, count += delta);
    }

    return result;
  }

  public static void main(String... args) {
    System.out.println(minimumRooms(
        new Interval[]{
            new Interval(30, 75),
            new Interval(0, 50),
            new Interval(60, 150)
        }));
  }
}
