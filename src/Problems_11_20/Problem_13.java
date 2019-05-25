package Problems_11_20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Amazon.
//
// Given an integer k and a string s,
// find the length of the longest substring that contains at most k distinct characters.
//
// For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".

/**
 * @author ashKIK
 */
public class Problem_13 {

  private static String findLongestSubstring(String s, int k) {
    Map<Character, Set<Integer>> map = new HashMap<>();
    String result = null;

    for (int i = 0, j = 0; j < s.length(); ) {
      if (!map.containsKey(s.charAt(j))) {
        if (map.size() >= k) {
          if ((result == null) || ((j - i + 1) > result.length())) {
            result = s.substring(i, j);
          }
          map.get(s.charAt(i)).remove(i);
          if (map.get(s.charAt(i)).isEmpty()) {
            map.remove(s.charAt(i));
          }
          i++;
        } else {
          map.put(s.charAt(j), new HashSet<>());
          map.get(s.charAt(j)).add(j++);
        }
      } else {
        map.get(s.charAt(j)).add(j++);
      }
    }

    return result;
  }

  public static void main(String... args) {
    System.out.println(findLongestSubstring("abcba", 2));
  }
}
