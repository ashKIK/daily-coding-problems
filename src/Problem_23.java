import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// Google.
//
// You are given an M by N matrix consisting of booleans that represents a board.
// Each True boolean represents a wall. Each False boolean represents a tile you can walk on.
//
// Given this matrix, a start coordinate, and an end coordinate,
// return the minimum number of steps required to reach the end coordinate from the start.
// If there is no possible path, then return null. You can move up, left, down, and right.
// You cannot move through walls. You cannot wrap around the edges of the board.
//
// For example, given the following board:
//
// [[f, f, f, f],
// [t, t, f, t],
// [f, f, f, f],
// [f, f, f, f]]
//
// and start = (3, 0) (bottom left) and end = (0, 0) (top left),
// the minimum number of steps required to reach the end is 7,
// since we would need to go through (1, 2) because there is a wall everywhere else on the second row.

/**
 * @author ashKIK
 */
public class Problem_23 {

  private static class Point {

    private int x;
    private int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    boolean isEqual(Point point) {
      return x == point.x && y == point.y;
    }
  }

  private static Integer minimumPathLength(boolean[][] board, Point from, Point to) {
    int[][] steps = new int[board.length][board[0].length];
    for (int[] row : steps) {
      Arrays.fill(row, ~0);
    }

    Deque<Point> queue = new ArrayDeque<>();
    queue.addLast(from);
    steps[from.y][from.x] = 0;

    while (!queue.isEmpty()) {
      Point point = queue.removeFirst();

      if (!point.isEqual(to)) {
        List<Point> pointList = List.of(
            new Point(point.x + 1, point.y),
            new Point(point.x, point.y - 1),
            new Point(point.x - 1, point.y),
            new Point(point.x, point.y + 1));

        for (Point nextPoint : pointList) {
          if (validStep(board, nextPoint) &&
              (steps[nextPoint.y][nextPoint.x] < 0 ||
                  steps[point.y][point.x] + 1 < steps[nextPoint.y][nextPoint.x])) {

            steps[nextPoint.y][nextPoint.x] = steps[point.y][point.x] + 1;
            queue.addLast(nextPoint);
          }
        }
      }
    }

    if (steps[to.y][to.x] == Integer.MAX_VALUE) {
      return null;
    } else {
      return steps[to.y][to.x];
    }
  }

  private static boolean validStep(boolean[][] board, Point point) {
    return point.y >= 0 && point.y < board.length &&
        point.x >= 0 && point.x < board[point.y].length &&
        !board[point.y][point.x];
  }

  public static void main(String... args) {
    boolean[][] board = {
        {false, false, false, false},
        {true, true, false, true},
        {false, false, false, false},
        {false, false, false, false}};
    System.out.println(minimumPathLength(board, new Point(0, 3), new Point(0, 0)));
  }
}
