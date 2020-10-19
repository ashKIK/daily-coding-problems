import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Microsoft.
//
// Given a dictionary of words and a string made up of those words (no spaces),
// return the original sentence in a list.
// If there is more than one possible reconstruction, return any of them.
// If there is no possible reconstruction, then return null.
//
// For example, given the set of words 'quick', 'brown', 'the', 'fox',
// and the string "thequickbrownfox", you should return ['the', 'quick', 'brown', 'fox'].
//
// Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond',
// and the string "bedbathandbeyond",
// return either ['bed', 'bath', 'and', 'beyond] or ['bedbath', 'and', 'beyond'].

/**
 * @author ashKIK
 */
public class Problem_22 {

  private static Set<String> words(String... words) {
    Set<String> set = new HashSet<>();
    Collections.addAll(set, words);
    return set;
  }

  private static List<String> splitWords(Set<String> words, String str) {
    Deque<String> queue = new ArrayDeque<>();
    splitWords(queue, words, str, 0);
    return queue.isEmpty() ? null : new ArrayList<>(queue);
  }

  private static boolean splitWords(Deque<String> deque, Set<String> words, String str, int offs) {
    if (offs >= str.length()) {
      return true;
    }
    for (String word : words) {
      if (!str.startsWith(word, offs)) {
        continue;
      }
      deque.addLast(word);
      if (splitWords(deque, words, str, offs + word.length())) {
        return true;
      }
      deque.removeLast();
    }

    return false;
  }

  public static void main(String... args) {
    System.out.println(
        splitWords(words("quick", "brown", "the", "fox"), "thequickbrownfox"));
    System.out.println(
        splitWords(words("bed", "bath", "bedbath", "and", "beyond"), "bedbathandbeyond"));
  }
}
