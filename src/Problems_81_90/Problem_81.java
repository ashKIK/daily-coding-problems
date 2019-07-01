package Problems_81_90;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// Yelp.
//
// Given a mapping of digits to letters (as in a phone number), and a digit string,
// return all possible letters the number could represent.
// You can assume each valid number in the mapping is a single digit.
//
// For example if {“2”: [“a”, “b”, “c”], 3: [“d”, “e”, “f”], …} then “23” should return
// [“ad”, “ae”, “af”, “bd”, “be”, “bf”, “cd”, “ce”, “cf"].

/**
 * @author ashKIK
 */
public class Problem_81 {

  private static final Map<Character, char[]> TABLE = new HashMap<>();

  static {
    TABLE.put('2', "abc".toCharArray());
    TABLE.put('3', "def".toCharArray());
    TABLE.put('4', "ghi".toCharArray());
    TABLE.put('5', "jkl".toCharArray());
    TABLE.put('6', "mno".toCharArray());
    TABLE.put('7', "pqrs".toCharArray());
    TABLE.put('8', "tuv".toCharArray());
    TABLE.put('9', "wxyz".toCharArray());
  }

  private static char[] getLetters(char digit) {
    return TABLE.getOrDefault(digit, new char[0]);
  }

  private static Set<String> getWords(int num) {

    String digits = String.valueOf(num);
    Set<String> words = new TreeSet<>();

    for (int i = 0; i < digits.length(); i++) {
      char digit = digits.charAt(i);

      Set<String> prvWords = new HashSet<>(words);
      words.clear();

      for (char letter : getLetters(digit)) {
        if (!prvWords.isEmpty()) {
          for (String word : prvWords) {
            words.add(word + letter);
          }
        } else {
          words.add(Character.toString(letter));
        }
      }
    }

    return words;
  }

  public static void main(String... args) {

    System.out.println(String.join(" ", getWords(9)));
    System.out.println(String.join(" ", getWords(12)));
    System.out.println(String.join(" ", getWords(123)));
    System.out.println(String.join(" ", getWords(1234)));
  }
}
