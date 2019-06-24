package Problems_71_80;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// Snapchat.
//
// Given a list of possibly overlapping intervals, return a new list of intervals where all overlapping intervals have been merged.
//
// The input list is not necessarily ordered in any way.
//
// For example, given [(1, 3), (5, 8), (4, 10), (20, 25)], you should return [(1, 3), (4, 10), (20, 25)].

/**
 * @author ashKIK
 */
public class Problem_77 {

  private static class Interval {

    int start;
    int end;

    Interval(int s, int e) {
      start = s;
      end = e;
    }

    @Override
    public String toString() {
      return "(" + start + ", " + end + ")";
    }
  }

  private static List<Interval> mergeIntervals(List<Interval> intervals) {

    intervals.sort(Comparator.comparingInt(o -> o.start));
    LinkedList<Interval> merged = new LinkedList<>();

    for (Interval interval : intervals) {
      if (merged.isEmpty() || merged.getLast().end < interval.start) {
        merged.add(interval);
      } else {
        merged.getLast().end = Math.max(merged.getLast().end, interval.end);
      }
    }

    return merged;
  }

  public static void main(String... args) {

    List<Interval> intervals = Arrays.asList(
        new Interval(1, 3),
        new Interval(5, 8),
        new Interval(4, 10),
        new Interval(20, 25));

    mergeIntervals(intervals).stream().map(Interval::toString).forEach(System.out::println);
  }
}
