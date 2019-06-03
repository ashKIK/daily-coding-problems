package Problems_61_70;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Google.
//
// On our special chessboard, two bishops attack each other if they share the same diagonal.
// This includes bishops that have another bishop located between them, i.e. bishops can attack through pieces.
//
// You are given N bishops, represented as (row, column) tuples on a M by M chessboard.
// Write a function to count the number of pairs of bishops that attack each other.
// The ordering of the pair doesn't matter: (1, 2) is considered the same as (2, 1).
//
// For example, given M = 5 and the list of bishops:
//
// (0, 0)
// (1, 2)
// (2, 2)
// (4, 0)
//
// The board would look like this:
//
// [b 0 0 0 0]
// [0 0 b 0 0]
// [0 0 b 0 0]
// [0 0 0 0 0]
// [b 0 0 0 0]
//
// You should return 2, since bishops 1 and 3 attack each other, as well as bishops 3 and 4.

/**
 * @author ashKIK
 */
public class Problem_68 {

  private static class Point {

    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Point point = (Point) o;
      return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  private static int countBishopPairs(Point[] points, int M) {

    Map<Point, Integer> posMap = new HashMap<>();
    Map<Point, Integer> negMap = new HashMap<>();

    for (Point p : points) {
      Point p1 = new Point(p.x - Math.min(p.x, p.y), p.y - Math.min(p.x, p.y));
      Point p2 = new Point(p.x - Math.min(p.x, M - p.y), p.y + Math.min(p.x, M - p.y));

      posMap.put(p1, !posMap.containsKey(p1) ? 1 : posMap.get(p1) + 1);
      negMap.put(p2, !negMap.containsKey(p2) ? 1 : negMap.get(p2) + 1);
    }

    return posMap.values().stream().mapToInt(c -> c).map(c -> c * (c - 1) / 2).sum() +
           negMap.values().stream().mapToInt(c -> c).map(c -> c * (c - 1) / 2).sum();
  }

  public static void main(String... args) {

    Point[] points = new Point[4];

    points[0] = new Point(0, 0);
    points[1] = new Point(1, 2);
    points[2] = new Point(2, 2);
    points[3] = new Point(4, 0);

    System.out.println(countBishopPairs(points, 5)); // 2
  }
}
