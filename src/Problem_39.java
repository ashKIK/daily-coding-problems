// Dropbox.
//
// Conway's Game of Life takes place on an infinite two-dimensional board of square cells.
// Each cell is either dead or alive, and at each tick, the following rules apply:
//
// Any live cell with less than two live neighbours dies.
// Any live cell with two or three live neighbours remains living.
// Any live cell with more than three live neighbours dies.
// Any dead cell with exactly three live neighbours becomes a live cell.
//
// A cell neighbours another cell if it is horizontally, vertically, or diagonally adjacent.
//
// Implement Conway's Game of Life.
// It should be able to be initialized with a starting list of live cell coordinates
// and the number of steps it should run for.
//
// Once initialized, it should print out the board state at each step.
// Since it's an infinite board, print out only the relevant coordinates,
// i.e. from the top-leftmost live cell to bottom-rightmost live cell.
//
// You can represent a live cell with an asterisk (*) and a dead cell with a dot (.).

/**
 * @author ashKIK
 */
public class Problem_39 {

  static class GameOfLife {

    boolean[][] board = new boolean[3][3];

    GameOfLife() {
    }

    GameOfLife(String[] board) {
      set((i, j, s) -> board[i].charAt(j * 2) == '*');
    }

    void set(Setter setter) {
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
          board[i][j] = setter.set(i, j, board[i][j]);
        }
      }
    }

    void get(Getter getter) {
      set((i, j, s) -> {
        getter.get(i, j, s);
        return s;
      });
    }

    int countNeighbors(int i, int j) {
      var counter = new Getter() {
        int count;

        @Override
        public void get(int li, int lj, boolean state) {
          if (distance(i, j, li, lj) == 1 && board[li][lj]) {
            count++;
          }
        }
      };
      get(counter);
      return counter.count;
    }

    int distance(int i, int j, int li, int lj) {
      return Math.max(
          Math.abs(i - li),
          Math.abs(j - lj));
    }

    GameOfLife makeNextGeneration() {
      var n = new GameOfLife();
      n.set((i, j, s) -> {
        var alive = board[i][j];
        int c = countNeighbors(i, j);
        if (alive) {
          return c == 2 || c == 3;
        } else {
          return c == 3;
        }
      });
      return n;
    }

    void print() {
      get((i, j, s) -> {
        if (j == 0) {
          System.out.println();
        }
        System.out.print(s ? "* " : "^ ");
      });
    }

    interface Setter {

      boolean set(int i, int j, boolean state);
    }

    interface Getter {

      void get(int i, int j, boolean state);
    }
  }

  public static void main(String[] args) {
    String[] board = {
        "* ^ * ",
        "^ * ^ ",
        "^ * ^ ",
    };
    GameOfLife gameOfLife = new GameOfLife(board);

    int steps = 5;
    for (int i = 0; i < steps; i++) {
      gameOfLife.print();
      System.out.println("\n");
      gameOfLife = gameOfLife.makeNextGeneration();
    }
  }
}
