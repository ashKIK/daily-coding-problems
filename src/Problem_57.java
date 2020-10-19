import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Amazon.
//
// Given a string s and an integer k,
// break up the string into multiple texts such that each text has a length of k or less.
// You must break it up so that words don't break across lines.
// If there's no way to break the text up, then return null.
//
// You can assume that there are no spaces at the ends of the string and
// that there is exactly one space between each word.
//
// For example, given the string "the quick brown fox jumps over the lazy dog" and k = 10,
// you should return: ["the quick", "brown fox", "jumps over", "the lazy", "dog"].
//
// No string in the list has a length of more than 10.

/**
 * @author ashKIK
 */
public class Problem_57 {

  private static List<String> splitIntoWords(String str, int k) {

    String[] words = str.split("\\s+");
    List<String> result = new ArrayList<>(words.length);

    StringBuilder temp = null;
    for (String word : words) {
      if (word.length() > k) {
        return null;
      }
      if (temp == null) {
        temp = new StringBuilder(word);
      } else if (temp.length() + word.length() + 1 <= k) {
        temp.append(' ').append(word);
      } else {
        result.add(temp.toString());
        temp = new StringBuilder(word);
      }
    }

    if (temp != null) {
      result.add(temp.toString());
    }

    return result;
  }

  public static void main(String... args) {

    List<String> result = splitIntoWords("the quick brown fox jumps over the lazy dog", 10);
    System.out.println(result != null ? Arrays.toString(result.toArray(new String[0])) : null);
  }
}
