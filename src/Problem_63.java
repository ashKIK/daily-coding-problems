import java.util.stream.IntStream;

// Microsoft.
//
// Given a 2D matrix of characters and a target word,
// write a function that returns whether the word can be found in the matrix by going left-to-right, or up-to-down.
//
// For example, given the following matrix:
//
// [['F', 'A', 'C', 'I'],
//  ['O', 'B', 'Q', 'P'],
//  ['A', 'N', 'O', 'B'],
//  ['M', 'A', 'S', 'S']]
//
// and the target word 'FOAM', you should return true, since it's the leftmost column.
// Similarly, given the target word 'MASS', you should return true, since it's the last row.

/**
 * @author ashKIK
 */
public class Problem_63 {

  private static boolean containsWord(char[][] matrix, String word) {
    return IntStream.range(0, matrix.length)
        .anyMatch(r -> IntStream.range(0, matrix[r].length)
            .anyMatch(c -> checkRow(matrix, r, c, word) || checkColumn(matrix, r, c, word)));
  }

  private static boolean checkColumn(char[][] matrix, int row, int col, String word) {
    if (col + word.length() > matrix[row].length) {
      return false;
    }

    return IntStream.range(0, matrix[row].length)
        .noneMatch(i -> matrix[row][col + i] != word.charAt(i));
  }

  private static boolean checkRow(char[][] matrix, int row, int col, String word) {
    if (row + word.length() > matrix.length) {
      return false;
    }

    return IntStream.range(0, matrix.length).noneMatch(i -> matrix[row + i][col] != word.charAt(i));
  }

  public static void main(String... args) {

    char[][] matrix = {
        {'F', 'A', 'C', 'I'},
        {'O', 'B', 'Q', 'P'},
        {'A', 'N', 'O', 'B'},
        {'M', 'A', 'S', 'S'}};
    System.out.println(containsWord(matrix, "FOAM"));
    System.out.println(containsWord(matrix, "MASS"));
    System.out.println(containsWord(matrix, "PQBO"));
  }
}
