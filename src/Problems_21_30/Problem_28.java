package Problems_21_30;

import java.util.ArrayList;
import java.util.List;

// Palantir.
//
// Write an algorithm to justify text.
// Given a sequence of words and an integer line length k,
// return a list of strings which represents each line, fully justified.
//
// More specifically, you should have as many words as possible in each line.
// There should be at least one space between each word.
// Pad extra spaces when necessary so that each line has exactly length k.
// Spaces should be distributed as equally as possible,
// with the extra spaces, if any, distributed starting from the left.
//
// If you can only fit one word on a line, then you should pad the right-hand side with spaces.
//
// Each word is guaranteed not to be longer than k.
//
// For example, given the list of words
// ["the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"] and k = 16,
// you should return the following:
//
// ["the  quick brown", # 1 extra space on the left
//  "fox  jumps  over", # 2 extra spaces distributed evenly
//  "the   lazy   dog"] # 4 extra spaces distributed evenly

/**
 * @author ashKIK
 */
public class Problem_28 {

  private static List<String> justify(List<String> words, int k) {
    List<String> lines = new ArrayList<>();
    List<String> line = new ArrayList<>();
    int length = 0;

    for (String word : words) {
      if (length + line.size() + line.size() > k) {
        lines.add(justify(line, length, k));
        line.clear();
        length = 0;
      }

      line.add(word);
      length += word.length();
    }

    if (!line.isEmpty()) {
      lines.add(justify(line, length, k));
    }

    return lines;
  }

  private static String justify(List<String> words, int length, int k) {

    int spacesLength = words.size() - 1;
    int[] spaces = new int[spacesLength];

    while (length < k) {
      for (int i = 0; i < spacesLength && length < k; i++) {
        spaces[i]++;
        length++;
      }
    }

    int i = 0;

    StringBuilder builder = new StringBuilder();
    for (String word : words) {
      builder.append(word);
      if (i < spacesLength) {
        builder.append(" ".repeat(Math.max(0, spaces[i++])));
      }
    }

    return builder.toString();
  }

  public static void main(String... args) {
    List<String> words =
        List.of("the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog");

    for (String formattedLine : justify(words, 16)) {
      System.out.println(formattedLine);
    }
  }
}
