// Google.
//
// A knight's tour is a sequence of moves by a knight on a chessboard such that all squares are visited once.
//
// Given N, write a function to return the number of knight's tours on an N by N chessboard.

/**
 * @author ashKIK
 */
public class Problem_64 {

  private static int N;
  private static int count;

  private static int[][] visited;

  private static final int[] ROW = {2, 1, -1, -2, -2, -1, 1, 2, 2};
  private static final int[] COL = {1, 2, 2, 1, -1, -2, -2, -1, 1};

  private static boolean isValid(int x, int y) {
    return x >= 0 && y >= 0 && x < N && y < N;
  }

  private static void print(int[][] visited) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        System.out.print(visited[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  private static void knightTour(int x, int y, int pos) {
    visited[x][y] = pos;

    if (pos >= N * N) {
      count++;
      visited[x][y] = 0;
      return;
    }

    for (int k = 0; k < 8; k++) {
      int newX = x + ROW[k];
      int newY = y + COL[k];

      if (isValid(newX, newY) && visited[newX][newY] == 0) {
        knightTour(newX, newY, pos + 1);
      }
    }

    visited[x][y] = 0;
  }

  public static void main(String... args) {

    N = 5;
    visited = new int[N][N];
    knightTour(0, 0, 1);
    System.out.println(count);    // 304
  }
}
