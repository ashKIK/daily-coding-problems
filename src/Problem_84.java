// Amazon.
//
// Given a matrix of 1s and 0s, return the number of "islands" in the matrix.
// A 1 represents land and 0 represents water,
// so an island is a group of 1s that are neighboring whose perimeter is surrounded by water.
//
// For example, this matrix has 4 islands.
//
// 1 0 0 0 0
// 0 0 1 1 0
// 0 1 1 0 0
// 0 0 0 0 0
// 1 1 0 0 1
// 1 1 0 0 1

/**
 * @author ashKIK
 */
public class Problem_84 {

  private static int[][] copyMatrix(int[][] matrix) {

    int size = matrix.length;
    int[][] copy = new int[size][];

    for (int row = 0; row < size; row++) {
      copy[row] = new int[matrix[row].length];
      for (int col = 0; col < matrix[row].length; col++) {
        copy[row][col] = matrix[row][col] != 0 ? -1 : 0;
      }
    }

    return copy;
  }

  private static int countIslands(int[][] matrix) {

    int[][] visited = copyMatrix(matrix);
    int count = 1;

    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        if (dfs(visited, row, col, count)) {
          count++;
        }
      }
    }

    return count - 1;
  }

  private static boolean dfs(int[][] visited, int row, int col, int id) {

    if (row < 0 || row >= visited.length) {
      return false;
    }
    if (col < 0 || col >= visited[row].length) {
      return false;
    }
    if (visited[row][col] != -1) {
      return false;
    }

    visited[row][col] = id;

    dfs(visited, row, col + 1, id);
    dfs(visited, row, col - 1, id);
    dfs(visited, row + 1, col, id);
    dfs(visited, row - 1, col, id);

    return true;
  }

  public static void main(String... args) {

    int[][] matrix = {
        {1, 0, 0, 0, 0},
        {0, 0, 1, 1, 0},
        {0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0},
        {1, 1, 0, 0, 1},
        {1, 1, 0, 0, 1}
    };

    System.out.println(countIslands(matrix));
  }
}
