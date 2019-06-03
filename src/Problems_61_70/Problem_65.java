package Problems_61_70;

import java.util.ArrayList;
import java.util.List;

// Amazon.
//
// Given a N by M matrix of numbers, print out the matrix in a clockwise spiral.
//
// For example, given the following matrix:
//
// [[1,  2,  3,  4,  5],
//  [6,  7,  8,  9,  10],
//  [11, 12, 13, 14, 15],
//  [16, 17, 18, 19, 20]]
//
// You should print out the following:
//
//  1
//  2
//  3
//  4
//  5
// 10
// 15
// 20
// 19
// 18
// 17
// 16
// 11
//  6
//  7
//  8
//  9
// 14
// 13
// 12

/**
 * @author ashKIK
 */
public class Problem_65 {

  private static List<Integer> clockwiseSpiral(int[][] matrix) {
    int m = matrix.length;
    if (m == 0) {
      return new ArrayList<>();
    }
    int n = matrix[0].length;
    int numRows = m - 1;
    int numCols = n - 1;
    int numElements = m * n;

    List<Integer> r = new ArrayList<>(m * n);

    boolean[][] visited = new boolean[m][n];
    boolean left = false;
    boolean right = true;
    boolean up = false;
    boolean down = false;

    int x = 0;
    int y = 0;
    for (int i = 0; i < numElements; i++) {
      r.add(matrix[x][y]);
      visited[x][y] = true;

      if (right) {
        if (y == numCols || visited[x][y + 1]) {
          right = false;
          down = true;
          x++;
        } else {
          y++;
        }
        continue;
      }

      if (left) {
        if (y == 0 || visited[x][y - 1]) {
          left = false;
          up = true;
          x--;
        } else {
          y--;
        }
        continue;
      }

      if (down) {
        if (x == numRows || visited[x + 1][y]) {
          down = false;
          left = true;
          y--;
        } else {
          x++;
        }
        continue;
      }

      if (up) {
        if (x == 0 || visited[x - 1][y]) {
          up = false;
          right = true;
          y++;
        } else {
          x--;
        }
      }
    }
    return r;
  }

  public static void main(String... args) {

    int[][] matrix = {
        {1, 2, 3, 4, 5},
        {6, 7, 8, 9, 10},
        {11, 12, 13, 14, 15},
        {16, 17, 18, 19, 20}};

    clockwiseSpiral(matrix).forEach(System.out::println);
  }
}
