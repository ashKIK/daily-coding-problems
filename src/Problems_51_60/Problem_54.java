package Problems_51_60;

import Problems_51_60.Problem_54.DancingLinksAlgorithm.DancingLinks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Dropbox.
//
// Sudoku is a puzzle where you're given a partially-filled 9 by 9 grid with digits.
// The objective is to fill the grid with the constraint
// that every row, column, and box (3 by 3 subgrid) must contain all of the digits from 1 to 9.
//
// Implement an efficient sudoku solver.

/**
 * @author ashKIK
 */
public class Problem_54 {

  private static class BacktrackingAlgorithm {

    private static final int BOARD_SIZE = 9;
    private static final int SUBSECTION_SIZE = 3;
    private static final int BOARD_START_INDEX = 0;

    private static final int NO_VALUE = 0;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;

    private int[][] board;

    private void initBoard(int[][] board) {
      this.board = board;
    }

    private void printBoard() {
      for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
        for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
          System.out.print(board[row][column] + " ");
        }
        System.out.println();
      }
    }

    private boolean solve(int[][] board) {
      for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
        for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
          if (board[row][column] == NO_VALUE) {
            for (int k = MIN_VALUE; k <= MAX_VALUE; k++) {
              board[row][column] = k;
              if (isValid(board, row, column) && solve(board)) {
                return true;
              }
              board[row][column] = NO_VALUE;
            }
            return false;
          }
        }
      }
      return true;
    }

    private boolean isValid(int[][] board, int row, int column) {
      return rowConstraint(board, row) &&
          columnConstraint(board, column) &&
          subsectionConstraint(board, row, column);
    }

    private boolean subsectionConstraint(int[][] board, int row, int column) {
      boolean[] constraint = new boolean[BOARD_SIZE];
      int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
      int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

      int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
      int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

      for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
        for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
          if (!checkConstraint(board, r, constraint, c)) {
            return false;
          }
        }
      }
      return true;
    }

    private boolean columnConstraint(int[][] board, int column) {
      boolean[] constraint = new boolean[BOARD_SIZE];
      for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
        if (!checkConstraint(board, row, constraint, column)) {
          return false;
        }
      }
      return true;
    }

    private boolean rowConstraint(int[][] board, int row) {
      boolean[] constraint = new boolean[BOARD_SIZE];
      for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
        if (!checkConstraint(board, row, constraint, column)) {
          return false;
        }
      }
      return true;
    }

    private boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column) {
      if (board[row][column] != NO_VALUE) {
        if (!constraint[board[row][column] - 1]) {
          constraint[board[row][column] - 1] = true;
        } else {
          return false;
        }
      }
      return true;
    }
  }

  static class DancingLinksAlgorithm {

    static class DancingNode {

      DancingNode L, R, U, D;
      ColumnNode C;

      DancingNode hookDown(DancingNode node) {
        assert (this.C == node.C);
        node.D = this.D;
        node.D.U = node;
        node.U = this;
        this.D = node;
        return node;
      }

      DancingNode hookRight(DancingNode node) {
        node.R = this.R;
        node.R.L = node;
        node.L = this;
        this.R = node;
        return node;
      }

      void unlinkLR() {
        this.L.R = this.R;
        this.R.L = this.L;
      }

      void relinkLR() {
        this.L.R = this.R.L = this;
      }

      void unlinkUD() {
        this.U.D = this.D;
        this.D.U = this.U;
      }

      void relinkUD() {
        this.U.D = this.D.U = this;
      }

      DancingNode() {
        L = R = U = D = this;
      }

      DancingNode(ColumnNode c) {
        this();
        C = c;
      }
    }

    static class ColumnNode extends DancingNode {

      int size;
      String name;

      ColumnNode(String n) {
        super();
        size = 0;
        name = n;
        C = this;
      }

      void cover() {
        unlinkLR();
        for (DancingNode i = this.D; i != this; i = i.D) {
          for (DancingNode j = i.R; j != i; j = j.R) {
            j.unlinkUD();
            j.C.size--;
          }
        }
      }

      void uncover() {
        for (DancingNode i = this.U; i != this; i = i.U) {
          for (DancingNode j = i.L; j != i; j = j.L) {
            j.C.size++;
            j.relinkUD();
          }
        }
        relinkLR();
      }
    }

    static class DancingLinks {

      private ColumnNode header;
      private List<DancingNode> answer;

      private void search(int k) {
        if (header.R == header) {
          handleSolution(answer);
        } else {
          ColumnNode c = selectColumnNodeHeuristic();
          c.cover();

          for (DancingNode r = c.D; r != c; r = r.D) {
            answer.add(r);

            for (DancingNode j = r.R; j != r; j = j.R) {
              j.C.cover();
            }

            search(k + 1);

            r = answer.remove(answer.size() - 1);
            c = r.C;

            for (DancingNode j = r.L; j != r; j = j.L) {
              j.C.uncover();
            }
          }
          c.uncover();
        }
      }

      private ColumnNode selectColumnNodeHeuristic() {
        int min = Integer.MAX_VALUE;
        ColumnNode ret = null;
        for (ColumnNode c = (ColumnNode) header.R; c != header; c = (ColumnNode) c.R) {
          if (c.size < min) {
            min = c.size;
            ret = c;
          }
        }
        return ret;
      }

      private ColumnNode makeDLXBoard(boolean[][] grid) {
        final int COLS = grid[0].length;

        ColumnNode headerNode = new ColumnNode("header");
        List<ColumnNode> columnNodes = new ArrayList<>();

        for (int i = 0; i < COLS; i++) {
          ColumnNode n = new ColumnNode(Integer.toString(i));
          columnNodes.add(n);
          headerNode = (ColumnNode) headerNode.hookRight(n);
        }
        headerNode = headerNode.R.C;

        for (boolean[] aGrid : grid) {
          DancingNode prev = null;
          for (int j = 0; j < COLS; j++) {
            if (aGrid[j]) {
              ColumnNode col = columnNodes.get(j);
              DancingNode newNode = new DancingNode(col);
              if (prev == null) {
                prev = newNode;
              }
              col.U.hookDown(newNode);
              prev = prev.hookRight(newNode);
              col.size++;
            }
          }
        }

        headerNode.size = COLS;

        return headerNode;
      }

      DancingLinks(boolean[][] cover) {
        header = makeDLXBoard(cover);
      }

      void runSolver() {
        answer = new LinkedList<>();
        search(0);
      }

      private void handleSolution(List<DancingNode> answer) {
        int[][] result = parseBoard(answer);
        printSolution(result);
      }

      private int[][] parseBoard(List<DancingNode> answer) {
        int size = 9;
        int[][] result = new int[size][size];
        for (DancingNode n : answer) {
          DancingNode rcNode = n;
          int min = Integer.parseInt(rcNode.C.name);
          for (DancingNode tmp = n.R; tmp != n; tmp = tmp.R) {
            int val = Integer.parseInt(tmp.C.name);
            if (val < min) {
              min = val;
              rcNode = tmp;
            }
          }
          int ans1 = Integer.parseInt(rcNode.C.name);
          int ans2 = Integer.parseInt(rcNode.R.C.name);
          int r = ans1 / size;
          int c = ans1 % size;
          int num = (ans2 % size) + 1;
          result[r][c] = num;
        }
        return result;
      }

      private static void printSolution(int[][] result) {
        int size = result.length;
        for (int[] aResult : result) {
          StringBuilder ret = new StringBuilder();
          for (int j = 0; j < size; j++) {
            ret.append(aResult[j]).append(" ");
          }
          System.out.println(ret);
        }
        System.out.println();
      }
    }

    private static final int BOARD_SIZE = 9;
    private static final int SUBSECTION_SIZE = 3;
    private static final int NO_VALUE = 0;
    private static final int CONSTRAINTS = 4;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;
    private static final int COVER_START_INDEX = 1;

    private int getIndex(int row, int column, int num) {
      return (row - 1) * BOARD_SIZE * BOARD_SIZE + (column - 1) * BOARD_SIZE + (num - 1);
    }

    private boolean[][] createExactCoverBoard() {
      boolean[][] coverBoard = new boolean[BOARD_SIZE * BOARD_SIZE * MAX_VALUE][BOARD_SIZE
          * BOARD_SIZE * CONSTRAINTS];

      int hBase = 0;
      hBase = checkCellConstraint(coverBoard, hBase);
      hBase = checkRowConstraint(coverBoard, hBase);
      hBase = checkColumnConstraint(coverBoard, hBase);
      checkSubsectionConstraint(coverBoard, hBase);

      return coverBoard;
    }

    private int checkSubsectionConstraint(boolean[][] coverBoard, int hBase) {
      for (int row = COVER_START_INDEX; row <= BOARD_SIZE; row += SUBSECTION_SIZE) {
        for (int column = COVER_START_INDEX; column <= BOARD_SIZE; column += SUBSECTION_SIZE) {
          for (int n = COVER_START_INDEX; n <= BOARD_SIZE; n++, hBase++) {
            for (int rowDelta = 0; rowDelta < SUBSECTION_SIZE; rowDelta++) {
              for (int columnDelta = 0; columnDelta < SUBSECTION_SIZE; columnDelta++) {
                int index = getIndex(row + rowDelta, column + columnDelta, n);
                coverBoard[index][hBase] = true;
              }
            }
          }
        }
      }
      return hBase;
    }

    private int checkColumnConstraint(boolean[][] coverBoard, int hBase) {
      for (int column = COVER_START_INDEX; column <= BOARD_SIZE; column++) {
        for (int n = COVER_START_INDEX; n <= BOARD_SIZE; n++, hBase++) {
          for (int row = COVER_START_INDEX; row <= BOARD_SIZE; row++) {
            int index = getIndex(row, column, n);
            coverBoard[index][hBase] = true;
          }
        }
      }
      return hBase;
    }

    private int checkRowConstraint(boolean[][] coverBoard, int hBase) {
      for (int row = COVER_START_INDEX; row <= BOARD_SIZE; row++) {
        for (int n = COVER_START_INDEX; n <= BOARD_SIZE; n++, hBase++) {
          for (int column = COVER_START_INDEX; column <= BOARD_SIZE; column++) {
            int index = getIndex(row, column, n);
            coverBoard[index][hBase] = true;
          }
        }
      }
      return hBase;
    }

    private int checkCellConstraint(boolean[][] coverBoard, int hBase) {
      for (int row = COVER_START_INDEX; row <= BOARD_SIZE; row++) {
        for (int column = COVER_START_INDEX; column <= BOARD_SIZE; column++, hBase++) {
          for (int n = COVER_START_INDEX; n <= BOARD_SIZE; n++) {
            int index = getIndex(row, column, n);
            coverBoard[index][hBase] = true;
          }
        }
      }
      return hBase;
    }

    private boolean[][] initializeExactCoverBoard(int[][] board) {
      boolean[][] coverBoard = createExactCoverBoard();
      for (int row = COVER_START_INDEX; row <= BOARD_SIZE; row++) {
        for (int column = COVER_START_INDEX; column <= BOARD_SIZE; column++) {
          int n = board[row - 1][column - 1];
          if (n != NO_VALUE) {
            for (int num = MIN_VALUE; num <= MAX_VALUE; num++) {
              if (num != n) {
                Arrays.fill(coverBoard[getIndex(row, column, num)], false);
              }
            }
          }
        }
      }
      return coverBoard;
    }
  }


  public static void main(String... args) {

    int[][] board = {
        {8, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 3, 6, 0, 0, 0, 0, 0},
        {0, 7, 0, 0, 9, 0, 2, 0, 0},
        {0, 5, 0, 0, 0, 7, 0, 0, 0},
        {0, 0, 0, 0, 4, 5, 7, 0, 0},
        {0, 0, 0, 1, 0, 0, 0, 3, 0},
        {0, 0, 1, 0, 0, 0, 0, 6, 8},
        {0, 0, 8, 5, 0, 0, 0, 1, 0},
        {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };

    System.out.println("\nBacktrackingAlgorithm Algorithm\n");

    BacktrackingAlgorithm solver = new BacktrackingAlgorithm();
    solver.initBoard(board);
    solver.solve(board);
    solver.printBoard();

    System.out.println("\nDancingLinks Algorithm\n");

    DancingLinksAlgorithm solverDL = new DancingLinksAlgorithm();
    boolean[][] cover = solverDL.initializeExactCoverBoard(board);
    new DancingLinks(cover).runSolver();
  }
}
